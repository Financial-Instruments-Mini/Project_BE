package com.financial.interest.repository;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.enums.DueDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    Slice<Interest> findAllByDueDateOrderByRateDesc(Pageable pageable, DueDate dueDate);

    @Query(value = "select i from Interest i join fetch i.product where i.dueDate = :dueDate order by i.product.productMakeDay desc ")
    Slice<Interest> findAllByDueDateOrderByProductMakeDay(Pageable pageable, DueDate dueDate);
}
