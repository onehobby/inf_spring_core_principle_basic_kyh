package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.discount.fixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    /*
    * SRP(Single Responsibility Principle) 위배, 마치 배우가 다른배우를 섭외하는 꼴(?)
    * DIP(Dependency Inversion Principle) 위배, 추상화에도 의존하고 구체화에도 의존한다.
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /*정책변경 시 클라이언트측 소스를 변경해야함, OCP(open-closed principle, 개방폐쇄원칙) 위반!!*/
//    private final DiscountPolicy discountPolicy = new fixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*생성자를 통해서 외부에서 memberRepository에 구현체를 주입 ==> 생성자주입
    , DIP 원칙을 위배하지 않음, 추상화에만 의존하고 구체화에는 의존하지 않는다.
    */
    /*@Autowired*/
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /*테스트 용도*/
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
    
}
