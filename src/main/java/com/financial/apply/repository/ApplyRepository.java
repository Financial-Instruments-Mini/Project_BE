package com.financial.apply.repository;

import com.financial.apply.entity.Apply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    List<Apply> findByMemberId(Long memberId);

    Slice<Apply> findByMemberId(Long memberId, Pageable pageable);
}
