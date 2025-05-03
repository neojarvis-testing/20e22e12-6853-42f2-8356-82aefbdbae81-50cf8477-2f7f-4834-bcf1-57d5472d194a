
import { Component, OnInit } from '@angular/core';

import { AuthService } from 'src/app/services/auth.service';

import Swal from 'sweetalert2'; // Import SweetAlert2




@Component({

  selector: 'app-profile',

  templateUrl: './profile.component.html'

})


export class ProfileComponent implements OnInit {

  user: any = {};

  showEditForm: boolean = false;

  constructor(private readonly authService: AuthService) {}


  ngOnInit(): void {
  
    const userId = +localStorage.getItem('userId');
  
    this.authService.getUserById(userId).subscribe((data) => {
  
      this.user = data;
  
      console.log(data);
  
    });
  
  }


  
  toggleEditForm(): void {
    this.showEditForm = !this.showEditForm;
  
  }


  updateProfile(): void {
  
    this.authService.updateUser(this.user.id, this.user).subscribe(
  
      () => {
  
        this.showEditForm = false;
  
        Swal.fire({
  
          icon: 'success',
  
          title: 'Profile Updated!',
  
  
          text: 'Your profile has been updated successfully.',
  
          showConfirmButton: false,
  
  
          timer: 2000 // Auto-close after 2 seconds
  
        });
        this.ngOnInit(); // Refresh user details
  
      },
  
      (error) => {
  
        Swal.fire({
  
  
          icon: 'error',
  
          title: 'Oops!',
  
          text: 'Error updating profile: ' + error.message,
  
        });
  
      }
  
      );
  }
}




