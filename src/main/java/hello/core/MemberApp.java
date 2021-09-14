package hello.core;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member member1 = memberService.findMember(1L);

        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + member1.getName());
    }
}
