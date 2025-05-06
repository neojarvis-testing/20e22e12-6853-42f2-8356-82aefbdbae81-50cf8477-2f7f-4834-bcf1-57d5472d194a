// import { Component,OnInit} from '@angular/core';
// import { CouponService } from 'src/app/services/coupon.service';

// @Component({
//   selector: 'app-coupon-history',
//   templateUrl: './coupon-history.component.html',
//   styleUrls: ['./coupon-history.component.css']
// })
// export class CouponHistoryComponent implements OnInit {
//   coupons: any[] = [];
//   userId: number = 1; // Replace this with actual user ID

//   constructor(private couponService: CouponService) {}

//   ngOnInit(): void {
//     this.loadCoupons();
//   }

//   loadCoupons(): void {
//     this.userId=+localStorage.getItem('userId')
//     this.couponService.getUserCoupons(this.userId).subscribe(
//       (data) => {
//         this.coupons = data;
//         console.log(data)
//       },
//       (error) => {
//         console.error('Error fetching coupons', error);
//       }
//     );
//   }
// }




import { Component,OnInit} from '@angular/core';
import { CouponService } from 'src/app/services/coupon.service';

@Component({
  selector: 'app-coupon-history',
  templateUrl: './coupon-history.component.html',
  styleUrls: ['./coupon-history.component.css']
})
export class CouponHistoryComponent implements OnInit {
  coupons: any[] = [];
  filteredCoupons: any[] = [];
  filterType: string = 'all'; // Default view
  userId: number;

  constructor(private couponService: CouponService) {}

  ngOnInit(): void {
    this.loadCoupons();
  }

  loadCoupons(): void {
    this.userId=+localStorage.getItem('userId')
    this.couponService.getUserCoupons(this.userId).subscribe(
      (data) => {
        this.coupons = data;
        this.filterCoupons(this.filterType); // Apply filter after fetching
      },
      (error) => {
        console.error('Error fetching coupons', error);
      }
    );
  }

  filterCoupons(type: string): void {
    this.filterType = type;
    if (type === 'all') {
      this.filteredCoupons = this.coupons;
    } else {
      this.filteredCoupons = this.coupons.filter(coupon => coupon.status === type);
    }
  }
}
