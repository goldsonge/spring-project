package com.estsoft.springproject.coupon;

// 테스트 코드에서만 사용할 더미 객체의 클래스
public class DummyCoupon implements ICoupon {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public int getDiscountPercent() {
        return 0;
    }

    @Override
    public boolean isAppliable(Item item) {
        return false;
    }

    @Override
    public void doExpire() {

    }

}
