package com.financial.bookmark.controller;

import com.financial.apply.dto.MemberProductRes;
import com.financial.bookmark.service.BookmarkService;
import com.financial.global.response.BaseResponse;
import com.financial.member.dto.MemberAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/api/v1/bookmarks")
    public BaseResponse<Slice<MemberProductRes>> memberBookmarks(@AuthenticationPrincipal MemberAdapter memberAdapter, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return BaseResponse.of(bookmarkService.memberBookmarks(memberAdapter.getMember().getId(), pageRequest));
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
