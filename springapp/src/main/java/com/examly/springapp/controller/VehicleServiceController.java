package com.examly.springapp.controller;
import java.util.List;
import java.util.Map;
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
import com.examly.springapp.model.VehicleMaintenanceDTO;
import com.examly.springapp.service.VehicleServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Vehicle Service Controller", description = "APIs for managing vehicle maintenance services.")
public class VehicleServiceController {

    @Autowired
    VehicleServiceImpl vehicleServiceImpl;

    @Operation(summary = "Add a new service", description = "Creates a new vehicle maintenance service, specifying details like service name, price, and type of vehicle.")
    @PostMapping("/api/service")
    public ResponseEntity<VehicleMaintenanceDTO> addServices(@Valid @RequestBody VehicleMaintenanceDTO vehicleMaintenanceDTO){
        return ResponseEntity.status(201).body(vehicleServiceImpl.addServices(vehicleMaintenanceDTO));
    }

    @PostMapping("/api/services")
    public ResponseEntity<VehicleMaintenance> addService(@Valid @RequestBody VehicleMaintenance vehicleMaintenanceDTO){
        return ResponseEntity.status(201).body(vehicleServiceImpl.addService(vehicleMaintenanceDTO));
    }
    
    @Operation(summary = "Update service by ID", description = "Updates the details of a specific vehicle maintenance service by its ID.")
    @PutMapping("/api/service/{id}")
    public ResponseEntity<VehicleMaintenanceDTO> updateServices(@Valid @PathVariable Long id,@RequestBody VehicleMaintenanceDTO vehicleMaintenanceDTO){
        return ResponseEntity.status(200).body(vehicleServiceImpl.updateServices(id, vehicleMaintenanceDTO));
    }

    @PutMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenance> updateService(@Valid @PathVariable Long id,@RequestBody VehicleMaintenance vehicleMaintenanceDTO){
        return ResponseEntity.status(200).body(vehicleServiceImpl.updateService(id, vehicleMaintenanceDTO));
    }

    @Operation(summary = "Delete service by ID", description = "Deletes a specific vehicle maintenance service by its ID.")
    @DeleteMapping("/api/services/{id}")
    public ResponseEntity<Map<String, String>>  deleteService(@PathVariable Long id){
        return ResponseEntity.status(200).body(vehicleServiceImpl.deleteService(id));
    }

    @Operation(summary = "Get all services", description = "Retrieves a list of all available vehicle maintenance services.")
    @GetMapping("/api/services")
    public ResponseEntity<List<VehicleMaintenanceDTO>> getAllServices(){
        return ResponseEntity.status(200).body(vehicleServiceImpl.getAllServices());
    }

    // @GetMapping("/api/services/{id}")
    // public ResponseEntity<VehicleMaintenance> getServiceById(@PathVariable Long id){
    //     return ResponseEntity.status(200).body(vehicleServiceImpl.getServiceById(id).get());
    // }
    
    @Operation(summary = "Get service by ID", description = "Fetches a specific vehicle maintenance service by its ID.")
    @GetMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenanceDTO> getServiceById(@PathVariable Long id){
        return ResponseEntity.status(200).body(vehicleServiceImpl.getServiceById(id));
    }

    @Operation(summary = "Get service by name", description = "Fetches a specific vehicle maintenance service by its name.")
    @GetMapping("/api/services/serviceName")
    public ResponseEntity<List<VehicleMaintenanceDTO>>  findByServiceNames(@RequestParam("serviceName") String serviceName){
        return ResponseEntity.status(200).body(vehicleServiceImpl.findByServicesName(serviceName));
    }

    @GetMapping("/api/services/service")
    public ResponseEntity<List<VehicleMaintenanceDTO>>  findByServiceName(@RequestParam("serviceName") String serviceName){
        return ResponseEntity.status(200).body(vehicleServiceImpl.findByServiceName(serviceName));
    }
}
