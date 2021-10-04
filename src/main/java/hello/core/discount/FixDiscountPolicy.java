package hello.core.discount;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FixDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()){
            return 1000;
        }
        else return 0;
    }
}
