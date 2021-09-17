package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void test_AutoAppConfig(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class);


        MemberService memberService = context.getBean(MemberService.class);


        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
