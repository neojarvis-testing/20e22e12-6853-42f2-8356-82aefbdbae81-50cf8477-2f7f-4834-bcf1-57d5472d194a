package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
            return null;
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
        return vehicleServiceRepo.findAll();
    }

    public Optional<VehicleMaintenance> getServiceById(Long serviceId) {
        return vehicleServiceRepo.findById(serviceId);
    }


    public List<VehicleMaintenance> findByServiceName(String serviceName) {
        return vehicleServiceRepo.findByServiceName(serviceName);
    }
    
}
