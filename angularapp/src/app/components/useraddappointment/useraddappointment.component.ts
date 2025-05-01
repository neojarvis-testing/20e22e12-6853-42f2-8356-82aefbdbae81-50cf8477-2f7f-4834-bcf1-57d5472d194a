import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'; // Import SweetAlert
import { AppointmentService } from 'src/app/services/appointment.service';
import { VehicleService } from 'src/app/services/vehicle.service';
 
@Component({
  selector: 'app-useraddappointment',
  templateUrl: './useraddappointment.component.html',
  styleUrls: ['./useraddappointment.component.css']
})
export class UseraddappointmentComponent implements OnInit {
  services: any[] = [];
  locations: string[] = ["Bangalore","Tirupati", "Hyderabad", "Chennai","Pune","Punjab","Noida"]; // Location options
 
  constructor(private vehicleService: VehicleService, private appointmentService: AppointmentService, private router: Router) {}
 
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
      Swal.fire('Error', 'User ID not found!', 'error');
      return;
    }
 
    // Validate appointment date (should not be past date)
    const selectedDate = new Date(service.appointmentDate);
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Reset time to compare correctly
 
    if (selectedDate < today) {
      Swal.fire('Failed', 'Appointment date cannot be in the past.', 'error');
      return;
    }
 
    const appointmentData = {
      serviceId: service.id,
      appointmentDate: service.appointmentDate,
      location: service.location,
      status: 'PENDING',
      userId: userId
    };
 
    this.appointmentService.addAppointments(appointmentData).subscribe(
      (response) => {
        Swal.fire({
          title: 'Appointment Booked!',
          text: 'We will notify you within a few minutes.',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          this.router.navigate(['/view-userappointment']); // Redirect after booking
        });
      },
      (error) => {
        Swal.fire('Failed', 'Error booking appointment. Please try again!', 'error');
      }
    );
  }
}