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
import { ProfileComponent } from './components/profile/profile.component';
import { AdminviewuserdetailsComponent } from './components/adminviewuserdetails/adminviewuserdetails.component';



const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'register',component:RegistrationComponent},
  {path:'login',component:LoginComponent},
  {path:'add-service',component:AdminaddserviceComponent},
  {path:'view-services',component:AdminviewserviceComponent},
  {path:'edit-service/:id',component:AdminaddserviceComponent},
  {path:'view-all-feedback',component:AdminviewfeedbackComponent},
  {path:'view-appointments',component:AdminviewappointmentComponent},
  {path:'add-appointment',component:UseraddappointmentComponent},
  {path:'view-feedback',component:UserviewfeedbackComponent},
  {path:'add-feedback',component:UseraddfeedbackComponent},
  {path:'view-userappointment',component:UserviewappointmentComponent},
  {path:'user-profile',component:ProfileComponent},
  {path:'admin-view-userDetails',component:AdminviewuserdetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
