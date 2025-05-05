package com.examly.springapp.service;
 
import java.util.List;
import java.util.Map;
import com.examly.springapp.model.VehicleMaintenanceDTO;
 
public interface VehicleService {
    public VehicleMaintenanceDTO addServices(VehicleMaintenanceDTO vehicleMaintenanceDTO);
    public VehicleMaintenanceDTO updateServices(Long serviceId, VehicleMaintenanceDTO vehicleMaintenanceDTO);
    public Map<String,String> deleteService(Long serviceId);
    public List<VehicleMaintenanceDTO> getAllServices();
    public VehicleMaintenanceDTO getServiceById(Long serviceId);
    public List<VehicleMaintenanceDTO> findByServicesName(String serviceName);
}
 
 