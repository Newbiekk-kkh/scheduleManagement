package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScheduleRepository {
        Schedule saveSchedule(Schedule schedule);
        List<ScheduleResponseDto> findAllSchedules();
        Schedule findScheduleById(Long id);
        void deleteSchedule(Long id);
}
