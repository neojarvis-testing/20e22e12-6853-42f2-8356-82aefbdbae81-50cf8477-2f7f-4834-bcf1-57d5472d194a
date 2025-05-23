import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
 
  private apiUrl: string = environment.apiUrl;
 
  constructor(private http: HttpClient, private router: Router) { }
 
  makeLog(msg: string): Observable<any> {
    let obj={
      'msg': msg
    }
    return this.http.post<User>(this.apiUrl + '/logs', obj);
  }

  registerUser(user: User): Observable<any> {
    return this.http.post<User>(this.apiUrl + '/registers', user);
  }
 
  loginUser(login: Login): Observable<any> {
    return this.http.post<any>(this.apiUrl + '/login', login);
  }
 
 
  isAdmin(): boolean {
    return localStorage.getItem('userRole') === environment.userRoles.admin;
  }
 
  isUser(): boolean {
    return localStorage.getItem('userRole') === environment.userRoles.user;
  }
 
  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
 
 
  logout() {
    localStorage.clear();
 
    //this.router.navigate(['/login']);
 
    this.router.navigate(['/login']);
 
  }
 
 
   getAuthenticatedToken(): string | null {
       return localStorage.getItem('token');
 
      }
 
      getUserById(userId: number): Observable<any> {
        return this.http.get(`${this.apiUrl}/user/${userId}`);
      }
   
      updateUser(userId: number, userData: any): Observable<any> {
        return this.http.put(`${this.apiUrl}/user/view/profile/${userId}`, userData);
      }
 
 
   }
 

   

 