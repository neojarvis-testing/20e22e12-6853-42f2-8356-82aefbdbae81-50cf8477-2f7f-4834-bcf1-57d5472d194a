package com.examly.springapp.service;
 
import java.util.List;
import java.util.Map;
import java.util.Optional;
 
import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.AppointmentDTO;
 
public interface AppointmentService {
    public AppointmentDTO addAppointments(AppointmentDTO appointmentDTO);
    public List<AppointmentDTO> getAppointmentsbyUserId(int userId);
    public List<AppointmentDTO> getAllAppointments();
    public AppointmentDTO updateAppointments(AppointmentDTO appointmentDTO, long appointmentId);
    public Map<String,String> deleteAppointment(long appointmentId);
    public Optional<Appointment> getAppointmentById(long appointmentId);
    public AppointmentDTO getLastAppointmentbyUserId(int userId);
    public AppointmentDTO getAppointmentsById(long id);
    public List<AppointmentDTO> getAppointmentsByPagination(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
}