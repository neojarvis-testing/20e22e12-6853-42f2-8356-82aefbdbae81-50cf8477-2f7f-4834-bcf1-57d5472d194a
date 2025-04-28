import { User } from "./user.model";
import { VehicleMaintenance } from "./vehicle-maintenance.model";

export interface Appointment{
    appointmentId?:number;
    service:VehicleMaintenance;
    appointmentDate:number;
    location:string;
    status?:string;
    user?:User

}