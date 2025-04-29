import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/login.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  signupForm: FormGroup;
  signupError: string;
  isLoading = false;
  passwordMismatch = false;

  constructor(private fb: FormBuilder, private router: Router, private service: AuthService){
    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      username: ['', [Validators.required, Validators.pattern(/^[A-Za-z\s]+$/)]],
      mobileNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      userRole: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(control: AbstractControl) {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');

    if (!password || !confirmPassword) {
      return null;
    }

    if (confirmPassword.errors && !confirmPassword.errors.passwordMismatch) {
      return null;
    }

    if (password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordMismatch: true });
    } else {
      confirmPassword.setErrors(null);
    }

    return null;
  }

  signupUser() {
    if (this.signupForm.valid) {
      this.isLoading = true;
      const loginObj: Login = { username: this.signupForm.value.username, password: this.signupForm.value.password };
      const newUser: User = {
        email: this.signupForm.value.email,
        password: this.signupForm.value.password,
        username: this.signupForm.value.username,
        mobileNumber: this.signupForm.value.mobileNumber,
        userRole: this.signupForm.value.userRole
      };
      console.log(newUser);
      this.service.registerUser(newUser).subscribe(
        (user) => {
          this.router.navigate(['/login']);
        },
        (error) => {
          this.isLoading = false;
          this.signupError = 'Registration failed. Please try again.';
          console.error(error);
        }
      );
    }
  }

  ngOnInit(): void {
  }

}