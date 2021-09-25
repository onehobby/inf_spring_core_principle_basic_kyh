package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /*다형성을 이용해서 MemberRepository 인터페이스에 구현체를 주입해줌(?)
    * memberRepository객체에는 MemoryMemberRepository 인스턴스의 주소값이 있음
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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

}
