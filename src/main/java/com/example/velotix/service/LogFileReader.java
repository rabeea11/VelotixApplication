package com.example.velotix.service;

import com.example.velotix.dto.Log;
import com.example.velotix.interfaces.repository.ILogToDb;
import com.example.velotix.interfaces.service.ILogFileReader;
import com.example.velotix.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LogFileReader implements ILogFileReader {

    @Autowired
    ILogToDb logToDb;

    @Override
    @PostConstruct
    public void readLogFile() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(Constants.FILE_LOCATION);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null) {
                if(strLine.startsWith(Constants.INFO) || strLine.startsWith(Constants.WARN)){
                    mapLineToLogEntry(strLine);
                }
                else if(strLine.startsWith(Constants.ERROR)){
                    StringBuilder stringBuilder=new StringBuilder(strLine);
                    while ((strLine = br.readLine()) != null && !(strLine.startsWith(Constants.INFO) || strLine.startsWith(Constants.WARN))){
                        stringBuilder.append(strLine);
                    }
                    mapLineToLogEntry(stringBuilder.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
           throw new NullPointerException();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Log> getByTimeRange(String from, String to) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(Constants.RANGE_PATTERN);
        LocalDateTime dateTimeFrom=LocalDateTime.parse(from,fmt);
        LocalDateTime dateTimeTo=LocalDateTime.parse(to,fmt);

        return logToDb.findByTimeBetween(dateTimeFrom,dateTimeTo);
    }


    private Log mapLineToLogEntry(String line) {
       Log log=new Log();
       String[] splitToGetLevel= line.split(Constants.DOTS,2);
       String level=splitToGetLevel[0].trim();
       log.setLevel(level);
       String[] splitToGetTime=(splitToGetLevel[1].split(Constants.SPACE,4));
       String timeStr=(splitToGetTime[1]+Constants.SPACE+splitToGetTime[2]).trim();
       DateTimeFormatter fmt = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
       LocalDateTime dateTime=LocalDateTime.parse(timeStr,fmt);
       log.setTime(dateTime);
       log.setMessage(splitToGetTime[3]);
        logToDb.save(log);
        return log;
    }

    @Override
    public List<Log> retrieveAllLogs(){
        return (List<Log>) logToDb.findAll();
    }

    @Override
    public List<Log> getByMessage(String message){
        return logToDb.findByMessageContains(message);
    }

    @Override
    public List<Log> getByLevel(String level){
        return logToDb.findByLevel(level);
    }

}
