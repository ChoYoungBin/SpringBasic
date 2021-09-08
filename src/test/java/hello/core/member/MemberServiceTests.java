package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTests {

    MemberSerivce memberSerivce = new MemberServiceImpl();
    @Test
    void test_findMember_returnsExactObject(){
        //given
        Member member = new Member(1L, "A",Grade.VIP);

        //when
        memberSerivce.join(member);
        Member member1 = memberSerivce.findMember(1L);

        //then
        assertThat(member).isEqualTo(member1);
    }
}
