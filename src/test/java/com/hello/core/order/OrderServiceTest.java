package com.hello.core.order;

import com.hello.core.AppConfig;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", com.hello.core.member.Grade.VIP);

        // when
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
