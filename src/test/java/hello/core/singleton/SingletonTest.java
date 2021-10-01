package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        /*기존의 DI 컨테이너(AppConfig)를 사용하면 동일한 클래스 정보를 가진 다른 인스턴스가 각각 생성된다.*/
        AppConfig appConfig = new AppConfig();

        /*호출할 때 마다 객체를 생성1*/
        MemberService memberService1 = appConfig.memberService();
        /*호출할 때 마다 객체를 생성2*/
        MemberService memberService2 = appConfig.memberService();

        /*참조하는 주소값이 다른것을 확인할 수 있다.*/
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /*memberService1 != memberService2*/
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

//      isEqualTo() :: 실제값(리터럴값)을 비교
//      isSameAs :: 객체비교 시 주소값을 비교
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    /*위의 pureContainer와 비교해보자*/
    void springContainer() {

//        AppConfig appConfig = new AppConfig();
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = annotationConfigApplicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = annotationConfigApplicationContext.getBean("memberService", MemberService.class);

        /*참조하는 주소값이 다른것을 확인할 수 있다.*/
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /*memberService1 == memberService2*/
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}
