export class Coupon {
    id!: number;
    userId!: number;
    couponCode!: string;
    status!: 'active' | 'expired';
    activationDate!: Date;
    expirationDate!: Date;
  }