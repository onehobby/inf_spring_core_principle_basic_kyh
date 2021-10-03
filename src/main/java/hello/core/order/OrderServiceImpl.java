package hello.core.order;

import hello.core.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.discount.fixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
/*@RequiredArgsConstructor  lombok의 @RequiredArgsConstructor :: final 지시어가 붙은 변수들을 매개변수로 받는 생성자를 자동으로 만들어줌 */
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
    /*@Autowired :: 생성자가 하나 일 경우 Autowired를 생략할 수 있다.*/
    /*lombok의 @RequiredArgsConstructor를 통해 final 지시어가 붙은 변수들을 매개변수로 받는 생성자를 자동으로 만들어줌*/
    /*spring bean으로 등록된 DiscountPolicy 구현체가 2개이므로 UnsatisfiedDependencyException발생,
    * 이 때 매개변수에 원하는 구현체의 이름을 매칭시켜주면 된다. ex)rateDiscountPolicy
    * */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/ @MainDiscountPolicy DiscountPolicy discountPolicy) {
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
