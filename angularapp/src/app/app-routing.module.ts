import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { AdminaddserviceComponent } from './components/adminaddservice/adminaddservice.component';
import { AdminviewserviceComponent } from './components/adminviewservice/adminviewservice.component';

const routes: Routes = [
  {path:'register',component:RegistrationComponent},
  {path:'login',component:LoginComponent},
  {path:'add-service',component:AdminaddserviceComponent},
  {path:'view-services',component:AdminviewserviceComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
