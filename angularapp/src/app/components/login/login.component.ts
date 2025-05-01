import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';
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
        Swal.fire({
          icon: 'success',
          title: 'Login Successful!',
          text: 'Redirecting...',
          showConfirmButton: false,
          timer: 2000
        });
  
        console.log(data);
        localStorage.setItem('token', data.token);
        localStorage.setItem('userId', data.userId);
        localStorage.setItem('userRole', data.userRole);
        this.router.navigate(['/']);
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Login Unsuccessful!',
          text: 'Please check your credentials.',
          confirmButtonText: 'Try Again'
        });
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

