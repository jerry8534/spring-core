package com.hello.core.discount;

import com.hello.core.annotation.MainDiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") // @Qualifier를 사용하여 특정 빈을 지정
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10; // 할인율 10%

    @Override
    public int discount(Member member, int price) {
        // VIP 회원에게만 할인 적용
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100; // 할인 금액 계산
        } else {
            return 0; // VIP가 아닌 경우 할인 없음
        }
    }
}
