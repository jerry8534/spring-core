package com.hello.core.beanfind;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanTypeTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
//         MemberRepository bean = ac.getBean(MemberRepository.class); // 예외 발생
        assertThrows(
            org.springframework.beans.factory.NoUniqueBeanDefinitionException.class,
            () -> ac.getBean(MemberRepository.class)
        );
        // MemberService memberService = ac.getBean(MemberService.class);
        // MemberService memberService2 = ac.getBean(MemberService.class);
        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService2 = " + memberService2);
        // assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // assertThat(memberService2).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로 조회시, 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
        MemberRepository memberRepository2 = ac.getBean("memberRepository2", MemberRepository.class);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
//        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
//        MemberRepository memberRepository2 = ac.getBean("memberRepository2", MemberRepository.class);
//        System.out.println("memberRepository1 = " + memberRepository1);
//        System.out.println("memberRepository2 = " + memberRepository2);
//        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
//        assertThat(memberRepository2).isInstanceOf(MemberRepository.class);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
