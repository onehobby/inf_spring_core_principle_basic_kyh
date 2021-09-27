package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*Junit5부터는 test클래스에서 public 접근제어자를 생략할 수 있다.*/
public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    /*Junit5부터는 test클래스에서 public 접근제어자를 생략할 수 있다.*/
    public void findAllBean() {
        /*@Bean이 붙어 spring bean으로 정의된 모든 bean의 이름을 가져온다.*/
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
//          applicationContext.getBean(beanDefinitionName) => bean이름을 key로 value값을 찾는 메소드
            Object bean = applicationContext.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    /*Junit5부터는 test클래스에서 public 접근제어자를 생략할 수 있다.*/
    public void findApplicationBean() {
        /*@Bean이 붙어 spring bean으로 정의된 모든 bean의 이름을 가져온다.*/
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
//          bean에 대한 metadata정보를 꺼내는 메소드
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

//          BeanDefinition.ROLE_APPLICATION :: 스프링 내부적으로 등록된 빈이 아닌 내가 애플리케이션을 개발하기 위해 등록한 빈
//                                             외부 라이브러리도 포함된다.
//          BeanDefinition클래스의 클래스변수 2가지
//          ROLE_APPLICATION :: 내가 직접 등록한 애플리케이션 빈
//          ROLE_INFRASTRUCTURE :: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName + "object = " + bean);
            }
        }
    }
}
