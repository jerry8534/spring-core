package com.hello.core.Order;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 조회
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public int calculatePrice(Long memberId, String itemName, int itemPrice) {
        // 회원 조회
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return itemPrice - discountPrice;
    }
}
