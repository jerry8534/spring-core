package com.hello.core.autowired;

import com.hello.core.AutoAppConfig;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        // 이 테스트는 모든 빈을 찾는 예시입니다.
        // @Autowired를 사용하여 모든 빈을 주입받을 수 있습니다.
        // 예를 들어, ApplicationContext에서 모든 빈을 가져와서 출력할 수 있습니다.
        // ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // Map<String, Object> beans = ac.getBeansOfType(Object.class);
        // beans.forEach((name, bean) -> System.out.println(name + " : " + bean));
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "memberA", Grade.VIP); // null은 등급을 지정하지 않음
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000); // RateDiscountPolicy의 할인율이 10%이므로 10000의 10%인 1000이 할인되어야 합니다.

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        // 이 클래스는 할인 서비스를 제공하는 빈으로 사용될 수 있습니다.
        // 필요한 필드와 메서드를 정의할 수 있습니다.
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            // 할인 정책을 선택하여 할인 금액을 계산하는 메서드
            // 예시로 첫 번째 정책을 사용하여 할인 금액을 계산
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
