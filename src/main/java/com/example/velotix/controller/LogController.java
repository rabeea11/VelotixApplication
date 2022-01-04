package com.example.velotix.controller;

import com.example.velotix.dto.Log;
import com.example.velotix.interfaces.controller.ILogController;
import com.example.velotix.service.LogFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController implements ILogController {

    @Autowired
    private LogFileReader logFileReader;

    @GetMapping(value = "allLogs")
    public List<Log> getLogFileInput(){
        return logFileReader.retrieveAllLogs();
    }

    @GetMapping(value = "logs/level")
    public List<Log> getByLevel(@RequestParam String level){
        return logFileReader.getByLevel(level);
    }

    @GetMapping(value = "logs/message")
    public List<Log> getByMessage(@RequestParam String message){
        return logFileReader.getByMessage(message);
    }

    @GetMapping(value = "logs/dateRange")
    public List<Log> getByDateRange(@RequestParam String from, @RequestParam String to){
        return logFileReader.getByTimeRange(from,to);
    }


}
