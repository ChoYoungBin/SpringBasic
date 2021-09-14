package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


public class AppConfig {

    //역할
    //DIP 원칙 해결
    public MemberService memberService(){
        //구현 & 역할
        return new MemberServiceImpl(memberRepository());
    }

    //역할
    public OrderService orderService(){
        //구현 & 역할
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //역할
    public MemberRepository memberRepository() {
        //구현
        return new MemoryMemberRepository();
    }

    //역할
    public DiscountPolicy discountPolicy(){
        //구현
        /**
         * 구현쪽에서만 수정하면 client code의 변경사항 없이 대응가능
         */
        //OCP 원칙 해결
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}