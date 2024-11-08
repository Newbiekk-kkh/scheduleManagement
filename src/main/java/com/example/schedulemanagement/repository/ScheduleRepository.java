package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
        ScheduleResponseDto saveSchedule(Schedule schedule);
        List<ScheduleResponseDto> findAllSchedules();
        Optional<Schedule> findScheduleById(Long id);
        int updateSchedule(Long id, String userName, String title, String contents);
        int deleteSchedule(Long id);
        Schedule findScheduleByIdOrElseThrow(Long id);
}
