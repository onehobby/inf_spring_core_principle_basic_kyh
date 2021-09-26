package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        this.memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given(이러한 상황이 주어졌을 때)
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when(이렇게 하면)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then(결과가 이렇게 나온다.)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
