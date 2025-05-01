import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentService } from 'src/app/services/appointment.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-useraddappointment',
  templateUrl: './useraddappointment.component.html'
})
export class UseraddappointmentComponent implements OnInit {
  services: any[] = [];
  

  constructor(private readonly vehicleService: VehicleService, private readonly appointmentService: AppointmentService,private readonly router:Router) {}

  ngOnInit(): void {
    this.fetchServices();
  }

  fetchServices(): void {
    this.vehicleService.getAllVehicleService().subscribe(
      (data) => {
        this.services = data;
        console.log(data);
      },
      (error) => {
        console.error('Error fetching services', error);
      }
    );
  }

  bookAppointment(service: any): void {
    const userId = localStorage.getItem('userId'); // Get user ID from local storage
    if (!userId) {
      alert('User ID not found.');
      return;
    }

    const appointmentData = {
      serviceId: service.id,
      appointmentDate: service.appointmentDate,
      location: service.location,
      status:'PENDING',
      userId: userId
    };

    console.log(appointmentData)

    this.appointmentService.addAppointments(appointmentData).subscribe(
      (response) => {
        alert('Appointment booked successfully!');
        this.router.navigate(['/view-userappointment']); // Redirect to appointments page
      },
      (error) => {
        console.error('Error booking appointment', error);
        alert('Failed to book appointment.');
      }
    );
  }
}
