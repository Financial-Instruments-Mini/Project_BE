package com.financial.member.service;

import com.financial.interest.entity.Interest;
import com.financial.interest.repository.InterestRepository;
import com.financial.member.dto.*;
import com.financial.member.entity.Member;
import com.financial.member.entity.RefreshToken;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.member.jwt.JwtUtils;
import com.financial.member.repository.MemberRepository;
import com.financial.member.repository.RefreshTokenRepository;
import com.financial.product.entity.enums.DueDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final RefreshTokenRepository refreshTokenRepository;

    private final InterestRepository interestRepository;

    @Transactional
    public MemberResponse signup(MemberRequest memberRequest) {
        if (isDuplicate(memberRequest.getEmail())) {
            return null;
        }
        memberRequest.encodePassword(passwordEncoder);
        Member member = memberRepository.save(memberRequest.toEntity());
        MemberResponse memberResponse = MemberResponse.from(member);
        TokenDto tokenDto = issueToken(member);
        memberResponse.setTokenDto(tokenDto);

        RefreshToken originRefreshToken = refreshTokenRepository.findByMemberId(member.getId()).orElse(null);
        updateToken(member, tokenDto, originRefreshToken);
        return memberResponse;
    }

    @Transactional
    public MemberResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (member == null) {
            return null;
        }

        MemberResponse memberResponse = MemberResponse.from(member);

        if (passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            TokenDto tokenDto = issueToken(member);
            memberResponse.setTokenDto(tokenDto);

            RefreshToken originRefreshToken = refreshTokenRepository.findByMemberId(member.getId()).orElse(null);
            updateToken(member, tokenDto, originRefreshToken);
        }
        return memberResponse;
    }

    private void updateToken(Member member, TokenDto tokenDto, RefreshToken originRefreshToken) {
        if (originRefreshToken == null) {
            RefreshToken refreshToken = RefreshToken.builder().memberId(member.getId()).token(tokenDto.getRefreshToken()).build();
            refreshTokenRepository.save(refreshToken);
        } else {
            originRefreshToken.updateToken(tokenDto.getRefreshToken());
        }
    }

    @Transactional
    public TokenDto reIssue(String refreshToken) {
        if (!jwtUtils.isValidToken(refreshToken)) {
            throw new IllegalArgumentException("refresh토큰 만료됨");
        }

        Long memberId = Long.valueOf(jwtUtils.getSubject(refreshToken));
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        RefreshToken originRefreshToken = refreshTokenRepository.findByMemberId(memberId).orElseThrow(
                () -> new IllegalArgumentException("토큰을 찾을 수 없습니다.")
        );

        if (!originRefreshToken.getToken().equals(refreshToken)) {
            throw new IllegalArgumentException("리프레시 토큰이 불일치 합니다.");
        }

        TokenDto tokenDto = issueToken(member);
        originRefreshToken.updateToken(tokenDto.getRefreshToken());

        return tokenDto;
    }

    public TokenDto issueToken(Member member) {
        Date date = new Date();
        String accessToken = jwtUtils.createToken(member);
        String refreshToken = jwtUtils.createRefreshToken(member.getId());

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessStartTime(String.valueOf(date))
                .accessExpirationTime(jwtUtils.getExpiration(accessToken))
                .build();
    }

    @Transactional
    public void updateInfo(MemberUpdateRequest request, Long memberId) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        memberRepository.findById(memberId).ifPresent(member -> member.updateMember(request));
    }

    public boolean isDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Slice<MemberRecommendDTO> getMemberRecommend(Pageable pageable, MemberAdapter memberAdapter) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Member member = memberAdapter.getMember();
        ProductType loginMemberProductType = member.getSurvey().getProductType();
        Job loginMemberJob = member.getSurvey().getJob();

        if (loginMemberProductType == ProductType.DEPOSIT_AND_SAVING) {
            Slice<Interest> findMemberRecommend = interestRepository.findAllByDueDateAndProductJob(pageRequest, DueDate.TWENTY_FOUR, loginMemberJob);
            Slice<MemberRecommendDTO> findMemberRecommendDTO = findMemberRecommend.map(interest -> new MemberRecommendDTO(interest));
            return findMemberRecommendDTO;
        }

        Slice<Interest> findMemberRecommend = interestRepository.findAllByDueDateAndProductProductTypeAndProductJob(pageRequest, DueDate.TWENTY_FOUR, loginMemberProductType, loginMemberJob);
        Slice<MemberRecommendDTO> findMemberRecommendDTO = findMemberRecommend.map(interest -> new MemberRecommendDTO(interest));
        return findMemberRecommendDTO;
    }
}
