import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/models/appointment.model';
import { VehicleMaintenance } from 'src/app/models/vehicle-maintenance.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-userviewappointment',
  templateUrl: './userviewappointment.component.html',
  styleUrls: ['./userviewappointment.component.css']
})
export class UserviewappointmentComponent implements OnInit {

  constructor(private appointmentService:AppointmentService,private vehicleService:VehicleService) { }
  appointments:Appointment[]=[]
  vehicles:VehicleMaintenance[]=[]

  ngOnInit(): void {
    this.getAllAppointments()
  }
  getAllAppointments(){
    this.appointmentService.getAllAppointments().subscribe((data)=>{
      this.appointments=data
      console.log(data)
      this.getVehicleServicesForAppointmentId();
    })
  }

  getVehicleServicesForAppointmentId():void{
    for(let appointment of this.appointments){
        this.vehicleService.getVehicleServiceById(appointment.service.serviceId).subscribe((data)=>{
          const vehicleDetails={
            ...data,
            appointment
          }
          console.log(vehicleDetails)
          this.vehicles.push(vehicleDetails);
        })
    }
  }
}
