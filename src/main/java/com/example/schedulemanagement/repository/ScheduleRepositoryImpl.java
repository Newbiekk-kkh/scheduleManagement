package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
        schedule.setId(scheduleId);

        scheduleList.put(scheduleId, schedule);

        return schedule;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        List<ScheduleResponseDto> allSchedules = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            allSchedules.add(responseDto);
        }

        return allSchedules;
    }
}
