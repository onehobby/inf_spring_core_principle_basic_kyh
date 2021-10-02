package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
//      ComponentScan의 시작패키지를 정할 수 있다.
        basePackages = "hello.core.member",
//      지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
        basePackageClasses = AutoAppConfig.class,
//        ComponentScan은 @Component이 붙은 클래스들을 읽어서 ApplicationContext가 스프링의 bean으로 등록한다.
//        ComponentScan의 excludeFilters설정은 제외할 클래스를 설정할 수 있다.
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
