package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.VehicleMaintenance;
@Repository
public interface VehicleServiceRepo extends JpaRepository<VehicleMaintenance,Long>{
    

}
