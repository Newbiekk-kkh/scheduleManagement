package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.entity.Schedule;
import org.springframework.stereotype.Repository;

public interface ScheduleRepository {
        Schedule saveSchedule(Schedule schedule);
}
