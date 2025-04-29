package com.examly.springapp.service;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
    // Constructor-based dependency injection
    public VehicleServiceImpl(VehicleServiceRepo vehicleServiceRepo) {
        this.vehicleServiceRepo = vehicleServiceRepo;
    }
    public VehicleMaintenanceDTO addServices(VehicleMaintenanceDTO vehicleMaintenanceDTO) {
        logger.info("Adding new vehicle maintenance service: {}", vehicleMaintenanceDTO.getServiceName());
        VehicleMaintenance vehicleMaintenance = VechicleMapper.mapVehicleDTOToVehicle(vehicleMaintenanceDTO);
        vehicleServiceRepo.save(vehicleMaintenance); 
        logger.info("Vehicle maintenance service added successfully with ID: {}", vehicleMaintenance.getId());
        return VechicleMapper.mapVehicleToVehicleDTO(vehicleMaintenance);
    }

    public VehicleMaintenance addService(VehicleMaintenance vehicleMaintenanceDTO) {
          return  vehicleServiceRepo.save(vehicleMaintenanceDTO);
      }

      public VehicleMaintenanceDTO updateServices(Long serviceId, VehicleMaintenanceDTO vehicleMaintenanceDTO) {
        logger.info("Updating vehicle service with ID: {}", serviceId);
        VehicleMaintenance vehicleMaintenance = vehicleServiceRepo.findById(serviceId).orElse(null);
        if (vehicleMaintenance == null) {
            logger.error("Vehicle maintenance service not found for ID: {}", serviceId);
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Maintenance Service with ID: " + serviceId + " not found");
        }
        vehicleMaintenance.setId(serviceId);
        vehicleMaintenance.setServiceName(vehicleMaintenanceDTO.getServiceName());
        vehicleMaintenance.setServicePrice(vehicleMaintenanceDTO.getServicePrice());
        vehicleMaintenance.setTypeOfVehicle(vehicleMaintenanceDTO.getTypeOfVehicle());
        vehicleMaintenance = vehicleServiceRepo.save(vehicleMaintenance);
        logger.info("Vehicle maintenance service updated successfully for ID: {}", serviceId);
        return VechicleMapper.mapVehicleToVehicleDTO(vehicleMaintenance);
    }

    public VehicleMaintenance updateService(Long id, VehicleMaintenance vehicleMaintenanceDTO) {
        VehicleMaintenance vehicleMaintenance=vehicleServiceRepo.findById(id).orElse(null);
        if(vehicleMaintenance==null){
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Maintenance Service with ID: "+id+" not found");
        }
        vehicleMaintenance.setId(id);
        vehicleMaintenance.setServiceName(vehicleMaintenanceDTO.getServiceName());
        vehicleMaintenance.setServicePrice(vehicleMaintenanceDTO.getServicePrice());
        vehicleMaintenance.setTypeOfVehicle(vehicleMaintenanceDTO.getTypeOfVehicle());
        vehicleMaintenance=vehicleServiceRepo.save(vehicleMaintenance);
        return vehicleMaintenance;
    }

    public Map<String,String> deleteService(Long serviceId) {
        logger.info("Deleting vehicle maintenance service with ID: {}", serviceId);
        if (vehicleServiceRepo.existsById(serviceId)) {
            vehicleServiceRepo.deleteById(serviceId);
            logger.info("Vehicle maintenance service deleted successfully for ID: {}", serviceId);
            return Map.of("message","Vehicle Service ID Deleted successfully");
        }
        logger.error("Vehicle maintenance service not found for ID: {}", serviceId);
        return Map.of("message","Vehicle Service with ID " + serviceId + " not found");
    }

    public List<VehicleMaintenanceDTO> getAllServices() {
        logger.info("Fetching all vehicle maintenance services");
        List<VehicleMaintenance> vehicleMaintenanceList = vehicleServiceRepo.findAll();
        if (vehicleMaintenanceList.isEmpty()) {
            logger.error("Vehicle maintenance service list is empty");
            throw new VehicleMaintenanceListEmptyException("Vehicle Service List is Empty");
        }
        return vehicleMaintenanceList.stream()
        .map(service->VechicleMapper.mapVehicleToVehicleDTO(service)).toList();
    }

    public VehicleMaintenanceDTO getServiceById(Long serviceId) {
        logger.info("Fetching vehicle maintenance service by ID: {}", serviceId);
        VehicleMaintenance vehicleMaintenance = vehicleServiceRepo.findById(serviceId).orElse(null);
        if (vehicleMaintenance==null) {
            logger.error("Vehicle maintenance service not found for ID: {}", serviceId);
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Service With ID: " + serviceId + " not found");
        }
        return VechicleMapper.mapVehicleToVehicleDTO(vehicleMaintenance);
    }

    public List<VehicleMaintenanceDTO> findByServicesName(String serviceName) {
        logger.info("Fetching vehicle maintenance services by name: {}", serviceName);
        List<VehicleMaintenance> vehicleMaintenanceList = vehicleServiceRepo.findByServiceName(serviceName);
        if (vehicleMaintenanceList.isEmpty()) {
            logger.error("No vehicle maintenance services found for name: {}", serviceName);
            throw new VehicleMaintenanceListEmptyException("Vehicle Maintenance For that name not found");
        }
        return vehicleMaintenanceList.stream()
        .map(service->VechicleMapper.mapVehicleToVehicleDTO(service)).toList();
    }

    public List<VehicleMaintenanceDTO> findByServiceName(String serviceName) {
        List<VehicleMaintenance> vehicleMaintenanceList=vehicleServiceRepo.findByServiceName(serviceName);
        if(vehicleMaintenanceList.isEmpty()){
            throw new VehicleMaintenanceListEmptyException("Vehicle Maintenance For that name not found");
        }
        return vehicleMaintenanceList.stream()
        .map(service->VechicleMapper.mapVehicleToVehicleDTO(service)).toList();
    }
    
}
