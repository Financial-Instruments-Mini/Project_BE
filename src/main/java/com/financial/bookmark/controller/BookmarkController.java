package com.financial.bookmark.controller;

import com.financial.apply.dto.MemberProductRes;
import com.financial.bookmark.dto.BookmarkRegistrationReq;
import com.financial.bookmark.service.BookmarkService;
import com.financial.global.response.BaseResponse;
import com.financial.member.dto.MemberAdapter;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"관심상품서비스"}, description = "관심상품 관련 등록/조회/삭제서비스")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @ApiOperation(value = "관심상품 목록조회", notes = "관심상품으로 등록한 금융상품 목록을 조회")
    @GetMapping
    public BaseResponse<Slice<MemberProductRes>> memberBookmarks(@AuthenticationPrincipal MemberAdapter memberAdapter, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return BaseResponse.of(bookmarkService.memberBookmarks(memberAdapter.getMember().getId(), pageRequest));
    }

    @ApiOperation(value = "관심상품 등록", notes = "금융상품을 관심상품으로 등록")
    @PostMapping
    public String applyRegistration(@AuthenticationPrincipal MemberAdapter memberAdapter, @RequestBody BookmarkRegistrationReq request) {
        return bookmarkService.bookmarkRegistration(memberAdapter, request);
    }

    @ApiOperation(value = "관심상품목록 전체삭제", notes = "관심상품으로 등록한 목록 전체삭제")
    @DeleteMapping
    public String deleteAllBookmark(@AuthenticationPrincipal MemberAdapter memberAdapter) {
        return bookmarkService.deleteAllBookmark(memberAdapter);
    }

    @ApiOperation(value = "관심상품 개별삭제", notes = "관심상품을 개별삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookmarkId", value = "관심상품목록의 관심상품id", dataType = "Long",required = true),})
    @DeleteMapping("/{bookmarkId}")
    public String bookmarkDelete(@AuthenticationPrincipal MemberAdapter memberAdapter, @PathVariable Long bookmarkId) {
        return bookmarkService.deleteBookmark(memberAdapter, bookmarkId);
    }

}
