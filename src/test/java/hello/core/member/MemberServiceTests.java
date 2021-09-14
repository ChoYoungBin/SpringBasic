package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTests {
    AppConfig appConfig;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void test_findMember_returnsExactObject(){
        //given
        Member member = new Member(1L, "A", Grade.VIP);

        //when
        memberService.join(member);
        Member member1 = memberService.findMember(1L);

        //then
        assertThat(member).isEqualTo(member1);
    }
}
