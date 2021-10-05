package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void test_prototypeBeanFind() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);


        assertThat(bean1).isNotSameAs(bean2);
        //client 가 직접 destroy를 호출해야만함
        bean1.destroy();
        bean2.destroy();


        context.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("Prototype.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Prototype.destroy");
        }
    }
}
