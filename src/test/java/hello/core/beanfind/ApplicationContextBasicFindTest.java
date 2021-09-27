package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름과 타입으로 조회")
    void findBeanByName() {
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = applicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    /*
    * 구체화된 타입으로 조회하는것은 DIP(Dependency Inversion Principle)위배
    * 프로그래머는 추상화(인터페이스)에 의존해야지 구체화(구체화된 인스턴스)에 의존하면 안된다. DI(의존성 주입)를 잘 활용해야하는 이유
    */
    void findBeanByName2() {
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회한 결과가 없는 경우")
    void findBeanByNameX() {
        /*
        * applicationContext.getBean("memberService", MemberService.class)를 실행했을 때
        * NoSuchBeanDefinitionException.class 예외가 발생하는 것을 기대하는 테스트 케이스
        * */
        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean("xxxx", MemberService.class));
    }
}
