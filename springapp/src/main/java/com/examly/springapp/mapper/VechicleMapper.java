package com.examly.springapp.mapper;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.model.VehicleMaintenanceDTO;

public class VechicleMapper {
     public static VehicleMaintenance mapUserDtoToVehicle(VehicleMaintenanceDTO vehicleMaintenanceDTO){
        return new VehicleMaintenance(
            vehicleMaintenanceDTO.getServiceName(),
            vehicleMaintenanceDTO.getServicePrice(),
            vehicleMaintenanceDTO.getTypeOfVehicle()
        );
    }

    public static VehicleMaintenanceDTO mapUserToUserDTO(VehicleMaintenance vehicleMaintenance){
        return new VehicleMaintenanceDTO(
            vehicleMaintenance.getServiceName(),
            vehicleMaintenance.getServicePrice(),
            vehicleMaintenance.getTypeOfVehicle()
          
        );
    }
}
