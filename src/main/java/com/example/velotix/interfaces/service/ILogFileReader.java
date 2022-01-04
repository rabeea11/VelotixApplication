package com.example.velotix.interfaces.service;

import com.example.velotix.dto.Log;

import java.util.List;

public interface ILogFileReader {
    public void readLogFile();

    public List<Log> getByTimeRange(String from, String to);
    public List<Log> retrieveAllLogs();
    public List<Log> getByMessage(String message);
    public List<Log> getByLevel(String level);
}