package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getUserName(), dto.getTitle(), dto.getContents(), dto.getPassword());

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String userName, String title, String contents) {
        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
        if (userName == null || title == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and title cannot be null");
        }

        schedule.update(userName, title, contents);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }

        scheduleRepository.deleteSchedule(id);
    }

}
