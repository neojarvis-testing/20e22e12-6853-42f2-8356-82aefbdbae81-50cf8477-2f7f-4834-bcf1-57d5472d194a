import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleMaintenance } from 'src/app/models/vehicle-maintenance.model';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-adminviewservice',
  templateUrl: './adminviewservice.component.html',
  styleUrls: ['./adminviewservice.component.css']
})
export class AdminviewserviceComponent implements OnInit {

  services:VehicleMaintenance[]=[]

  constructor(private vehicleService:VehicleService,private router:Router) { }

  ngOnInit(): void {
    this.getAllVehicleServices();
  }

  getAllVehicleServices():void{
    this.vehicleService.getAllVehicleService().subscribe((data)=>{
      console.log(data)
      this.services=data;
    })
  }

  delete(id:number):void{
    this.vehicleService.deleteVehicleService(id).subscribe((data)=>{
      alert('deleted successfully');
      this.getAllVehicleServices();
    })
  }

  edit(index:number):void{
    this.router.navigate(['/edit-service',index]);
  }
}
