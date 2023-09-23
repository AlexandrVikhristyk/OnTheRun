package com.gachigang.ontherun.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Provides the current auditor for auditing in a Spring Security-enabled application.
 *
 * <p>
 * This service retrieves the current authentication information from the Spring Security context
 * to determine the current user.
 *
 * @see AuditorAware
 */
@Service
public class AuditorAwareImpl implements AuditorAware<String>  {


    // TODO: Review Spring Security configuration to resolve "anonymousUser" issue.
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return Optional.of(authentication.getName());
        } else {
            return Optional.of("system");
        }
    }
}
