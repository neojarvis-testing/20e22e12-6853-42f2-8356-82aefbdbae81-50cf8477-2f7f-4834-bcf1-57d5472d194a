import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  signupForm: FormGroup;
  alertMessage: string = '';
  alertType: string = ''; // 'success' or 'danger'
  showAlert: boolean = false;
  isLoading = false;

  constructor(private fb: FormBuilder, private router: Router, private service: AuthService) {
    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), this.passwordValidator]],
      confirmPassword: ['', Validators.required],
      username: ['', [Validators.required, Validators.pattern(/^[A-Za-z\s]+$/), Validators.minLength(3)]],
      mobileNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      userRole: ['USER', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

  signupUser() {
    if (this.signupForm.valid) {
      this.isLoading = true;
      const newUser: User = {
        email: this.signupForm.value.email,
        password: this.signupForm.value.password,
        username: this.signupForm.value.username,
        mobileNumber: this.signupForm.value.mobileNumber,
        userRole: this.signupForm.value.userRole
      };

      this.service.registerUser(newUser).subscribe(
        (user) => {
          this.showBootstrapAlert("✅ Registration successful! Redirecting...", "success");
          setTimeout(() => this.router.navigate(['/login']), 3000);
        },
        (error) => {
          this.isLoading = false;
          this.showBootstrapAlert("❌ Registration failed. Please try again.", "danger");
        }
      );
    }
  }

  showBootstrapAlert(message: string, type: string) {
    this.alertMessage = message;
    this.alertType = type;
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }

  passwordValidator(control: AbstractControl) {
    const password = control.value;
    const regex = /^[A-Za-z\d!@#$%^&*(),.?":{}|<>]{8,}$/;
    
    return regex.test(password) ? null : { invalidPassword: true };
  }

  passwordMatchValidator(control: AbstractControl) {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');

    if (!password || !confirmPassword) return null;

    if (confirmPassword.errors && !confirmPassword.errors.passwordMismatch) return null;

    if (password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordMismatch: true });
    } else {
      confirmPassword.setErrors(null);
    }

    return null;
  }

  ngOnInit(): void {}
}
