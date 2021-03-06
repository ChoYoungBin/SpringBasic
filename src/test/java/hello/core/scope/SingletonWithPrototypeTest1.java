package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void test_FindPrototypeBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean prototypeBean1 = context.getBean(ProtoTypeBean.class);
        ProtoTypeBean prototypeBean2 = context.getBean(ProtoTypeBean.class);


        prototypeBean1.addCount();
        prototypeBean2.addCount();


        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void test_FindSingletonTypeBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);
        ClientBean clientBean1 = context.getBean(ClientBean.class);
        ClientBean clientBean2 = context.getBean(ClientBean.class);


        int count1 = clientBean1.logic();
        int count2 = clientBean2.logic();


        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        //@Autowired
        //private ProtoTypeBean protoTypeBean; //생성시점에 주입 x01

        //Dependency Lookup (DL)
        //@Autowired
        //private ObjectProvider<ProtoTypeBean> protoTypeBeanObjectProvider;
        //ObjectFactory도 가능(ObjectProvider가 더 구체화)
        //private ObjectFactory<ProtoTypeBean> protoTypeBeanObjectProvider;

        @Autowired//Javax Provider Dependency 활용
        private Provider<ProtoTypeBean> protoTypeBeanObjectProvider;

        public int logic() {

            //DL방식
            //ProtoTypeBean protoTypeBean = protoTypeBeanObjectProvider.getObject();

            //Javax Provider 방식
            ProtoTypeBean protoTypeBean = protoTypeBeanObjectProvider.get();
            protoTypeBean.addCount();

            return protoTypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class ProtoTypeBean {
        private int count = 0;


        public ProtoTypeBean() {

        }

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);

        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

        public int getCount() {
            return this.count;
        }
    }

}
