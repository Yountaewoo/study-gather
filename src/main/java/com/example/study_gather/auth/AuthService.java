package com.example.study_gather.auth;

import com.example.study_gather.auth.dto.LoginRequest;
import com.example.study_gather.auth.dto.LoginSuccess;
import com.example.study_gather.auth.dto.SignupRequest;
import com.example.study_gather.auth.dto.SignupResponse;
import com.example.study_gather.common.errorCode.AuthErrorCode;
import com.example.study_gather.common.errorCode.MemberErrorCode;
import com.example.study_gather.common.exception.BusinessException;
import com.example.study_gather.common.security.JwtProvider;
import com.example.study_gather.member.Member;
import com.example.study_gather.member.MemberRepository;
import com.example.study_gather.member.dto.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignupResponse register(SignupRequest request) {

        String email = request.memberEmail().trim().toLowerCase();

        if (memberRepository.existsByUserLoginIdAndDeletedAtIsNull(request.userLoginId())) {
            throw new BusinessException(MemberErrorCode.DUPLICATE_LOGIN_ID);
        }

        if (memberRepository.existsByMemberEmailAndDeletedAtIsNull(email)) {
            throw new BusinessException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        if (memberRepository.existsByNicknameAndDeletedAtIsNull(request.nickname())) {
            throw new BusinessException(MemberErrorCode.DUPLICATE_NICKNAME);
        }

        String rawPassword = request.password();

        String encodedPassword = passwordEncoder.encode(rawPassword);

        Member member = Member.createMember(
                request.userLoginId(),
                request.memberName(),
                request.memberEmail(),
                request.nickname(),
                request.memberBirth(),
                request.memberGender(),
                encodedPassword);

        Member saved = memberRepository.save(member);

        return SignupResponse.of(saved);
    }

    @Transactional
    public LoginSuccess login(LoginRequest request) {

        Optional<Member> optional = memberRepository.findByUserLoginIdAndDeletedAtIsNull(request.identifier());
        Member member = optional.orElseThrow(() -> new BusinessException(AuthErrorCode.INVALID_CREDENTIALS));

        if (!member.isActive()) {
            throw new BusinessException(AuthErrorCode.INVALID_CREDENTIALS);
        }

        if (!passwordEncoder.matches(request.password(), member.getPasswordHash())) {
            throw new BusinessException(AuthErrorCode.INVALID_CREDENTIALS);
        }

        Long accessTokenMinutes = 60L;
        Long refreshTokenDays = 7L;

        String accessToken = jwtProvider.createAccessToken(member.getId(), member.getUserLoginId(), member.getRole().toString(), accessTokenMinutes);
        String refreshToken = jwtProvider.createRefreshToken(member.getId(), member.getRole().toString(), refreshTokenDays);

        MemberInfo memberInfo = MemberInfo.of(member);

        return new LoginSuccess(accessToken, refreshToken, accessTokenMinutes, refreshTokenDays, memberInfo);
    }

    @Transactional
    public void logout(String accessToken, String refreshToken) {

        if (accessToken == null && refreshToken == null) {
            throw new BusinessException(AuthErrorCode.AUTH_REQUIRED);
        }

        addTokenToBlacklist(accessToken);

        addTokenToBlacklist(refreshToken);
    }

    private void addTokenToBlacklist(String token) {
        if (token == null || !jwtProvider.isValid(token)) {
            return;
        }

        Date expirationDate = jwtProvider.getExpirationDate(token);
        if (expirationDate == null || expirationDate.getTime() <= System.currentTimeMillis()) {
            return;
        }
    }
}
