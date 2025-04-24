package com.examly.springapp.service;
 
import java.util.List;
import java.util.Optional;
 
import com.examly.springapp.model.Appointment;
 
public interface AppointmentService {
 
    public Appointment addAppointment(Appointment appointment);
 
    public List<Appointment> getAppointmentsbyUserId(int userId);
 
    public List<Appointment> getAllAppointments();
 
    public Appointment updateAppointment(Appointment appointment, long appointmentId);
 
    public String deleteAppointment(long appointmentId);
 
    public Optional<Appointment> getAppointmentById(long appointmentId);
 
    public Appointment getLastAppointmentbyUserId(int userId);
}