package com.estsoft.springproject.tdd;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

// TDD
// 2. 잔금 조회
// 3. 입/출금
@Disabled
public class AccountTest {
    Account account;
    @BeforeEach
    public void setUp() {
        Account account = new Account(10000);
    }
    // 1. 계좌 생성
    @Test
    public void testAccount() {
//        Account account = new Account(10000);
        // JUnit, AssertJ, hamcrest
        assertThat(account.getBalance(), is(10000)); // hamcrest 검증 완료
        // 잔금 조회가 잘 되나?
//        if(account.getBalance() != 10000){
//            fail();
//        }

        account = new Account(20000);
        assertThat(account.getBalance(), is(20000));
//        if(account2.getBalance() != 20000){
//            fail();
//        }
        account = new Account(50000);
        assertThat(account.getBalance(), is(50000));
//        if(account3.getBalance() != 50000){
//            fail();
//        }
    }

    @Test
    public void testDeposit() {
//        Account account = new Account(10000);
        account.deposit(100000);
        assertThat(account.getBalance(), is(110000)); // 입금 기능 검증 완료
    }

    @Test
    public void testWithdraw() {
//        Account account = new Account(10000);
        account.withdraw(10000);
        assertThat(account.getBalance(), is(0)); // 출금 기능 검증 완료
    }
}
