package hello.core.beanfind;

import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameBeanFindTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시, 같은 타입이 둘 이상 있으면 중복 오류 발생")
    void test_getBean_returnsErrorOnDuplication(){


        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                ()-> context.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회 시, 같은 타입이 둘 이상 있으면 Bean 이름을 지정해서 해결")
    void test_getBean_returnsBeanOnSameTypeDifferentBeanName(){
        MemberRepository repository = context.getBean("memberRepository", MemberRepository.class);


        assertThat(repository).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void test_getBean_returnsAllBeans(){
        Map<String, MemberRepository> beansOfType = context.getBeansOfType(MemberRepository.class);


        for (String key : beansOfType.keySet()) {
            System.out.println("key =" + key + " value = " + beansOfType.get(key));
        }


        System.out.println("beansOfType == "+ beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig{

        @Bean
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
