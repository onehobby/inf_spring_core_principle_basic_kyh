package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.discount.fixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*애플리케이션 전체를 설정하고 구성하는 클래스
 * ex) 구현객체를 생성하고 연결하는 책임을 가지는 별도의 설정클래스
 */
@Configuration
public class AppConfig {
    /*

    [before Refactoring]

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new fixDiscountPolicy());
    }*/


    /*
        ApplicationContext(스프링 컨테이너)가 관리하는 bean으로 등록되는데 이 때 이름은 메소드명으로 관리되고
        인스턴스는 return되는 인스턴스가 반환된다.
        HashMap을 생각하면된다. key와 value로 매칭된다.
        key를 찾고 key에 대한 value를 반환받는다.
    */

    /*
    *  @Bean memberService -> new MemoryMemberRepository()
    *  @Bean orderService ->  new MemoryMemberRepository(), new RateDiscountPolicy()
     */

    /*
    * Call AppConfig.memberService
    * Call AppConfig.memberRepository
    * Call AppConfig.orderService
    * Call AppConfig.memberRepository
    * AppConfig.discountPolicy
    * */
    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
//        return new fixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
