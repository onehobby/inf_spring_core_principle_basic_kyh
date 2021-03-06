package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();

/*
        [ApplicationContext 사용 전]
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        */

        /*
            ApplicationContext(Spring Container)가 AppConfing 클래스의 설정정보들을 읽어서 Spring Bean으로 등록/관리해준다
        */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Spring이 관리해주는 bean을 찾을때에는 설정클래스인 AppConfig에 bean으로 등록한 메소드 이름 및 그 클래스를 인자로 준다.
        // 찾고싶은 bean의 이름과 반환타입을 명시하여 해당 클래스를 반환받는다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
