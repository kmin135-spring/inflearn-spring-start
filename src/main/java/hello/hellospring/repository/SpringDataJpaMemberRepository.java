package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 메서드 이름의 규칙을 통해 자동으로 쿼리를 만들어서 실행해준다.
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
