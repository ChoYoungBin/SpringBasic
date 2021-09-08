package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class DiscountImpl implements DiscountPolicy {
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()){
            return 1000;
        }
        else return 0;
    }
}
