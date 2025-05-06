// import { Component, OnInit } from '@angular/core';
// import { Coupon } from 'src/app/models/coupon.model';
// import { CouponService } from 'src/app/services/coupon.service';

// @Component({
//   selector: 'app-adminviewcoupons',
//   templateUrl: './adminviewcoupons.component.html',
//   styleUrls: ['./adminviewcoupons.component.css']
// })
// export class AdminviewcouponsComponent implements OnInit {

//   coupons:Coupon[]=[]
//   constructor(private coupon:CouponService) { }

//   ngOnInit(): void {
//     this.loadAllCoupons()
//   }

//   loadAllCoupons(){
//     this.coupon.getAllUserCoupons().subscribe((data)=>{
//       this.coupons=data
//     })
//   }
// }

import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CouponService } from 'src/app/services/coupon.service';

@Component({
  selector: 'app-adminviewcoupons',
  templateUrl: './adminviewcoupons.component.html',
  styleUrls: ['./adminviewcoupons.component.css']
})
export class AdminviewcouponsComponent implements OnInit {
  coupons: Coupon[] = [];
  filteredCoupons: Coupon[] = [];
  searchUserId: string = '';

  constructor(private couponService: CouponService) {}

  ngOnInit(): void {
    this.loadAllCoupons();
  }

  loadAllCoupons() {
    this.couponService.getAllUserCoupons().subscribe((data) => {
      this.coupons = data;
      this.filteredCoupons = data; // Default view shows all coupons
    });
  }

  filterByUserId() {
    if (this.searchUserId.trim()) {
      this.filteredCoupons = this.coupons.filter(coupon => coupon.userId.toString().includes(this.searchUserId));
    } else {
      this.filteredCoupons = this.coupons;
    }
  }
}

