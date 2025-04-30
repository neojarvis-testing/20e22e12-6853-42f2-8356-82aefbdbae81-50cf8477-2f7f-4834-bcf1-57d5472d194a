import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VehicleMaintenance } from 'src/app/models/vehicle-maintenance.model';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-adminaddservice',
  templateUrl: './adminaddservice.component.html',
  styleUrls: ['./adminaddservice.component.css']
})
export class AdminaddserviceComponent implements OnInit {
  
  id:any
  isEdit:boolean=false;
  newService:VehicleMaintenance={
    serviceName: '',
    servicePrice: 0,
    typeOfVehicle: '',
    serviceId: 0
  }

  constructor(private vehicleService:VehicleService,private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.activatedRoute.snapshot.paramMap.get('id');
    if(this.id){
      this.isEdit=true;
      this.vehicleService.getVehicleServiceById(this.id).subscribe((data)=>{
        this.newService=data;
      })
    }
  }

  addVehicle():void{
    if(this.isEdit){
      this.vehicleService.updateVehicleService(this.id,this.newService).subscribe((data)=>{
        alert('updated successfully');
      })
    }
    else{
      this.vehicleService.addVehicleService(this.newService).subscribe((data)=>{
        alert("vehicle added successfully");
      })
    }
  }


}