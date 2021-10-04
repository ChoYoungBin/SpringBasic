package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountRate / 100;
        }
        else return price;
    }
}
