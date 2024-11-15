package com.koreait.Surl_project_11.domain.member.controller;

import com.koreait.Surl_project_11.domain.member.entity.Member;
import com.koreait.Surl_project_11.domain.member.service.MemberService;
import com.koreait.Surl_project_11.grobal.eceptions.GlobalException;
import com.koreait.Surl_project_11.grobal.rsData.RsData;
import com.koreait.Surl_project_11.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping("/join")
    public RsData join(
            String username, String password, String nickname
    ) {
        if (Ut.str.isBlank(username)) {
         //   return RsData.of("400-1", "username을 입력해");
        throw new GlobalException("400-1","username을 입력해");
        }

        if (Ut.str.isBlank(password)) {
            throw new GlobalException("400-1","password를 입력해");
        }

        if (Ut.str.isBlank(nickname)) {
            throw new GlobalException("400-1","nickname을 입력해");
        }


        RsData<Member> joinRs = memberService.join(username, password, nickname);
        return joinRs;
    }
}
