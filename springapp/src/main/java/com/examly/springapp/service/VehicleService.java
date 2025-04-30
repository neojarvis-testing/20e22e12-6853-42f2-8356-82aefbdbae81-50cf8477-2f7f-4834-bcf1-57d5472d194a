package com.examly.springapp.service;
 
import java.util.List;
import java.util.Map;
import java.util.Optional;
 
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.model.VehicleMaintenanceDTO;
 
public interface VehicleService {
    // public VehicleMaintenanceDTO addService(VehicleMaintenanceDTO vehicleMaintenanceDTO);
    public VehicleMaintenance addService(VehicleMaintenance vehicleMaintenanceDTO);
    public VehicleMaintenance updateService(Long id, VehicleMaintenance vehicleMaintenanceDTO);
    public Map<String, String> deleteService(Long serviceId);
    public List<VehicleMaintenanceDTO> getAllServices();
    public VehicleMaintenanceDTO getServiceById(Long serviceId);
    public List<VehicleMaintenanceDTO> findByServiceName(String serviceName);


}
 
 