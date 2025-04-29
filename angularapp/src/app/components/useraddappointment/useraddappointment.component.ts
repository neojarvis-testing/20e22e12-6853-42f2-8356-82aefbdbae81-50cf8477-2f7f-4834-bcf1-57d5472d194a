// import { Component, OnInit } from '@angular/core';
// import { Appointment } from 'src/app/models/appointment.model';
// import { AppointmentService } from 'src/app/services/appointment.service';
// import { VehicleService } from 'src/app/services/vehicle.service';

// @Component({
//   selector: 'app-useraddappointment',
//   templateUrl: './useraddappointment.component.html',
//   styleUrls: ['./useraddappointment.component.css']
// })
// export class UseraddappointmentComponent implements OnInit {
//   services: any[] = [];
  

//   constructor(private vehicleService: VehicleService, private appointmentService: AppointmentService) {}

//   ngOnInit(): void {
//     this.fetchServices();
//   }

//   fetchServices(): void {
//     this.vehicleService.getAllVehicleService().subscribe(
//       (data) => {
//         this.services = data;
//         console.log(data);
//       },
//       (error) => {
//         console.error('Error fetching services', error);
//       }
//     );
//   }

//   bookAppointment(service: any): void {
//     const userId = localStorage.getItem('userId'); // Get user ID from local storage
//     if (!userId) {
//       alert('User ID not found.');
//       return;
//     }

//     const appointmentData = {
//       serviceId: service.serviceId,
//       appointmentDate: service.appointmentDate,
//       location: service.location,
//       userId: Number(userId)
//     };

//     this.appointmentService.addAppointments(appointmentData).subscribe(
//       (response) => {
//         alert('Appointment booked successfully!');
//         // this.router.navigate(['/appointments']); // Redirect to appointments page
//       },
//       (error) => {
//         console.error('Error booking appointment', error);
//         alert('Failed to book appointment.');
//       }
//     );
//   }
// }

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentService } from 'src/app/services/appointment.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-useraddappointment',
  templateUrl: './useraddappointment.component.html',
  styleUrls: ['./useraddappointment.component.css']
})
export class UserAddAppointmentComponent implements OnInit {
  services: any[] = [];
  appointmentData: { [key: number]: { appointmentDate: string, location: string } } = {};

  constructor(
    private serviceService: VehicleService,
    private appointmentService: AppointmentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllServices();
  }

  getAllServices(): void {
    this.serviceService.getAllVehicleService().subscribe({
      next: (res) => {
        this.services = res;
      },
      error: (err) => {
        console.error('Error fetching services', err);
      }
    });
  }

  bookAppointment(serviceId: number): void {
    const form = this.appointmentData[serviceId];

    if (!form || !form.appointmentDate || !form.location) {
      alert('Please enter both date and location!');
      return;
    }

    const userId = localStorage.getItem('userId');
    const appointment = {
      serviceId: serviceId,
      userId: userId,
      appointmentDate: form.appointmentDate,
      location: form.location,
      status: 'Pending'
    };

    this.appointmentService.addAppointments(appointment).subscribe({
      next: () => {
        alert('Booked successfully!');
        this.router.navigate(['/my-appointments']);
      },
      error: (err) => {
        alert('Booking failed');
        console.error(err);
      }
    });
  }
}
