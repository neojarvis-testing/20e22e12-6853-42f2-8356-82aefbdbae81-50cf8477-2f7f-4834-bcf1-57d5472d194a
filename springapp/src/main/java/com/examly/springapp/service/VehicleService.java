package com.examly.springapp.service;
 
import java.util.List;
 
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.model.VehicleMaintenanceDTO;
 
public interface VehicleService {

    public VehicleMaintenance addService(VehicleMaintenance vehicleMaintenanceDTO);
    public List<VehicleMaintenanceDTO> getAllServices();
    public VehicleMaintenanceDTO getServiceById(Long serviceId);
    public List<VehicleMaintenanceDTO> findByServiceName(String serviceName);

}
 
 