import { Component, OnInit } from '@angular/core';

import { Appointment } from 'src/app/models/appointment.model';

import { VehicleMaintenance } from 'src/app/models/vehicle-maintenance.model';

import { AppointmentService } from 'src/app/services/appointment.service';

import { VehicleService } from 'src/app/services/vehicle.service';

@Component({

  selector: 'app-adminviewappointment',

  templateUrl: './adminviewappointment.component.html'

})
export class AdminviewappointmentComponent implements OnInit {

  
  constructor(private readonly vehicleService:VehicleService,private readonly appointmentService:AppointmentService ) { }
  
  appointments:Appointment[]=[]
  
  vehicles:VehicleMaintenance[]=[]
  
  appointment:Appointment
  
  isApproved:boolean=false;
  
  isRejected:boolean=false;

  
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
    
    this.vehicles=[]
    
    for(let appointment of this.appointments){
    
      this.vehicleService.getVehicleServiceById(appointment.serviceId).subscribe((data)=>{
    
        const vehicleDetails={
    
          ...data,
    
          appointment
    
        }
    
        console.log(vehicleDetails)
    
        this.vehicles.push(vehicleDetails);
    
      })
    
    }
  
  }

  approve(id:number, appointment:Appointment){
  
    this.isApproved=true;
  
    appointment.status="APPROVED"
  
    this.appointmentService.updateAppointments(id,appointment).subscribe((data)=>{
  
      alert('Approved successfully');
  
      this.getAllAppointments();
  
    })
  
  }
  
  reject(id:number, appointment:Appointment){
  
    this.isRejected=true
  
    appointment.status="REJECTED"
  
    this.appointmentService.updateAppointments(id,appointment).subscribe((data)=>{
  
      alert('Rejected successfully');
  
      this.getAllAppointments();
  
    })
  
  }

  
  deleteAppointment(id:number){
  
    this.appointmentService.deleteAppointment(id).subscribe(()=>{
  
      alert("Appointment Deleted Successfully")
  
      this.getAllAppointments()
  
    })
  
  }


}
