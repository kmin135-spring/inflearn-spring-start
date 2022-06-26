package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService mService;

    public MemberController(MemberService mService) {
        this.mService = mService;
    }
}
