package com.examly.springapp.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.examly.springapp.model.Appointment;
 
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long>{
 
 
    @Query("select appointment from Appointment appointment where appointment.user.userId = ?1")
    List<Appointment> findByUserId(int userId);
 
    @Query("select appointment from Appointment appointment where appointment.user.userId = ?1 order by appointment.appointmentDate desc limit 1")
    Appointment getLastAppointmentbyUserId(int userId);
 
}
 