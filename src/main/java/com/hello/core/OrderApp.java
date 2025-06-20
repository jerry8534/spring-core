package com.hello.core;

import com.hello.core.Order.Order;
import com.hello.core.Order.OrderService;
import com.hello.core.Order.OrderServiceImpl;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", com.hello.core.member.Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
