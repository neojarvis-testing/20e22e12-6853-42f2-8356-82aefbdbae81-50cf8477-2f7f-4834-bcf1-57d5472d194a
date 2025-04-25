package com.examly.springapp.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;
import com.examly.springapp.exception.VehicleMaintenanceListEmptyException;
import com.examly.springapp.exception.VehicleMaintenanceServiceNotFoundException;

import com.examly.springapp.mapper.VechicleMapper;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.model.VehicleMaintenanceDTO;
import com.examly.springapp.repository.VehicleServiceRepo;
@Service
public class VehicleServiceImpl implements VehicleService{
    private final VehicleServiceRepo vehicleServiceRepo;
 
    // Constructor-based dependency injection
    public VehicleServiceImpl(VehicleServiceRepo vehicleServiceRepo) {
        this.vehicleServiceRepo = vehicleServiceRepo;
    }
    public VehicleMaintenanceDTO addService(VehicleMaintenanceDTO vehicleMaintenanceDTO) {
      VehicleMaintenance vehicleMaintenance=VechicleMapper.mapUserDtoToVehicle(vehicleMaintenanceDTO);
        vehicleServiceRepo.save(vehicleMaintenance);
        return  VechicleMapper.mapUserToUserDTO(vehicleMaintenance);
    }
 
    public VehicleMaintenanceDTO updateService(Long serviceId, VehicleMaintenanceDTO vehicleMaintenanceDTO) {
        VehicleMaintenance vehicleMaintenance=vehicleServiceRepo.findById(serviceId).orElse(null);
        if(vehicleMaintenance==null){
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Maintenance Service with ID: "+serviceId+" not found");
        }
        vehicleMaintenance.setServiceId(serviceId);
        vehicleMaintenance.setServiceName(vehicleMaintenanceDTO.getServiceName());
        vehicleMaintenance.setServicePrice(vehicleMaintenanceDTO.getServicePrice());
        vehicleMaintenance.setTypeOfVehicle(vehicleMaintenanceDTO.getTypeOfVehicle());
        vehicleMaintenance=vehicleServiceRepo.save(vehicleMaintenance);
        return VechicleMapper.mapUserToUserDTO(vehicleMaintenance);
    }
 
    public String deleteService(Long serviceId) {
       if(vehicleServiceRepo.existsById(serviceId)){
          vehicleServiceRepo.deleteById(serviceId);
          return "Vehicle Service ID Deleted successfully";
       }
       return "Vehicle Service with ID "+serviceId+" not found";
    }
 
    public List<VehicleMaintenance> getAllServices() {
        List<VehicleMaintenance> vehicleMaintenanceList=vehicleServiceRepo.findAll();
        if(vehicleMaintenanceList.isEmpty()){
            throw new VehicleMaintenanceListEmptyException("Vehicle Service List is Empty");
        }
        return vehicleMaintenanceList;
    }
 
    public Optional<VehicleMaintenance> getServiceById(Long serviceId) {
        Optional<VehicleMaintenance> vehicleMaintenance=vehicleServiceRepo.findById(serviceId);
        if(vehicleMaintenance.isEmpty()){
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Service With ID: "+serviceId+" not found");
        }
        return vehicleMaintenance;
    }
 
 
    public List<VehicleMaintenance> findByServiceName(String serviceName) {
        List<VehicleMaintenance> vehicleMaintenanceList=vehicleServiceRepo.findByServiceName(serviceName);
        if(vehicleMaintenanceList.isEmpty()){
            throw new VehicleMaintenanceListEmptyException("Vehicle Maintenance For that name not found");
        }
        return vehicleMaintenanceList;
    }
   
}
