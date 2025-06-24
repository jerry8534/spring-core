package com.hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = new MemoryMemberRepository(); // Assuming MemoryMemberRepository is the implementation of MemberRepository
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // For testing purposes, you might want to add a method to get the member repository
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
