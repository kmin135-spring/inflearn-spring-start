package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService mService;

    public MemberController(MemberService mService) {
        this.mService = mService;
        /*
        1. AOP 미적용일 때
        #memberService = class hello.hellospring.service.MemberService

        2. AOP가 적용되어있을 때 (@Transactional 이나 이 강의처럼 TimeTraceAop 에서 대상으로 건 경우)
        #memberService = class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$1b2067fc

        AOP가 적용되기전에는 MemberService를 직접 참조한 반면
        적용 후에는 CGLIB를 통해 프록시로 감싸서 (가짜 MemberService) 참조함을 알 수 있음
         */
        System.out.println("#memberService = " + mService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        mService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", mService.findMembers());
        return "members/memberList";
    }
}
