package com.ohgimduir.jaray.security.jwt.helper;

import com.ohgimduir.jaray.member.domain.Member;
import com.ohgimduir.jaray.member.domain.MemberNotFoundException;
import com.ohgimduir.jaray.member.domain.MemberRepository;
import com.ohgimduir.jaray.security.jwt.consts.JwtType;
import com.ohgimduir.jaray.security.jwt.exception.WrongTokenTypeException;
import com.ohgimduir.jaray.security.jwt.properties.JwtProperties;
import com.ohgimduir.jaray.security.oauth.principal.CustomMemberDetails;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtHelper {

    private final MemberRepository memberRepository;
    private final JwtProperties jwtProperties;

    public String generateAccessToken(String socialId) {
        return generateToken(JwtType.ACCESS, socialId, jwtProperties.getAccessExpire(), jwtProperties.getAccessKey());
    }

    private String generateToken(JwtType jwtType, String socialId, long expire, String key) {
        System.out.println(key);
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, jwtType)
                .setSubject(socialId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Jws<Claims> getClaimsFromAccessToken(final String accessToken) {
        return getClaims(accessToken, jwtProperties.getAccessKey());
    }

    private Jws<Claims> getClaims(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(extractToken(token));
    }

    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaimsFromAccessToken(token);

        this.isWrongType(claims, JwtType.ACCESS);

        final Member member = memberRepository.findBySocialId(Long.parseLong(claims.getBody().getSubject()))
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        final CustomMemberDetails details = CustomMemberDetails.create(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public String extractTokenFromRequest(final HttpServletRequest request) {
        return extractToken(request.getHeader("Authorization"));
    }

    public void isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        if(!(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()))) {
            throw WrongTokenTypeException.EXCEPTION;
        }
    }

    private String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

}