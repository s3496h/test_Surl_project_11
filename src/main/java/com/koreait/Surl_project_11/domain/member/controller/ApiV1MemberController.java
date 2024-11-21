package com.koreait.Surl_project_11.domain.member.controller;

import com.koreait.Surl_project_11.domain.member.dto.MemberDto;
import com.koreait.Surl_project_11.domain.member.entity.Member;
import com.koreait.Surl_project_11.domain.member.service.MemberService;
import com.koreait.Surl_project_11.grobal.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j
public class ApiV1MemberController {
    private final MemberService memberService;

    @AllArgsConstructor
    @Getter
    public static class MemberJoinReqBody {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String nickname;
    }

    @AllArgsConstructor
    @Getter
    public static class MemberJoinRespBody {
        MemberDto item;
    }
    // POST /api/v1/members
    @PostMapping("")
    public RsData<MemberJoinRespBody> join(
            @RequestBody @Valid MemberJoinReqBody requestBody
    ) {
        RsData<Member> joinRs = memberService.join(requestBody.username, requestBody.password, requestBody.nickname);

        return joinRs.newDataOf(
                new MemberJoinRespBody(
                        new MemberDto(
                                joinRs.getData()
                        )
                )
        );
    }

}