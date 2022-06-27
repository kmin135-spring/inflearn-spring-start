package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository mRepo;

    // mRepo를 DI (Dependency Injection)
    public MemberService(MemberRepository mRepo) {
        this.mRepo = mRepo;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        throwIfExistsName(member);

        mRepo.save(member);
        return member.getId();
    }

    /** 전체 회원 조회 */
    public List<Member> findMembers() {
        return mRepo.findAll();
    }

    /** 단일 회원 조회 */
    public Optional<Member> findOne(Long memberId) {
        return mRepo.findById(memberId);
    }

    /** 요구사항 : 중복 이름이 있으면 안 됨 */
    private void throwIfExistsName(Member member) {
        mRepo.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }
}
