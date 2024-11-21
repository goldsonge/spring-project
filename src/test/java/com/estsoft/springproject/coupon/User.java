package com.estsoft.springproject.coupon;

import java.util.ArrayList;
import java.util.List;

// Production Code
public class User {
    private List<ICoupon> coupons;
    public User(String id) {
        coupons = new ArrayList<>();
    }
    public int getTotalCouponCount(){
        return coupons.size();
    }

    public void addCoupon(ICoupon coupon) {
        if(coupon.isValid()){
            coupons.add(coupon);
        }
    }
}
