import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/models/appointment.model';
import { VehicleMaintenance } from 'src/app/models/vehicle-maintenance.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { VehicleService } from 'src/app/services/vehicle.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-adminviewappointment',
  templateUrl: './adminviewappointment.component.html',
  styleUrls: ['./adminviewappointment.component.css']
})
export class AdminviewappointmentComponent implements OnInit {

  constructor(private vehicleService:VehicleService,private appointmentService:AppointmentService ) { }
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
  approve(id: number, appointment: Appointment) {
    this.isApproved = true;
    appointment.status = "APPROVED";
    this.appointmentService.updateAppointments(id, appointment).subscribe(() => {
      Swal.fire({
        icon: 'success',
        title: 'Approved!',
        text: 'Appointment has been approved successfully.',
        confirmButtonColor: '#28a745'
      });
      this.getAllAppointments();
    });
  }
  
  reject(id: number, appointment: Appointment) {
    this.isRejected = true;
    appointment.status = "REJECTED";
    this.appointmentService.updateAppointments(id, appointment).subscribe(() => {
      Swal.fire({
        icon: 'error',
        title: 'Rejected',
        text: 'Appointment has been rejected.',
        confirmButtonColor: '#dc3545'
      });
      this.getAllAppointments();
    });
  }
  
  deleteAppointment(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#6c757d',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.appointmentService.deleteAppointment(id).subscribe(() => {
          Swal.fire({
            icon: 'success',
            title: 'Deleted!',
            text: 'Appointment has been deleted successfully.',
            confirmButtonColor: '#28a745'
          });
          this.getAllAppointments();
        });
      }
    });
  }
  
}
