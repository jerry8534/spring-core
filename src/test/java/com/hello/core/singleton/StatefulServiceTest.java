package com.hello.core.singleton;

import com.hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StatefulService.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A: A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // Thread B: B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // Thread A: 사용자 A가 주문 금액을 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice); // 예상: 10000원

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        // 테스트용 설정 클래스
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
