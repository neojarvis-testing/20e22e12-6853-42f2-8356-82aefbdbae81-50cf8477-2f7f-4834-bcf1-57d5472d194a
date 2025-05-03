import { Component} from '@angular/core';

import { Router } from '@angular/router';

import { AuthService } from 'src/app/services/auth.service';

@Component({

  selector: 'app-login',

  templateUrl: './login.component.html',

  styleUrls: ['./login.component.css']

})

export class LoginComponent{


  login: any = {
  
    username: '',
  
    password: ''
  
  };


  
  constructor(private readonly loginService: AuthService,private readonly router:Router) { }

  
  loginUser(): void {
  
    this.loginService.loginUser(this.login).subscribe((data) => {
  
      alert("Login successful");
  
      console.log(data);
  
      localStorage.setItem('token', data.token);
  
      localStorage.setItem('userId', data.userId);
  
      localStorage.setItem('userRole', data.userRole);
  
      this.router.navigate(['/'])
  
    }, (error) => {
  
      alert("Login unsuccessful");
  
      console.log(error);
  
    });
  
  }

}
