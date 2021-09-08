package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTests {
    private MemberSerivce memberSerivce = new MemberServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    @Test
    void order(){
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "nameA", Grade.VIP);
        memberSerivce.join(member);

        //when
        Order order = orderService.order(memberId, "ItemA", 10000);

        //then
        assertThat(order.getItemName()).isEqualTo("ItemA");
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
