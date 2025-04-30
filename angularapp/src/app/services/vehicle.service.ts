import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { VehicleMaintenance } from '../models/vehicle-maintenance.model';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private apiUrl: string = environment.apiUrl;
  
  constructor(private http:HttpClient) { }

  public addVehicleService(vehicleService:VehicleMaintenance):Observable<VehicleMaintenance>{
    return this.http.post<VehicleMaintenance>(`${this.apiUrl}/service`,vehicleService);
  }

  public getAllVehicleService():Observable<VehicleMaintenance[]>{
    return this.http.get<VehicleMaintenance[]>(`${this.apiUrl}/services`);
  }

  public getVehicleServiceById(id:number):Observable<VehicleMaintenance>{
    return this.http.get<VehicleMaintenance>(`${this.apiUrl}/services/${id}`);
  }

  public updateVehicleService(id:number,vehicleService:VehicleMaintenance):Observable<VehicleMaintenance>{
    return this.http.put<VehicleMaintenance>(`${this.apiUrl}/service/${id}`,vehicleService);
  }

  public deleteVehicleService(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/services/${id}`)
  }

}





