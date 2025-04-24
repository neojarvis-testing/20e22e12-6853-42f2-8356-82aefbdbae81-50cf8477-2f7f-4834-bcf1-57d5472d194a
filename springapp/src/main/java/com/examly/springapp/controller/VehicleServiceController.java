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
        vehicleMaintenance=vehicleServiceImpl.addService(vehicleMaintenance);
        return ResponseEntity.status(201).body(vehicleMaintenance);
    }
    
    @PutMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenance> updateService(@PathVariable Long id,@RequestBody VehicleMaintenance vehicleMaintenance){
        vehicleMaintenance=vehicleServiceImpl.updateService(id,vehicleMaintenance);
        return ResponseEntity.status(200).body(vehicleMaintenance);
    }

    @DeleteMapping("/api/services/{id}")
    public ResponseEntity<String>  deleteService(@PathVariable Long id){
        boolean res=vehicleServiceImpl.deleteService(id);
        if(res){
            return ResponseEntity.status(201).body("User Deleted Successfully");
        }else{
            return ResponseEntity.status(404).body("Id not found");
        }
    }

    @GetMapping("/api/services")
    public ResponseEntity<List<VehicleMaintenance>> getAllServices(){
        List<VehicleMaintenance> list=vehicleServiceImpl.getAllServices();
        return ResponseEntity.status(201).body(list);
    }

    @GetMapping("/api/services/{id}")
    public ResponseEntity<VehicleMaintenance> getServiceById(@PathVariable Long id){
        Optional<VehicleMaintenance> vehicleMaintenance=vehicleServiceImpl.getServiceById(id);
        return ResponseEntity.status(200).body(vehicleMaintenance.get());
    }

    @GetMapping("/api/services/serviceName")
    public ResponseEntity<List<VehicleMaintenance>>  findByServiceName(@RequestParam("serviceName") String serviceName){
        List<VehicleMaintenance>list=vehicleServiceImpl.findByServiceName(serviceName);
        if(list.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(list);
    }
}
