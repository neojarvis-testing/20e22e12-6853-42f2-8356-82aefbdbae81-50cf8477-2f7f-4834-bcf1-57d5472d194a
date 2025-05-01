import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import { AuthService } from './services/auth.service';
 
@Injectable({
  providedIn: 'root'
})
export class BothGuard implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    return this.service.isUser() || this.service.isAdmin();
  }
 
  constructor(private readonly service:AuthService){}
 
}