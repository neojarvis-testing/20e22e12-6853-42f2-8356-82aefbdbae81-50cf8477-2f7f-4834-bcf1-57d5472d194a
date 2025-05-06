package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Coupon;
import com.examly.springapp.repository.CouponRepository;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    public Coupon createCoupon(Long userId) {
    // Check if an active coupon already exists for the user
    List<Coupon> existingCoupons = couponRepository.findByUserId(userId)
            .stream()
            .filter(coupon -> "active".equals(coupon.getStatus()))
            .collect(Collectors.toList());

    if (!existingCoupons.isEmpty()) {
        // Return the existing coupon instead of creating a new one
        return existingCoupons.get(0);
    }
    
    // If no active coupon exists, create a new one
    Coupon coupon = new Coupon();
    coupon.setUserId(userId);
    coupon.setCouponCode("COUPON-" + new Random().nextInt(10000));
    coupon.setStatus("active");
    coupon.setActivationDate(LocalDate.now());
    coupon.setExpirationDate(LocalDate.now().plusDays(30)); // Expires in 30 days

    return couponRepository.save(coupon);
}


    public List<Coupon> getUserCoupons(Long userId) {
        return couponRepository.findByUserId(userId);
    }

    public Coupon expireCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        coupon.setStatus("expired");
        return couponRepository.save(coupon);
    }


    public List<Coupon> getAllUserCoupons() {
        return couponRepository.findAll();
    }
}