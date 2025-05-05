package com.examly.springapp.mapper;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.model.VehicleMaintenanceDTO;

public class VechicleMapper {
     public static VehicleMaintenance mapVehicleDTOToVehicle(VehicleMaintenanceDTO vehicleMaintenanceDTO){
        return new VehicleMaintenance(
            vehicleMaintenanceDTO.getServiceName(),
            vehicleMaintenanceDTO.getServicePrice(),
            vehicleMaintenanceDTO.getTypeOfVehicle()
        );
    }

    public static VehicleMaintenanceDTO mapVehicleToVehicleDTO(VehicleMaintenance vehicleMaintenance){
        return new VehicleMaintenanceDTO(
            vehicleMaintenance.getId(),
            vehicleMaintenance.getServiceName(),
            vehicleMaintenance.getServicePrice(),
            vehicleMaintenance.getTypeOfVehicle()
          
        );
    }
}
