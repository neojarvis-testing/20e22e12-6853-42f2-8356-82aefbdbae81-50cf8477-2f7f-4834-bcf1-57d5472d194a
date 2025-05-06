import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { AdminaddserviceComponent } from './components/adminaddservice/adminaddservice.component';
import { AdminviewserviceComponent } from './components/adminviewservice/adminviewservice.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { HomeComponent } from './components/home/home.component';
import { AdminviewappointmentComponent } from './components/adminviewappointment/adminviewappointment.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UseraddappointmentComponent } from './components/useraddappointment/useraddappointment.component';
import { UserviewappointmentComponent } from './components/userviewappointment/userviewappointment.component';

import { AdminviewuserdetailsComponent } from './components/adminviewuserdetails/adminviewuserdetails.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ErrorComponent } from './components/error/error.component';
import { AdminGuard } from './admin.guard';
import { UserGuard } from './user.guard';
import { BothGuard } from './both.guard';
import { CouponHistoryComponent } from './components/coupon-history/coupon-history.component';
import { AdminviewcouponsComponent } from './components/adminviewcoupons/adminviewcoupons.component';
 
 
 
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },

  // Admin Routes with AdminGuard
  { path: 'add-service', component: AdminaddserviceComponent, canActivate: [AdminGuard] },
  { path: 'view-services', component: AdminviewserviceComponent, canActivate: [BothGuard] },
  { path: 'edit-service/:id', component: AdminaddserviceComponent, canActivate: [AdminGuard] },
  { path: 'view-all-feedback', component: AdminviewfeedbackComponent, canActivate: [AdminGuard] },
  { path: 'view-appointments', component: AdminviewappointmentComponent, canActivate: [AdminGuard] },
  { path: 'admin-view-userDetails', component: AdminviewuserdetailsComponent, canActivate: [AdminGuard] },
  {path:'admin-view-coupon',component:AdminviewcouponsComponent,canActivate:[AdminGuard]},

  // User Routes with UserGuard
  { path: 'add-appointment', component: UseraddappointmentComponent, canActivate: [UserGuard] },
  { path: 'view-feedback', component: UserviewfeedbackComponent, canActivate: [UserGuard] },
  { path: 'add-feedback', component: UseraddfeedbackComponent, canActivate: [UserGuard] },
  { path: 'view-userappointment', component: UserviewappointmentComponent, canActivate: [UserGuard] },
  { path: 'user-Profile', component: ProfileComponent, canActivate: [BothGuard] },
  {path:'coupon',component:CouponHistoryComponent,canActivate:[UserGuard]},

  // Catch-All Route
  { path: '**', component: ErrorComponent }



];

 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }