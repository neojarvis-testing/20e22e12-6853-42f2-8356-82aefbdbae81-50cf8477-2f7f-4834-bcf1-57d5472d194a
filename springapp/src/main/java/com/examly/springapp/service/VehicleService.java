package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.model.VehicleMaintenanceDTO;

public interface VehicleService {
    public VehicleMaintenanceDTO addService(VehicleMaintenanceDTO vehicleMaintenanceDTO);
    public VehicleMaintenanceDTO updateService(Long serviceId, VehicleMaintenanceDTO vehicleMaintenanceDTO);
    public String deleteService(Long serviceId);
    public List<VehicleMaintenance> getAllServices();
    public Optional<VehicleMaintenance> getServiceById(Long serviceId);
    public List<VehicleMaintenance> findByServiceName(String serviceName);






}
