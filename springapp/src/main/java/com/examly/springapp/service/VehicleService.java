package com.examly.springapp.service;
 
import java.util.List;
import java.util.Optional;
 
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.model.VehicleMaintenanceDTO;
 
public interface VehicleService {
    // public VehicleMaintenanceDTO addService(VehicleMaintenanceDTO vehicleMaintenanceDTO);
    public VehicleMaintenance addService(VehicleMaintenance vehicleMaintenanceDTO);
    public VehicleMaintenance updateService(Long id, VehicleMaintenance vehicleMaintenanceDTO);
    public String deleteService(Long serviceId);
    public List<VehicleMaintenance> getAllServices();
    public VehicleMaintenance getServiceById(Long serviceId);
    public List<VehicleMaintenance> findByServiceName(String serviceName);
 
 
 
 
 
 
}
 
 