package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String couponCode;
    private String status; // "active" or "expired"
    private LocalDate activationDate;
    private LocalDate expirationDate;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getCouponCode() {
        return couponCode;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getActivationDate() {
        return activationDate;
    }
    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    public Coupon(Long id, Long userId, String couponCode, String status, LocalDate activationDate,
            LocalDate expirationDate) {
        this.id = id;
        this.userId = userId;
        this.couponCode = couponCode;
        this.status = status;
        this.activationDate = activationDate;
        this.expirationDate = expirationDate;
    }
    public Coupon() {
    }

    // Constructors, Getters, Setters
    
    
}