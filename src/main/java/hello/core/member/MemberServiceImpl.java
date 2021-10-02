package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/*@Component("memberService2") 처럼 bean의 이름을 지정해서 등록할 수 있다.*/
public class MemberServiceImpl implements MemberService {

    /*다형성을 이용해서 MemberRepository 인터페이스에 구현체를 주입해줌
    * memberRepository객체에는 MemoryMemberRepository 인스턴스의 주소값이 있음
    * SRP(Single Responsibility Principle) 위배, 마치 배우가 다른배우를 섭외하는 꼴(?)
    * DIP(Dependency Inversion Principle) 위배, 추상화에도 의존하고 구체화에도 의존한다.
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    /*생성자를 통해서 외부에서 memberRepository에 구현체를 주입 ==> 생성자주입
    , DIP 원칙을 위배하지 않음, 추상화에만 의존하고 구체화에는 의존하지 않는다.
    */
    @Autowired // ac.getBean(MemberRepository.class 처럼 동작함)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*회원가입 기능*/
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    /*회원찾기 기능*/
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /*테스트 용도*/
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
