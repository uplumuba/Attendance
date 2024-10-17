package com.Attendance.service;

import com.Attendance.dto.AttendanceDto;
import com.Attendance.modle.AttendanceModel;
import com.Attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<AttendanceModel> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }

    public Optional<AttendanceModel> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    public AttendanceModel createAttendance(AttendanceDto attendanceDto) {
        AttendanceModel attendance = new AttendanceModel(
                attendanceDto.getEmployeeId(),
                attendanceDto.getCheckInTime(),
                attendanceDto.getCheckOutTime()
        );
        return attendanceRepository.save(attendance);
    }

    public AttendanceModel updateAttendance(Long id, AttendanceDto attendanceDto) {
        AttendanceModel attendance = attendanceRepository.findById(id).orElseThrow();
        attendance.setCheckInTime(attendanceDto.getCheckInTime());
        attendance.setCheckOutTime(attendanceDto.getCheckOutTime());
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}