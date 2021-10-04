package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void test_findAllBean(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = context.getBean(DiscountService.class);
        Member userA = new Member(1L, "userA", Grade.VIP);
        int fixDiscount = discountService.discount(userA, 10000, "fixDiscountPolicy");
        int rateDiscount = discountService.discount(userA, 20000, "rateDiscountPolicy");


        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscount).isEqualTo(1000);


        assertThat(rateDiscount).isEqualTo(2000);
    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;

        @Autowired
        DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
            System.out.println("policyMap = " + policyMap);
        }

        public int discount(Member userA, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(userA, price);
        }
    }
}
