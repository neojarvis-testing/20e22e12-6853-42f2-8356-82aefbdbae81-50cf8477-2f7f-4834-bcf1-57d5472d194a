package com.examly.springapp.repository;
 
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.examly.springapp.model.Appointment;
 
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long>{
 
    @Query("select appointment from Appointment appointment where appointment.user.id = ?1")
    List<Appointment> findByUserId(int userId);
 
    @Query("select appointment from Appointment appointment where appointment.user.id = ?1 order by appointment.appointmentDate desc limit 1")
    Appointment getLastAppointmentbyUserId(int userId);
    
        Page<Appointment> findByUser_Id(Long userId, Pageable pageable);
 
        // Example: Custom method to find appointments by status with pagination
        Page<Appointment> findByStatus(String status, Pageable pageable);
 
        // Example: Custom method to find appointments by location with pagination
        Page<Appointment> findByLocation(String location, Pageable pageable);
}
 
