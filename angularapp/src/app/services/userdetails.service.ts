import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserdetailsService {
  private apiUrl: string = environment.apiUrl;
  constructor(private http:HttpClient) { }

  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(this.apiUrl+'/users');
  }

  deleteUser(userId:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/users/${userId}`)
  }

  getUserByName(name:string):Observable<User>{
    return this.http.get<User>(`${this.apiUrl}/users`)
  }
  updateUser(userId:number,UpdateUser:User):Observable<User>{
    return this.http.put<User>(`${this.apiUrl}/users/${userId}`,UpdateUser)
  }

}
