package hello.core.order;

import hello.core.discount.DiscountImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;

public class OrderServiceImpl implements OrderService {

    private MemberSerivce memberSerivce = new MemberServiceImpl();
    private DiscountPolicy discountPolicy = new DiscountImpl();

    @Override
    public Order order(Long id, String product, int price) {
        Member member = memberSerivce.findMember(id);
        int discount = discountPolicy.discount(member, price);

        return new Order(member.getId(), product, price, discount);
    }
}
