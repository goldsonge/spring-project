package com.estsoft.springproject.lombok;

// import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @Test
    public void test() {
        // given    //보통 3개로 이루어져있지만 given이 빠질때도 있음
        int a = 1;
        int b = 2;

        // when :  검증하고싶은 메서드(코드) 호출
        int sum = a + b;

        // then : when절에서 실행한 결과 검증
        // Assertions.assertEquals(3, sum);
        Assertions.assertThat(sum).isEqualTo(3);

        // Assertions.assertThat(sum).isEven();
        Assertions.assertThat(sum).isOdd();
    }
}
