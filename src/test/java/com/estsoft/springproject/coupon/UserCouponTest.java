package com.estsoft.springproject.coupon;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class UserCouponTest {
    @Test
    public void testAddCoupon() {
        User user = new User("area00");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

        ICoupon coupon = new DummyCoupon();    // 어떻게 처리해야..?! dummy 쿠폰을 만들어서 사용

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
    }
    @Test
    public void testAddCouponWithMock() { // 쿠폰이 유효할 경우에만 유저에게 발급한다.
        User user = new User("area00");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

        ICoupon coupon = Mockito.mock(ICoupon.class);    // 어떻게 처리해야..?! mock객체는 행위를 정의할 수 있다.
        Mockito.when(coupon.isValid()).thenReturn(true); // stub
        Mockito.doReturn(true).when(coupon).isValid(); // stub

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
    }
//    @Test
//    public void testAddCouponWithMock() { // 쿠폰이 유효하지 않을 경우 발급되지 않는다.
//        // todo
//    }
}
