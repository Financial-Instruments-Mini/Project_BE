package com.financial.bookmark.service;

import com.financial.apply.dto.MemberProductRes;
import com.financial.apply.entity.Apply;
import com.financial.bookmark.dto.BookmarkRegistrationReq;
import com.financial.bookmark.entity.Bookmark;
import com.financial.bookmark.repository.BookmarkRepository;
import com.financial.interest.entity.Interest;
import com.financial.interest.repository.InterestRepository;
import com.financial.member.dto.MemberAdapter;
import com.financial.member.entity.Member;
import com.financial.product.entity.Product;
import com.financial.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ProductRepository productRepository;
    private final InterestRepository interestRepository;


    public List<MemberProductRes> memberBookmarks(Long memberId) {
        return bookmarkRepository.findByMemberId(memberId).stream()
                .map(MemberProductRes::fromBookmark)
                .collect(Collectors.toList());
    }

    @Transactional
    public String bookmarkRegistration(MemberAdapter memberAdapter, BookmarkRegistrationReq request) {
        Member member = memberAdapter.getMember();
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new IllegalStateException());
        Interest interest = interestRepository.findById(request.getInterestId()).orElseThrow(() -> new IllegalStateException());

        Bookmark bookmark = Bookmark.crateBookMark(member, product, interest);

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        Bookmark findBookmark = bookmarkRepository.findById(savedBookmark.getId()).get();

        if (findBookmark != null) {
            return "success";
        } else {
            return "fail";
        }
    }


    @Transactional
    public String deleteAllBookmark(MemberAdapter memberAdapter) {
        Member member = memberAdapter.getMember();
        bookmarkRepository.deleteAllByMember_Id(member.getId());

        List<Bookmark> findBookmark = bookmarkRepository.findByMemberId(member.getId());
        if (findBookmark.size() == 0) {
            return "success";
        }

        return "삭제에 실패 하였습니다.";
    }
}
