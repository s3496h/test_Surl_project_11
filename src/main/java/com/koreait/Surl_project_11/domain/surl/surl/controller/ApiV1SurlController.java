package com.koreait.Surl_project_11.domain.surl.surl.controller;

import com.koreait.Surl_project_11.domain.auth.auth.service.AuthService;
import com.koreait.Surl_project_11.domain.member.entity.Member;
import com.koreait.Surl_project_11.domain.member.service.MemberService;
import com.koreait.Surl_project_11.domain.surl.surl.dto.SurlDto;
import com.koreait.Surl_project_11.domain.surl.surl.entity.Surl;
import com.koreait.Surl_project_11.domain.surl.surl.service.SurlService;
import com.koreait.Surl_project_11.grobal.eceptions.GlobalException;
import com.koreait.Surl_project_11.grobal.rq.Rq;
import com.koreait.Surl_project_11.grobal.rsData.RsData;
import com.koreait.Surl_project_11.standard.dto.Empty;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surls")
//@RequestMapping(value = "/api/v1/surls", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiV1SurlController {
    private final Rq rq;
    private final SurlService surlService;
    private final AuthService authService;
    private final MemberService memberService;

    @AllArgsConstructor
    @Getter
    public static class SurlAddReqBody {
        @NotBlank
        private String body;
        @NotBlank
        private String url;
    }

    @AllArgsConstructor
    @Getter
    public static class SurlAddRespBody {
        private SurlDto item;
    }

    @PostMapping("")
    @ResponseBody
    @Transactional
    @Operation(summary = "생성")
    public RsData<SurlAddRespBody> add(
            @RequestBody @Valid SurlAddReqBody reqBody
    ) {
        Member member = rq.getMember(); // 현재 브라우저로 로그인 한 회원 정보
        RsData<Surl> addRs = surlService.add(member, reqBody.body, reqBody.url);
        return addRs.newDataOf(
                new SurlAddRespBody(
                        new SurlDto(addRs.getData())
                )
        );
    }

    @AllArgsConstructor
    @Getter
    public static class SurlGetRespBody {
        private SurlDto item;
    }

    // /api/v1/surls/{id}
    // /api/v1/surls/1
    // /api/v1/surls?id=1
    @GetMapping("/{id}")
    @Operation(summary = "단건조회")
    public RsData<SurlGetRespBody> get(
            @PathVariable long id
    ) {
        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        authService.checkCanGetSurl(rq.getMember(), surl);

        return RsData.of(
                new SurlGetRespBody(
                        new SurlDto(surl)
                )
        );
    }

    @AllArgsConstructor
    @Getter
    public static class SurlGetItemsRespBody {
        private List<SurlDto> items;
    }

    @GetMapping("")
    @Operation(summary = "다건조회")
    public RsData<SurlGetItemsRespBody> getItems() {

        Member member = rq.getMember();
        List<Surl> surls = surlService.findByAuthorOrderByIdDesc(member);
        // Page
        // QueryDSL
        return RsData.of(
                new SurlGetItemsRespBody(
                        surls.stream()
                                .map(SurlDto::new)
                                .toList()
                )
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "삭제")
    public RsData<Empty> delete(
            @PathVariable long id
    ) {
        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);


        rq.getMember(); // member 로딩
        rq.getMember(); // 빠르게 했으면 좋겠어

        authService.checkCanGetSurl(rq.getMember(), surl);

        surlService.delete(surl);
        return RsData.OK;
    }

    @AllArgsConstructor
    @Getter
    public static class SurlModifyReqBody {
        @NotBlank
        private String body;
        @NotBlank
        private String url;
    }

    @AllArgsConstructor
    @Getter
    public static class SurlModifyRespBody {
        private SurlDto item;
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "수정")
    public RsData<SurlModifyRespBody> modify(
            @PathVariable long id,
            @RequestBody @Valid SurlModifyReqBody reqBody
    ) {
        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        authService.checkCanGetSurl(rq.getMember(), surl);

        RsData<Surl> modifyRs = surlService.modify(surl, reqBody.body, reqBody.url);
        return modifyRs.newDataOf(
                new SurlModifyRespBody(
                        new SurlDto(modifyRs.getData())
                )
        );
    }
}