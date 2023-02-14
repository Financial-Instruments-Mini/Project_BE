package com.financial.bookmark.repository;

import com.financial.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

//@EntityGraph(attributePaths = {"member","product","interest"})
List<Bookmark> findByMemberId(Long id);


}
