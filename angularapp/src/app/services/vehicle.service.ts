import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VehicleMaintenance } from '../models/vehicle-maintenance.model';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  //private apiUrl:string=environment.apiUrl;

  constructor(private http:HttpClient) { }
  // getAllServices():Observable<VehicleMaintenance[]>{
  //   return this.http.get<VehicleMaintenance[]>(this.apiUrl+'/vehicleMaintenance')
  // }
  // addService(service:VehicleMaintenance):Observable<VehicleMaintenance>{
  //   return this.http.post<VehicleMaintenance>(this.apiUrl+'/vehicleMaintenance',service)
  // }
  // updateService(serviceId:number,updatedService:VehicleMaintenance):Observable<VehicleMaintenance>{
  //   return this.http.put<VehicleMaintenance>(`${this.apiUrl}/${serviceId}`,updatedService)
  // }
  // deleteService(serviceId:number):Observable<void>{
  //   return this.http.delete<void>(this.apiUrl+'/vehicleMaintenance'+serviceId)
  // }
  // getServiceByName(serviceName:string):Observable<VehicleMaintenance>{
  //   return this.http.get<VehicleMaintenance>(`${this.apiUrl}/${serviceName}`)
  // }

}
