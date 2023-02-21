package com.financial.interest.repository;

import com.financial.interest.entity.Interest;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.DueDate;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    Slice<Interest> findAllByDueDateOrderByRateDesc(Pageable pageable, DueDate dueDate);

    @Query(value = "select i from Interest i join fetch i.product where i.dueDate = :dueDate order by i.product.productMakeDay desc ")
    Slice<Interest> findAllByDueDateOrderByProductMakeDay(Pageable pageable, DueDate dueDate);

    Slice<Interest> findAllByDueDateAndProductProductNameContainingOrderByRateDesc(Pageable pageable,DueDate dueDate, String search);

    Slice<Interest> findAllByDueDateAndProductProductNameContainingOrderByProductProductMakeDay(Pageable pageable,DueDate dueDate, String search);

    Slice<Interest> findAllByDueDateAndProductProductTypeAndProductJob(Pageable pageable,DueDate dueDate, ProductType productType, Job job);

    Slice<Interest> findAllByDueDateAndProductJob(Pageable pageable,DueDate dueDate, Job job);

}
