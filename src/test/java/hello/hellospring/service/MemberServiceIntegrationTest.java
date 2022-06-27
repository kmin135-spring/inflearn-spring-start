package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
통합테스트
단위테스트대비 시간이 오래걸리므로 매번 돌리기에는 부담됨
필요한 내용에 한해 통합테스트를 작성하고 CI 등에서 한번에 몰아서 돌려주는게 좋겠다.
*/
@SpringBootTest
/*
테스트케이스에 달면 각 테스트 단위로 트랜잭션을 걸어주고 테스트 종료 후 롤백해준다.
*/
@Transactional
class MemberServiceIntegrationTest {
    @Autowired private MemberService mService;
    @Autowired private MemberRepository repo;

    @Test
//    @Commit
    void join() {
        // given
        Member m = new Member();
        m.setName("spring");

        // when
        Long saveId = mService.join(m);

        // then
        Member result = mService.findOne(saveId).get();
        assertThat(m.getName()).isEqualTo(result.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member m1 = new Member();
        m1.setName("spring");

        Member m2 = new Member();
        m2.setName("spring");

        // when
        mService.join(m1);

        // then
        // assertThrows 를 이용한 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> mService.join(m2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}