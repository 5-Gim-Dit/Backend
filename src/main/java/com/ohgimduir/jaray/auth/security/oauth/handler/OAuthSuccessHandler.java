package com.ohgimduir.jaray.auth.security.oauth.handler;

import com.ohgimduir.jaray.member.domain.entity.Member;
import com.ohgimduir.jaray.auth.cookie.CookieAuthorizationRequestRepository;
import com.ohgimduir.jaray.auth.cookie.CookieManager;
import com.ohgimduir.jaray.auth.jwt.helper.JwtHelper;
import com.ohgimduir.jaray.auth.security.oauth.principal.CustomMemberDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.ohgimduir.jaray.auth.cookie.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtHelper jwtHelper;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final CookieManager cookieManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        clearAuthenticationAttributesAndCookies(request);

        super.getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        isNotMatchedUri(cookieManager.getCookie(REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue));

        Member member = ((CustomMemberDetails) authentication.getPrincipal()).getMember();

        String socialId = String.valueOf(member.getSocialId());

        final String accessToken = jwtHelper.generateAccessToken(socialId);

        return UriComponentsBuilder.fromUriString("http://localhost:3000/callback")
                .queryParam("accessToken", accessToken)
                .build().toUriString();
    }

    private void clearAuthenticationAttributesAndCookies(HttpServletRequest request) {
        super.clearAuthenticationAttributes(request);

        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies();
    }

    private void isNotMatchedUri(final Optional<String> redirectUri) {
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("redirect URIs are not matched");
        }
    }

    private boolean isAuthorizedRedirectUri(final String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create("/login/success");

        return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort();
    }

}