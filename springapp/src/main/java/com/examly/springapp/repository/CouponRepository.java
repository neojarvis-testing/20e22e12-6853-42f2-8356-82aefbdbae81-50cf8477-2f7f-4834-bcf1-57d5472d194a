package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("select coupon from Coupon coupon where coupon.userId =?1 ")
    List<Coupon> findByUserId(Long userId);
}
