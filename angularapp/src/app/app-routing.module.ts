import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { AdminaddserviceComponent } from './components/adminaddservice/adminaddservice.component';
import { AdminviewserviceComponent } from './components/adminviewservice/adminviewservice.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
 
const routes: Routes = [
  {path:'register',component:RegistrationComponent},
  {path:'login',component:LoginComponent},
  {path:'add-service',component:AdminaddserviceComponent},
  {path:'view-service',component:AdminviewserviceComponent},
  {path:'edit-service/:id',component:AdminaddserviceComponent},
  {path:'add-feedback',component:UseraddfeedbackComponent},
  {path:'view-feedback-user',component:UserviewfeedbackComponent},
  {path:'view-feedback-admin',component:AdminviewfeedbackComponent}
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }