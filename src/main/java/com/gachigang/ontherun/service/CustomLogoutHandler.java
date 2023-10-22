package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Token;
import com.gachigang.ontherun.persistence.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.gachigang.ontherun.common.ApplicationConstants.Security.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");

        if (Optional.ofNullable(authHeader).orElse("").startsWith(TOKEN_PREFIX)) {
            return;
        }
        final String jwt = authHeader.substring(TOKEN_PREFIX.length());
        Token storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}
