package com.financial.apply.service;

import com.financial.apply.dto.MemberProductRes;
import com.financial.apply.entity.Apply;
import com.financial.apply.repository.ApplyRepository;
import com.financial.apply.dto.ApplyRegistrationReq;
import com.financial.interest.entity.Interest;
import com.financial.interest.repository.InterestRepository;
import com.financial.member.dto.MemberAdapter;
import com.financial.member.entity.Member;
import com.financial.product.entity.Product;
import com.financial.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final ProductRepository productRepository;
    private final InterestRepository interestRepository;
    private final EntityManager em;

    @Transactional
    public String applyRegistration(MemberAdapter memberAdapter, ApplyRegistrationReq request) {
        Member member = memberAdapter.getMember();
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new IllegalStateException());
        Interest interest = interestRepository.findById(request.getInterestId()).orElseThrow(() -> new IllegalStateException());

        Apply apply = Apply.createApply(member, product, interest);

        Apply savedApply = applyRepository.save(apply);

        Apply findApply = applyRepository.findById(savedApply.getId()).get();

        if (findApply != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    public List<MemberProductRes> memberApply(Long memberId){
        return applyRepository.findByMemberId(memberId).stream()
                .map(MemberProductRes::fromApply)
                .collect(Collectors.toList());
    }

    public String deleteApply(MemberAdapter memberAdapter, Long applyId) {
        if (memberAdapter != null) {
            applyRepository.deleteById(applyId);
            return "success";
        } else {
            return "fail";
        }
    }
}

