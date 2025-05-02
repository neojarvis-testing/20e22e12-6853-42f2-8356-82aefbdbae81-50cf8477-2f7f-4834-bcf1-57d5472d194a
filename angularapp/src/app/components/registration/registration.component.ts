import { Component} from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent{
  signupForm: FormGroup;
  signupError: string;
  isLoading = false;
  passwordMismatch = false;
  showSuccessPopup = false;

  constructor(private readonly fb: FormBuilder, private readonly router: Router, private readonly service: AuthService) {
    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), this.passwordValidator]],
      confirmPassword: ['', Validators.required],
      username: ['', [Validators.required, Validators.pattern(/^[A-Za-z\s]+$/), Validators.minLength(3)]],
      mobileNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      userRole: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

  closePopup() {
    this.showSuccessPopup = false;
    // Optionally redirect to login:
    // this.router.navigate(['/login']);
  }

  passwordValidator(control: AbstractControl) {
    const password = control.value;
    const regex = /^[A-Za-z\d!@#$%^&*(),.?":{}|<>]{8,}$/;

    return regex.test(password) ? null : { invalidPassword: true };
  }

  passwordMatchValidator(control: AbstractControl) {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');

    // Check for null control references
    if (!password || !confirmPassword) {
      return { invalidMatch: true };
    }

    // Validate password mismatch
    if (password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordMismatch: true });
      return { passwordsDoNotMatch: true }; // Return distinct value for mismatch
    } else {
      confirmPassword.setErrors(null);
      return null; // Match is valid
    }
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

}