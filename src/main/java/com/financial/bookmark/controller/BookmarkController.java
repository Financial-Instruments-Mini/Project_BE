package com.financial.bookmark.controller;

import com.financial.bookmark.service.BookmarkService;
import com.financial.global.response.BaseResponse;
import com.financial.member.dto.MemberAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/api/v1/bookmarks")
    public BaseResponse<?> memberBookmarks(@AuthenticationPrincipal MemberAdapter memberAdapter){
        return BaseResponse.of(bookmarkService.memberBookmarks(memberAdapter.getMember().getId()));
    }

}
