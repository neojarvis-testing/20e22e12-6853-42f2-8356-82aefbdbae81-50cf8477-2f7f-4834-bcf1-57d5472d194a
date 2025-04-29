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

  constructor(private http: HttpClient) { }

  registerUser(user: User): Observable<any> {
    return this.http.post<User>(this.apiUrl + '/register', user);
  }

  loginUser(login: Login): Observable<any> {
    return this.http.post<any>(this.apiUrl + '/login', login);
  }
 

  isAdmin(): boolean {
    return sessionStorage.getItem('userRole') === environment.userRoles.admin;
  }

  isUser(): boolean {
    return sessionStorage.getItem('userRole') === environment.userRoles.user;
  }

  isLoggedIn(): boolean {
    return sessionStorage.getItem('token') !== null;
  }

  // logout() {
  //   sessionStorage.clear();
  //   this.router.navigate(['/login']);
  // }
}
