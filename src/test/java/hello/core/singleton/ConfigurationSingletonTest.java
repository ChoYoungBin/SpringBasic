package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.repository.MemberRepository;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void test_configurationTest(){
        MemberServiceImpl memberService = context.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = context.getBean("orderService", OrderServiceImpl.class);


        MemberRepository memberRepository = memberService.getMemberRepository();
        MemberRepository memberRepository1 = orderService.getMemberRepository();


        assertThat(memberRepository).isSameAs(memberRepository1);
    }

    @Test
    void test_configurationDeep_returnsCGLIBClass(){
        AppConfig bean = context.getBean(AppConfig.class);

        System.out.println("bean = " + bean);
        //bean = hello.core.AppConfig $$ EnhancerBySpringCGLIB $$ b43eb665@2002348
        //          원래 AppConfig      Spring이 조작해 만든 Class       클래스주소
    }
}
