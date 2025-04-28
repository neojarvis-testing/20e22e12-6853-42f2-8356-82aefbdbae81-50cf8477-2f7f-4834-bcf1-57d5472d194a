package com.examly.springapp.mapper;

import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.AppointmentDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VehicleMaintenance;

public class AppointmentMapper {

    // Convert AppointmentDTO into Appointment entity
    public static Appointment mapAppointmentDTOToAppointment(AppointmentDTO appointmentDTO,VehicleMaintenance vehicleMaintenance,User user) {
        return new Appointment(
            vehicleMaintenance,
            appointmentDTO.getAppointmentDate(),
            appointmentDTO.getLocation(),
            appointmentDTO.getStatus(),
            user
        );
    }

    // Convert Appointment entity into AppointmentDTO
    public static AppointmentDTO mapToAppointmentDTO(Appointment appointment) {
       return new AppointmentDTO(
            appointment.getService().getId(),
            appointment.getAppointmentDate(),
            appointment.getLocation(),
            appointment.getStatus(),
            appointment.getUser().getId()
       );
    }
}