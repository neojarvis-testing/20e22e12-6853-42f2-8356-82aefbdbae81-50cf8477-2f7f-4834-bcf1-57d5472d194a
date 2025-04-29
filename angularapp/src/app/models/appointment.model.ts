import { User } from "./user.model"
import { VehicleMaintenance } from "./vehicle-maintenance.model"

export interface Appointment{
    appointmentId?:number
    serviceId?:number
    appointmentDate:string
    location:string
    status?:string
    userId?:number
}