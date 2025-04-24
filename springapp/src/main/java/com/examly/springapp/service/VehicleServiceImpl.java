package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.repository.VehicleServiceRepo;
@Service
public class VehicleServiceImpl implements VehicleService{
    private final VehicleServiceRepo vehicleServiceRepo;

    // Constructor-based dependency injection
    public VehicleServiceImpl(VehicleServiceRepo vehicleServiceRepo) {
        this.vehicleServiceRepo = vehicleServiceRepo;
    }
    public VehicleMaintenance addService(VehicleMaintenance vehicleMaintenance) {
        return vehicleServiceRepo.save(vehicleMaintenance);  
    }

    public VehicleMaintenance updateService(Long serviceId, VehicleMaintenance vehicleMaintenance) {
        VehicleMaintenance vehicleMaintain=vehicleServiceRepo.findById(serviceId).orElse(null);
        if(vehicleMaintain==null){
            return null;
        }
        vehicleMaintenance.setServiceId(serviceId);
        return vehicleServiceRepo.save(vehicleMaintenance);
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
