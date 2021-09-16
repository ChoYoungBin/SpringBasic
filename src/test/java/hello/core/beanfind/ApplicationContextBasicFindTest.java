package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void test_getBean_returnsBeanOnNameAndType(){
        MemberService memberService = context.getBean("memberService", MemberService.class);


        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로 조회")
    void test_getBean_returnsBeanOnType(){
        MemberService memberService = context.getBean( MemberService.class);


        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체로 조회")
    void test_getBean_returnsBeanOnImpl(){
        MemberServiceImpl memberService = context.getBean("memberService", MemberServiceImpl.class);


        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void test_getBean_ReturnsNothing(){

        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> context.getBean("wow",MemberService.class));
    }
}
