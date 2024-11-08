package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 저장
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getUserName(), dto.getTitle(), dto.getContents(), dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    // 일정 조회(전체)
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    // 일정 조회(단건)
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    // 일정 수정
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        if (dto.getTitle() == null || dto.getUserName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and title cannot be null");
        }
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // 비밀번호 틀릴시 403 FORBIDDEN
        if (!schedule.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Passwords don't match");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, dto.getTitle(), dto.getContents(), dto.getUserName());

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
        // 새 데이터로 수정된 객체 재생성
        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(updatedSchedule);
    }

    // 일정 삭제
    @Override
    public void deleteSchedule(Long id, @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        // 비밀번호 틀릴시 403 FORBIDDEN
        if (!schedule.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Passwords don't match");
        }
        int deleteRow = scheduleRepository.deleteSchedule(id);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
    }
}
