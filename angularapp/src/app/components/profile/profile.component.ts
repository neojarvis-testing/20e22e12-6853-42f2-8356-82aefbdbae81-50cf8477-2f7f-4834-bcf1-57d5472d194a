// import { Component, OnInit } from '@angular/core';
// import { AuthService } from 'src/app/services/auth.service';
// import Swal from 'sweetalert2';

// @Component({
//   selector: 'app-profile',
//   templateUrl: './profile.component.html',
//   styleUrls: ['./profile.component.css'],
// })
// export class ProfileComponent implements OnInit {
//   user: any = {};
//   showEditForm: boolean = false;

//   constructor(private authService: AuthService) {}

//   ngOnInit(): void {
//     const userId = +localStorage.getItem('userId');
//     this.authService.getUserById(userId).subscribe((data) => {
//       this.user = data;
//       this.user.profileImage = localStorage.getItem('profileImage') || ''; // Retrieve stored image
//     });
//   }

//   toggleEditForm(): void {
//     this.showEditForm = !this.showEditForm;
//   }

//   updateProfile(): void {
//     this.authService.updateUser(this.user.id, this.user).subscribe(() => {
//       this.showEditForm = false;
//       this.ngOnInit(); // Refresh user details
//       Swal.fire({
//         icon: 'success',
//         title: 'Profile Updated!',
//         text: 'Your profile has been updated successfully.',
//         showConfirmButton: false,
//         timer: 2000
//       });
//     });
//   }

//   onFileSelected(event: any): void {
//     const file = event.target.files[0];
//     if (file) {
//       const reader = new FileReader();
//       reader.onload = (e: any) => {
//         this.user.profileImage = e.target.result;
//         localStorage.setItem('profileImage', this.user.profileImage); // Save to local storage
//         console.log("Profile Image Saved:", this.user.profileImage);
//       };
//       reader.readAsDataURL(file);
//     }
//   }
// }

import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: any = {};
  showEditForm: boolean = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    const userId = +localStorage.getItem('userId');
    this.authService.getUserById(userId).subscribe((data) => {
      this.user = data;
    });
  }

  toggleEditForm(): void {
    this.showEditForm = !this.showEditForm;
  }

  updateProfile(): void {
    this.authService.updateUser(this.user.id, this.user).subscribe(() => {
      this.showEditForm = false;
      this.ngOnInit(); // Refresh user details
      Swal.fire({
        icon: 'success',
        title: 'Profile Updated!',
        text: 'Your profile has been updated successfully.',
        showConfirmButton: false,
        timer: 2000
      });
    });
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.user.profileImage = e.target.result.split(',')[1]; // Extract Base64 content
        console.log(this.user.profileImage)

        // Send updated image to the backend immediately
        this.authService.updateUser(this.user.id, this.user).subscribe(() => {
          console.log("Profile Image Updated!");
        });
      };
      reader.readAsDataURL(file);
    }
  }
}