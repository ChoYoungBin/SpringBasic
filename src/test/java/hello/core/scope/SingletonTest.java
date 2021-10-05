package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    void test_singletonBeanFind() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = context.getBean(SingletonBean.class);
        SingletonBean bean2 = context.getBean(SingletonBean.class);

        
        assertThat(bean1).isSameAs(bean2);
        context.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destory() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
