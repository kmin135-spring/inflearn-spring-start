package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Service, @Repository 대신 이렇게해도 할 수도 있다.
다만 Controller, Service, Repository 는 컴포넌트 스캔을 사용하는게 보통이고
그 외에 정형화되지 않거나 상황에 따라 구현 클래스를 변경해야할 때 Configuration 을 통해
스프링 빈으로 등록한다.
 */
@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
//    private final EntityManager em;

    private final MemberRepository memberRepository;

    /*
    SpringDataJpaMemberRepository 는 JpaRepository 을 확장한 인터페이스이므로
    스프링이 자동으로 빈으로 등록해준다.
    그래서 여기서 바로 DI 할 수 있다.
     */
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*@Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }*/

    /*@Bean
    public MemberRepository memberRepository() {
        // 직접 컨트롤을 하면 구현체를 한 곳에서 쉽게 조절할 수 있다.
        // 다형성. OCP
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }*/
}
