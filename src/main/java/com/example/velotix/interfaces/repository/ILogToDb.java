package com.example.velotix.interfaces.repository;

import com.example.velotix.dto.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ILogToDb extends JpaRepository<Log,String> {

    public List<Log> findByLevel(String level);
    public List<Log> findByTimeBetween(LocalDateTime from, LocalDateTime to);
    public List<Log> findByMessageContains(String message);

}
