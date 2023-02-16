package com.financial.bookmark.service;

import com.financial.apply.dto.MemberProductRes;
import com.financial.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public List<MemberProductRes> memberBookmarks(Long memberId) {
        return bookmarkRepository.findByMemberId(memberId).stream()
                .map(MemberProductRes::fromBookmark)
                .collect(Collectors.toList());
    }
}
