package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    private MemoryMemberRepository repo = new MemoryMemberRepository();

    @AfterEach
    void tearDown() {
        repo.clearStore();
    }

    @Test
    void save() {
        Member m = new Member();
        m.setName("spring");
        repo.save(m);

        Member result = repo.findById(m.getId()).get();
        assertThat(m).isEqualTo(result);
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
        Member m1 = new Member();
        m1.setName("spring1");
        repo.save(m1);

        Member m2 = new Member();
        m2.setName("spring2");
        repo.save(m2);

        Member result = repo.findByName("spring1").get();
        assertThat(result).isEqualTo(m1);
    }

    @Test
    void findAll() {
        Member m1 = new Member();
        m1.setName("spring1");
        repo.save(m1);

        Member m2 = new Member();
        m2.setName("spring2");
        repo.save(m2);

        List<Member> result = repo.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}