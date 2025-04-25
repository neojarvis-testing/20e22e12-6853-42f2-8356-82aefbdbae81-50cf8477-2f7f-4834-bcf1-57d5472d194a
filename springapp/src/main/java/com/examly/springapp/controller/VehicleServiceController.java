package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.service.VehicleServiceImpl;

@RestController
public class VehicleServiceController {

    private final VehicleServiceImpl vehicleServiceImpl;

     /**
     * Constructor-based dependency injection ensures that VehicleServiceImpl
     * is provided at the time of object creation. This improves testability
     * and avoids direct object instantiation.
     *
     * @param vehicleServiceImpl Injected instance of VehicleServiceImpl
     */

    public VehicleServiceController(VehicleServiceImpl vehicleServiceImpl) {
        this.vehicleServiceImpl = vehicleServiceImpl;
    }

      /**
     * Creates a new vehicle maintenance service record.
     *
     * @param vehicleMaintenance The service details provided in the request body
     * @return ResponseEntity containing the created service record with HTTP status 201 (Created)
     */

    @PostMapping("/api/services")
    public ResponseEntity<VehicleMaintenance> addService(@RequestBody VehicleMaintenance vehicleMaintenance){
        return ResponseEntity.status(201).body(vehicleServiceImpl.addService(vehicleMaintenance));
    }
    /**
     * Updates an existing vehicle maintenance service record.
     *
     * @param id Unique identifier of the service record to be updated
     * @param vehicleMaintenance Updated service details provided in the request body
     * @return ResponseEntity containing the updated service record with HTTP status 200 (OK)
     */
    @PutMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenance> updateService(@PathVariable Long id,@RequestBody VehicleMaintenance vehicleMaintenance){
        return ResponseEntity.status(200).body(vehicleServiceImpl.updateService(id, vehicleMaintenance));
    }
     /**
     * Deletes a vehicle maintenance service record by ID.
     *
     * @param id Unique identifier of the service record to be deleted
     * @return ResponseEntity containing a success message with HTTP status 200 (OK)
     * Consider handling cases where the service record does not exist by returning HTTP status 404.
     */

    @DeleteMapping("/api/services/{id}")
    public ResponseEntity<String>  deleteService(@PathVariable Long id){
        return ResponseEntity.status(200).body(vehicleServiceImpl.deleteService(id));
    }
     /**
     * Retrieves all vehicle maintenance service records stored in the system.
     *
     * @return ResponseEntity containing a list of all service records with HTTP status 200 (OK)
     */

    @GetMapping("/api/services")
    public ResponseEntity<List<VehicleMaintenance>> getAllServices(){
        return ResponseEntity.status(201).body(vehicleServiceImpl.getAllServices());
    }
      /**
     * Retrieves a specific vehicle maintenance service record by ID.
     *
     * @param id Unique identifier of the service record
     * @return ResponseEntity containing the requested service record with HTTP status 200 (OK)
     * Note: `.get()` is used on an Optional value, which may throw an exception if the record is not found.
     * Consider checking if the object is present before calling `.get()`.
     */

    @GetMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenance> getServiceById(@PathVariable Long id){
        return ResponseEntity.status(200).body(vehicleServiceImpl.getServiceById(id).get());
    }
    /**
     * Finds service records by service name.
     *
     * @param serviceName The name of the service to search for
     * @return ResponseEntity containing a list of matching service records with HTTP status 200 (OK)
     */

    @GetMapping("/api/services/serviceName")
    public ResponseEntity<List<VehicleMaintenance>>  findByServiceName(@RequestParam("serviceName") String serviceName){
        return ResponseEntity.status(200).body(vehicleServiceImpl.findByServiceName(serviceName));
    }
}
