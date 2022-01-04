package com.example.velotix.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Log {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private String  id;
    @Column
    private String  level;
    @Column
    private LocalDateTime time;
    @Column(length = 65000,columnDefinition="Text")
    private String  message;
}
