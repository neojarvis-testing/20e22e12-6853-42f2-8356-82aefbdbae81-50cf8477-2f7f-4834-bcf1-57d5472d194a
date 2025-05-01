import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: any = { username: '', password: '' };
  alertMessage: string = '';
  alertType: string = ''; // 'success' or 'danger'
  showAlert: boolean = false;

  constructor(private loginService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  loginUser(): void {
    this.loginService.loginUser(this.login).subscribe(
      (data) => {
        this.showBootstrapAlert("Login successful!", "success");
        console.log(data);
        localStorage.setItem('token', data.token);
        localStorage.setItem('userId', data.userId);
        localStorage.setItem('userRole', data.userRole);
        this.router.navigate(['/']);
      },
      (error) => {
        this.showBootstrapAlert("Login unsuccessful! Please check your credentials.", "danger");
      }
    );
  }

  showBootstrapAlert(message: string, type: string) {
    this.alertMessage = message;
    this.alertType = type;
    this.showAlert = true;
    
    setTimeout(() => {
      this.showAlert = false;
    }, 3000); // Hide alert after 3 seconds
  }
}

