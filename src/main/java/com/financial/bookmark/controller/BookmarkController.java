package com.financial.bookmark.controller;

import com.financial.bookmark.dto.BookmarkRegistrationReq;
import com.financial.bookmark.service.BookmarkService;
import com.financial.global.response.BaseResponse;
import com.financial.member.dto.MemberAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public BaseResponse<?> memberBookmarks(@AuthenticationPrincipal MemberAdapter memberAdapter){
        return BaseResponse.of(bookmarkService.memberBookmarks(memberAdapter.getMember().getId()));
    }

    @PostMapping
    public String applyRegistration(@AuthenticationPrincipal MemberAdapter memberAdapter, @RequestBody BookmarkRegistrationReq request) {
        return bookmarkService.bookmarkRegistration(memberAdapter, request);
    }

    @DeleteMapping
    public String deleteAllBookmark(@AuthenticationPrincipal MemberAdapter memberAdapter) {
        return bookmarkService.deleteAllBookmark(memberAdapter);
    }

}
