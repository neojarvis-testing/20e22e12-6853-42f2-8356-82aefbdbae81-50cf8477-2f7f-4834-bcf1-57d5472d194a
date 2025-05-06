import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AppointmentService } from 'src/app/services/appointment.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-useraddappointment',
  templateUrl: './useraddappointment.component.html',
  styleUrls: ['./useraddappointment.component.css']
})
export class UseraddappointmentComponent implements OnInit {
  services: any[] = [];
  filteredServices: any[] = [];
  locations: string[] = ["Bangalore", "Tirupati", "Hyderabad", "Chennai", "Pune", "Punjab", "Noida"];
  searchTerm: string = '';
  sortOrder: string = '';

  constructor(private vehicleService: VehicleService, private appointmentService: AppointmentService, private router: Router) {}

  ngOnInit(): void {
    this.fetchServices();
  }

  fetchServices(): void {
    this.vehicleService.getAllVehicleService().subscribe(
      (data) => {
        this.services = data;
        this.filteredServices = [...this.services]; // Initialize filtered data
      },
      (error) => {
        console.error('Error fetching services', error);
      }
    );
  }

  filterServices(): void {
    this.filteredServices = this.services.filter(service =>
      service.serviceName.toLowerCase().includes(this.searchTerm.toLowerCase())
    );

    if (this.sortOrder === 'asc') {
      this.filteredServices.sort((a, b) => a.servicePrice - b.servicePrice);
    } else if (this.sortOrder === 'desc') {
      this.filteredServices.sort((a, b) => b.servicePrice - a.servicePrice);
    }
  }

  bookAppointment(service: any): void {
    const userId = localStorage.getItem('userId');
    if (!userId) {
      Swal.fire('Error', 'User ID not found!', 'error');
      return;
    }

    const selectedDate = new Date(service.appointmentDate);
    const today = new Date();
    today.setHours(0, 0, 0, 0);

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
      () => {
        Swal.fire({
          title: 'Appointment Booked!',
          text: 'We will notify you within a few minutes.',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          this.router.navigate(['/view-userappointment']);
        });
      },
      () => {
        Swal.fire('Failed', 'Error booking appointment. Please try again!', 'error');
      }
    );
  }
}