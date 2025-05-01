import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: any = {
    username: '',
    password: ''
  };

  constructor(private loginService: AuthService,private router:Router) { }

  ngOnInit(): void { }

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
    });
  }
}
