package com.hello.core.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        // Given
        // XML 설정 파일을 사용하여 ApplicationContext를 생성합니다.
        ApplicationContext ac = new org.springframework.context.support.GenericXmlApplicationContext("appConfig.xml");

        // When
        com.hello.core.member.MemberService memberService = ac.getBean("memberService", com.hello.core.member.MemberService.class);
        com.hello.core.member.Member member = new com.hello.core.member.Member(1L, "memberA", com.hello.core.member.Grade.VIP);
        memberService.join(member);
        com.hello.core.member.Member findMember = memberService.findMember(1L);

        // Then
        org.junit.jupiter.api.Assertions.assertEquals(member.getName(), findMember.getName());
    }
}
