import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VehicleMaintenance } from 'src/app/models/vehicle-maintenance.model';
import { VehicleService } from 'src/app/services/vehicle.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-adminaddservice',
  templateUrl: './adminaddservice.component.html',
  styleUrls: ['./adminaddservice.component.css']
})
export class AdminaddserviceComponent implements OnInit {
  
  id: any;
  isEdit: boolean = false;

  newService: VehicleMaintenance = {
    serviceName: '',
    servicePrice: 0,
    typeOfVehicle: '',
    serviceId: 0
  };

  constructor(private vehicleService: VehicleService, private activatedRoute: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.isEdit = true;
      this.vehicleService.getVehicleServiceById(this.id).subscribe((data) => {
        this.newService = data;
      });
    }
  }

  addVehicle(): void {
    if (this.newService.servicePrice < 0 || this.newService.servicePrice > 50000) {
      this.showSweetAlert('❌ Service Price must be between ₹0 and ₹50,000.', 'error');
      return; // Stops submission if validation fails
    }
  
    if (this.isEdit) {
      this.vehicleService.updateVehicleService(this.id, this.newService).subscribe(
        () => {
          this.showSweetAlert('✅ Updated successfully!', 'success');
          this.router.navigate(['/view-services']);
        },
        () => {
          this.showSweetAlert('❌ Update failed. Please try again.', 'error');
        }
      );
    } else {
      this.vehicleService.addVehicleService(this.newService).subscribe(
        () => {
          this.showSweetAlert('✅ Vehicle added successfully!', 'success');
          this.router.navigate(['/view-services']);
        },
        () => {
          this.showSweetAlert('❌ Failed to add vehicle. Please try again.', 'error');
        }
      );
    }
  }

  showSweetAlert(message: string, type: 'success' | 'error') {
    Swal.fire({
      title: type === 'success' ? 'Success' : 'Error',
      text: message,
      icon: type,
      confirmButtonColor: type === 'success' ? '#28a745' : '#dc3545',
      timer: 3000
    });
  }
}
