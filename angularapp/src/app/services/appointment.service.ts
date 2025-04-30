import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment } from '../models/appointment.model';
import { environment } from 'src/environments/environment';
 
@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  private apiUrl: string = environment.apiUrl;
 
  constructor(private http: HttpClient) {}
 
 
  // Add Appointment
  addAppointments(appointmentData:any): Observable<Appointment> {
    return this.http.post<Appointment>(`${this.apiUrl}/appointments`, appointmentData);
  }
 
  // Get Appointments by User ID
  getAppointmentsByUserId(userId: number): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.apiUrl}/appointment/user/${userId}`);
  }
 
  // Get Appointments by User (Alternate)
  getAppointmentsByUser(userId: number): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.apiUrl}/appointment/${userId}`);
  }
 
  // Get Last Appointment by User ID
  getLastAppointmentByUserId(userId: number): Observable<Appointment> {
    return this.http.get<Appointment>(`${this.apiUrl}/appointment/user/${userId}/last`);
  }
 
  // Get All Appointments
  getAllAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.apiUrl}/appointments`);
  }
 
 
  // Update Appointment
  updateAppointments(appointmentId: number, appointmentData: any): Observable<Appointment> {
    return this.http.put<Appointment>(`${this.apiUrl}/appointments/${appointmentId}`, appointmentData);
  }
 
  // Delete Appointment
  deleteAppointment(appointmentId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/appointment/${appointmentId}`);
  }
 
  // Get Sorted Appointments (With Pagination)
  getAppointmentsWithPagingAndSorting(
    pageNo: number = 0,
    pageSize: number = 10,
    sortBy: string = 'appointmentDate',
    sortDir: string = 'asc'
  ): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.apiUrl}/appointments?pageNo=${pageNo}&pageSize=${pageSize}&sortBy=${sortBy}&sortDir=${sortDir}`);
  }
 
  getAppointmentById(id:number):Observable<Appointment>{
    return this.http.get<Appointment>(`${this.apiUrl}/appointments/${id}`)
  }
}
 