package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Coupon;
import com.examly.springapp.service.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/create")
    public ResponseEntity<Coupon> createCoupon(@RequestParam Long userId) {
        return ResponseEntity.ok(couponService.createCoupon(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Coupon>> getUserCoupons(@PathVariable Long userId) {
        return ResponseEntity.ok(couponService.getUserCoupons(userId));
    }

    @PutMapping("/expire/{couponId}")
    public ResponseEntity<Coupon> expireCoupon(@PathVariable Long couponId) {
        return ResponseEntity.ok(couponService.expireCoupon(couponId));
    }
    @GetMapping("users")
    public ResponseEntity<?> getAllUserCoupons() {
        List<Coupon> list=couponService.getAllUserCoupons();
        return ResponseEntity.status(200).body(list);
    }
}