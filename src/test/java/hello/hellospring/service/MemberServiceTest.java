package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private MemoryMemberRepository repo;
    private MemberService mService;

    @BeforeEach
    void setUp() {
        repo = new MemoryMemberRepository();
        mService = new MemberService(repo);
    }

    @AfterEach
    void tearDown() {
        repo.clearStore();
    }

    @Test
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

        // assertThrows 를 이용한 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> mService.join(m2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

        /* try-catch 를 이용한 방법
        try {
            mService.join(m2);
            fail("중복 저장 발생!");
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
        }
        */

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}