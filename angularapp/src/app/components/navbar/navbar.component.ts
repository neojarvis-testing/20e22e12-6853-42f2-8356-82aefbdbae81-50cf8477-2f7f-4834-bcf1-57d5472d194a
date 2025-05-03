import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
    
    selector: 'app-navbar',
    
    templateUrl: './navbar.component.html'

})

export class NavbarComponent implements OnInit {

    showLogoutPopup: boolean = false;

    isLoggedIn: boolean = false;

    userRole: string | null = null;

    userName: string | null = null;
 
    constructor(private readonly authService: AuthService, private readonly router: Router) {}
 
    ngOnInit(): void {

        this.isLoggedIn = this.authService.isLoggedIn();

        this.userRole = localStorage.getItem('userRole');

        this.userName = localStorage.getItem('userName');

    }
 
    logout() {

        this.authService.logout(); // Call AuthService's logout method

        this.isLoggedIn = false;

        this.router.navigate(['/login']);

    }
    
    
}