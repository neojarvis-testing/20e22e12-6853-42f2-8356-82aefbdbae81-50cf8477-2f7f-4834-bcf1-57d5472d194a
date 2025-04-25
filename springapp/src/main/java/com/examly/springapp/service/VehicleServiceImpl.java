package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.repository.VehicleServiceRepo;
@Service
public class VehicleServiceImpl implements VehicleService{
    private final VehicleServiceRepo vehicleServiceRepo;

     /**
     * Constructor-based dependency injection ensures that the repository instance 
     * is provided at the time of object creation. This improves testability and maintainability.
     *
     * @param vehicleServiceRepo Repository for managing vehicle maintenance services
     */
    public VehicleServiceImpl(VehicleServiceRepo vehicleServiceRepo) {
        this.vehicleServiceRepo = vehicleServiceRepo;
    }
    /**
     * Adds a new vehicle maintenance service entry to the database.
     *
     * @param vehicleMaintenance The vehicle maintenance details to be saved
     * @return The saved VehicleMaintenance object
     */
    public VehicleMaintenance addService(VehicleMaintenance vehicleMaintenance) {
        return vehicleServiceRepo.save(vehicleMaintenance);  
    }
    /**
     * Updates an existing vehicle maintenance service entry.
     * Ensures that the service exists before updating.
     *
     * @param serviceId The unique identifier of the vehicle service
     * @param vehicleMaintenance Updated details of the vehicle service
     * @return Updated VehicleMaintenance object if found, otherwise returns null
     */

    public VehicleMaintenance updateService(Long serviceId, VehicleMaintenance vehicleMaintenance) {
        VehicleMaintenance vehicleMaintain=vehicleServiceRepo.findById(serviceId).orElse(null);

        // Check if the existing vehicle service entry is found

        if(vehicleMaintain==null){
            return null;
        }
        // Set the correct serviceId before updating

        vehicleMaintenance.setServiceId(serviceId);
        return vehicleServiceRepo.save(vehicleMaintenance);
    }
     /**
     * Deletes a vehicle maintenance service entry based on the ID.
     *
     * @param serviceId The unique identifier of the vehicle service
     * @return Success message if deleted, otherwise a message indicating the service was not found
     */

    public String deleteService(Long serviceId) {
       if(vehicleServiceRepo.existsById(serviceId)){
          vehicleServiceRepo.deleteById(serviceId);
          return "Vehicle Service ID Deleted successfully";
       }
       return "Vehicle Service with ID "+serviceId+" not found";
    }

    /**
     * Retrieves all vehicle maintenance service records.
     *
     * @return List of all VehicleMaintenance records stored in the database
     */

    public List<VehicleMaintenance> getAllServices() {
        return vehicleServiceRepo.findAll();
    }

     /**
     * Retrieves a specific vehicle maintenance service record by its ID.
     *
     * @param serviceId The unique identifier of the vehicle service
     * @return Optional containing the VehicleMaintenance object if found
     */

    public Optional<VehicleMaintenance> getServiceById(Long serviceId) {
        return vehicleServiceRepo.findById(serviceId);
    }

     /**
     * Finds vehicle maintenance service records based on the service name.
     *
     * @param serviceName The name of the vehicle service to search for
     * @return List of matching VehicleMaintenance records
     */

    public List<VehicleMaintenance> findByServiceName(String serviceName) {
        return vehicleServiceRepo.findByServiceName(serviceName);
    }
    
}
