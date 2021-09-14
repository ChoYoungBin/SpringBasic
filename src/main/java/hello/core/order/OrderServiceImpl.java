package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order order(Long id, String product, int price) {
        Member member = memberRepository.findById(id);
        int discount = discountPolicy.discount(member, price);

        return new Order(member.getId(), product, price, discount);
    }
}
