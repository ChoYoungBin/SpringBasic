package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void test_pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();


        assertThat(memberService).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void test_SingletonServiceTest(){
        SingletonService instance = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();


        assertThat(instance).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void test_SpringSingleContainer(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = context.getBean("memberService", MemberService.class);
        MemberService memberService2 = context.getBean("memberService", MemberService.class);

        assertThat(memberService).isSameAs(memberService2);
    }
}
