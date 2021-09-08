package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import org.springframework.util.Assert;

public class MemberApp {
    public static void main(String[] args) {
        MemberSerivce memberSerivce = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Member member1 = memberSerivce.findMember(1L);

        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + member1.getName());
    }
}
