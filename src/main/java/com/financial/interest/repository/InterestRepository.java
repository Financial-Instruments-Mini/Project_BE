package com.financial.interest.repository;

import com.financial.interest.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
//    @Query(value = "select distinct p, i from Interest i join fetch i.product p where i.product.id = :id")
//    @Query(value = "select i from Interest i join fetch i.product where i.product.id = :id")
    List<Interest> findDistinctByProductId(Long id);

}
