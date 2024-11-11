package com.koreait.Surl_project_11.grobal.rq;

import com.koreait.Surl_project_11.domain.member.entity.Member;
import com.koreait.Surl_project_11.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final MemberService memberService;
    public Member getMember() {
        return memberService.getReferenceById(1L);
    }
}