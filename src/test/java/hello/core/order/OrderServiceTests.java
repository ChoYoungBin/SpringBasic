package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTests {

    AppConfig appConfig = new AppConfig();
    private MemberService memberService = appConfig.memberService();
    private OrderService orderService = appConfig.orderService();

    @Test
    void test_order_returnsMember(){
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "nameA", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.order(memberId, "ItemA", 10000);

        //then
        assertThat(order.getItemName()).isEqualTo("ItemA");
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
