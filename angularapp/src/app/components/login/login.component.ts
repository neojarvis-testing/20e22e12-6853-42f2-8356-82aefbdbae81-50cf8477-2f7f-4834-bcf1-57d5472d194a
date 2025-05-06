import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { CouponService } from 'src/app/services/coupon.service';
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

  constructor(private loginService: AuthService, private router: Router,private appointmentService:AppointmentService,private couponService:CouponService) {}

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
        this.checkLastAppointment(data.userId);
        this.checkCouponEligibility(data.userId);
        console.log(this.checkCouponEligibility(data.userId))
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

  checkLastAppointment(userId: number): void {
    this.appointmentService.getLastAppointmentByUserId(userId).subscribe(
      (appointment) => {
        if (appointment && appointment.appointmentDate) {
          const lastAppointmentDate = new Date(appointment.appointmentDate);
          const currentDate = new Date();
          const differenceInDays = Math.floor((currentDate.getTime() - lastAppointmentDate.getTime()) / (1000 * 60 * 60 * 24));
  
          if (differenceInDays > 40) {
            Swal.fire({
              icon: 'warning',
              title: 'Appointment Reminder',
              text: `It has been ${differenceInDays} days since your last appointment. Consider scheduling a new one.`,
              confirmButtonText: 'OK'
            })
            .then((result) => {
              if (result.isConfirmed) {
                this.router.navigate(['/add-appointment']);
              }
            });
          }
        }
      },
      (error) => {
        console.error('Error fetching last appointment', error);
      }
    );
  }
  
  checkCouponEligibility(userId: number): void {
    this.appointmentService.getAppointmentsByUserId(userId).subscribe(
      (appointments) => {
        if (appointments.length > 3) {
          const lastAppointmentDate = new Date(appointments[appointments.length - 1].appointmentDate);
          const currentDate = new Date();
          const differenceInDays = Math.floor((currentDate.getTime() - lastAppointmentDate.getTime()) / (1000 * 60 * 60 * 24));
  
          if (differenceInDays > 20) {
            this.couponService.createCoupon(userId).subscribe((coupon) => {
              Swal.fire({
                icon: 'success',
                title: 'Congratulations!',
                html: `<p>You have won a coupon! 🎉</p>
                        <img src="assets/scratch.gif" alt="Coupon GIF" width="200"/>
                       <p>Coupon Code: <strong>${coupon.couponCode}</strong></p>
                       <p>Expires on: <strong>${coupon.expirationDate}</strong></p>
                       <p><strong>Please Note:</strong> All credit and debit cards are acceptable. Extra 2% charges are applicable for cards.</p>`,
                confirmButtonText: 'OK'
              }).then((result) => {
                if (result.isConfirmed) {
                  this.router.navigate(['/coupon']);
                }
              });
            });
          }          
        }
      },
      (error) => {
        console.error('Error fetching appointments', error);
      }
    );
  }
  // checkCouponEligibility(userId: number): void {
  //   this.appointmentService.getAppointmentsByUserId(userId).subscribe(
  //     (appointments) => {
  //       if (appointments.length > 3) {
  //         const lastAppointmentDate = new Date(appointments[appointments.length - 1].appointmentDate);
  //         const currentDate = new Date();
  //         const differenceInDays = Math.floor((currentDate.getTime() - lastAppointmentDate.getTime()) / (1000 * 60 * 60 * 24));
  
  //         if (differenceInDays > 20) {
  //           Swal.fire({
  //             icon: 'success',
  //             title: 'Congratulations!',
  //             html: `<p>You have won a coupon! 🎉</p>
  //                    <img src="assets/scratch.gif" alt="Coupon GIF" width="200"/>
  //                    <p><strong>Please Note:</strong> All credit and debit cards are acceptable. Extra 2% charges are applicable for cards.</p>`,
  //             confirmButtonText: 'OK'
  //           }).then((result) => {
  //             if (result.isConfirmed) {
  //               this.router.navigate(['/add-appointment']);
  //             }
  //           });
  //         }
  //       }
  //     },
  //     (error) => {
  //       console.error('Error fetching appointments', error);
  //     }
  //   );
  // }
}

