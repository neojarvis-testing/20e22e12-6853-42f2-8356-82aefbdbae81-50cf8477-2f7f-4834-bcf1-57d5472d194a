import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coupon } from '../models/coupon.model';

@Injectable({
  providedIn: 'root'
})
export class CouponService {
  private baseUrl:string='https://8080-efbdffbfaafacfbfddfefcecfffbcfdda.premiumproject.examly.io/coupons'
  constructor(private http: HttpClient) {}

  createCoupon(userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/create?userId=${userId}`, {});
  }

  getUserCoupons(userId: number): Observable<Coupon[]> {
    return this.http.get<Coupon[]>(`${this.baseUrl}/user/${userId}`);
  }

  getAllUserCoupons():Observable<Coupon[]>{
    return this.http.get<Coupon[]>(`${this.baseUrl}/users`);
  }
}

