package com.financial.bookmark.repository;

import com.financial.bookmark.entity.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

//@EntityGraph(attributePaths = {"member","product","interest"})
Slice<Bookmark> findByMemberId(Long id, Pageable pageable);

    List<Bookmark> findByMemberId(Long id);
    void deleteAllByMember_Id(Long memberId);


}
