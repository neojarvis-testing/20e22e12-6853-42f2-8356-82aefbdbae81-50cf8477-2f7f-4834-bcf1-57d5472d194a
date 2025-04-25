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

    @Autowired
    VehicleServiceImpl vehicleServiceImpl;
    @PostMapping("/api/services")
    public ResponseEntity<VehicleMaintenance> addService(@RequestBody VehicleMaintenance vehicleMaintenance){
        return ResponseEntity.status(201).body(vehicleServiceImpl.addService(vehicleMaintenance));
    }
    
    @PutMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenance> updateService(@PathVariable Long id,@RequestBody VehicleMaintenance vehicleMaintenance){
        return ResponseEntity.status(200).body(vehicleServiceImpl.updateService(id, vehicleMaintenance));
    }

    @DeleteMapping("/api/services/{id}")
    public ResponseEntity<String>  deleteService(@PathVariable Long id){
        return ResponseEntity.status(200).body(vehicleServiceImpl.deleteService(id));
    }

    @GetMapping("/api/services")
    public ResponseEntity<List<VehicleMaintenance>> getAllServices(){
        return ResponseEntity.status(201).body(vehicleServiceImpl.getAllServices());
    }

    @GetMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenance> getServiceById(@PathVariable Long id){
        return ResponseEntity.status(200).body(vehicleServiceImpl.getServiceById(id).get());
    }

    @GetMapping("/api/services/serviceName")
    public ResponseEntity<List<VehicleMaintenance>>  findByServiceName(@RequestParam("serviceName") String serviceName){
        return ResponseEntity.status(200).body(vehicleServiceImpl.findByServiceName(serviceName));
    }
}
