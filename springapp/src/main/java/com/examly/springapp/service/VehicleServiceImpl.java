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

    public boolean deleteService(Long serviceId) {
        VehicleMaintenance vehicleMaintenance=vehicleServiceRepo.findById(serviceId).orElse(null);
        if(vehicleMaintenance==null){
            return false;
        }
        vehicleServiceRepo.deleteById(serviceId);
        return true;  
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
