package com.hello.core.autowired;

import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        // 이 테스트는 @Autowired의 옵션을 테스트하는 예시입니다.
        // @Autowired(required = false)와 같은 옵션을 사용하여 의존성 주입을 선택적으로 수행할 수 있습니다.
        // 예를 들어, 특정 빈이 없을 때 에러를 발생시키지 않도록 설정할 수 있습니다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        ac.getBean(MemberRepository.class);
    }

    static class TestBean {
        // 이 클래스는 @Autowired 테스트를 위한 빈으로 사용될 수 있습니다.
        // 필요한 필드와 메서드를 정의할 수 있습니다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
