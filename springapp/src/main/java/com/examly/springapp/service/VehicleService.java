package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.VehicleMaintenance;

public interface VehicleService {
    public VehicleMaintenance addService(VehicleMaintenance vehicleMaintenance);
    public VehicleMaintenance updateService(Long serviceId, VehicleMaintenance vehicleMaintenance);
    public String deleteService(Long serviceId);
    public List<VehicleMaintenance> getAllServices();
    public Optional<VehicleMaintenance> getServiceById(Long serviceId);
    public List<VehicleMaintenance> findByServiceName(String serviceName);






}
