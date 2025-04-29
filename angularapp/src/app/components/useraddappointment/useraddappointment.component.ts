import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppointmentService } from 'src/app/services/appointment.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-useraddappointment',
  templateUrl: './useraddappointment.component.html',
  styleUrls: ['./useraddappointment.component.css']
})
export class UseraddappointmentComponent implements OnInit {

  // appointmentForm: FormGroup;
  // services: any[] = [];
  // selectedService: any = null;
  // isFormValid = false;

  // constructor(
  //   private fb: FormBuilder,
  //   private appointmentService: AppointmentService,
  //   private vehicleService:VehicleService,
  //   private router: Router
  // ) {
  //   this.appointmentForm = this.fb.group({
  //     serviceId: ['', Validators.required],
  //     appointmentDate: ['', Validators.required],
  //     location: ['', Validators.required],
  //   });

  //   this.appointmentForm.valueChanges.subscribe(() => {
  //     this.isFormValid = this.appointmentForm.valid;
  //   });
  // }

  ngOnInit() {
    // this.fetchServices();
  }

  // fetchServices() {
  //   this.vehicleService.getAllVehicleService().subscribe((data) => {
  //     this.services = data;
  //   });
  // }

  // selectService(service: any) {
  //   this.selectedService = service;
  //   this.appointmentForm.patchValue({ serviceId: service.serviceId });
  // }

  // submitAppointment() {
  //   if (this.appointmentForm.valid) {
  //     this.appointmentService.addAppointment(this.appointmentForm.value).subscribe({
  //       next: () => {
  //         alert('Appointment added successfully!');
  //         this.router.navigate(['/my-appointments']);
  //       },
  //       error: () => alert('Error adding appointment'),
  //     });
  //   }
  // }
}