package hello.core.autowired;

import hello.core.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;


import java.util.Optional;

public class AutowiredTest {

    @Test
    void test_AutowiredOption(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)
        public void setNoBean(Member member){
            System.out.println("member = " + member);

        }

        @Autowired
        public void setMember(@Nullable Member member){
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member){
            System.out.println("member = " + member);

        }

    }


}
