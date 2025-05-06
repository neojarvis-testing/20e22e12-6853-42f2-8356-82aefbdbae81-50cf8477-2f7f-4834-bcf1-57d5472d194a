import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleMaintenance } from 'src/app/models/vehicle-maintenance.model';
import { VehicleService } from 'src/app/services/vehicle.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-adminviewservice',
  templateUrl: './adminviewservice.component.html',
  styleUrls: ['./adminviewservice.component.css']
})
export class AdminviewserviceComponent implements OnInit {

  services: VehicleMaintenance[] = [];
  filteredServices: VehicleMaintenance[] = [];
  searchTerm: string = '';
  minPrice: number | null = null;
  maxPrice: number | null = null;

  constructor(private vehicleService: VehicleService, private router: Router) { }

  ngOnInit(): void {
    this.getAllVehicleServices();
  }

  getAllVehicleServices(): void {
    this.vehicleService.getAllVehicleService().subscribe((data) => {
      console.log(data);
      this.services = data;
      this.filteredServices = data;
    });
  }

  filterServices(): void {
    this.filteredServices = this.services.filter(service =>
      service.serviceName.toLowerCase().includes(this.searchTerm.toLowerCase()) &&
      (this.minPrice === null || service.servicePrice >= this.minPrice) &&
      (this.maxPrice === null || service.servicePrice <= this.maxPrice)
    );
  }

  delete(id: number): void {
    Swal.fire({
      title: 'Are you sure?',
      text: "This action cannot be undone!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#6c757d',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.vehicleService.deleteVehicleService(id).subscribe(() => {
          Swal.fire({
            title: 'Deleted!',
            text: 'Service deleted successfully.',
            icon: 'success',
            timer: 3000
          });
          this.getAllVehicleServices();
        });
      }
    });
  }

  edit(id: number): void {
    this.router.navigate(['/edit-service', id]);
  }
}