package com.hello.core.singleton;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        // 스프링 컨테이너를 사용하여 Configuration 클래스를 테스트하는 예시
        // 이 부분은 실제로 AppConfig와 같은 설정 클래스를 사용하여 테스트할 수 있습니다.
        // 예: ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        // memberRepository1과 memberRepository2가 동일한 인스턴스인지 확인
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository -> memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        // Configuration 클래스의 프록시 객체를 확인하는 테스트
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = ac.getBean(AppConfig.class);

        // AppConfig가 CGLIB 프록시로 생성되었는지 확인
        System.out.println("appConfig.getClass() = " + appConfig.getClass());
        // 출력 예시: class com.hello.core.AppConfig$$EnhancerBySpringCGLIB$$12345678

        assertThat(appConfig).isInstanceOf(AppConfig.class);
    }
}
