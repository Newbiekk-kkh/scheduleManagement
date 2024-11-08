package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String userName;
    private String title;
    private String contents;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Schedule(String userName, String title, String contents, String password) {
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public Schedule(Long id, String userName, String title, String contents, String password) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public void update(String userName, String title, String contents) {
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }
}
