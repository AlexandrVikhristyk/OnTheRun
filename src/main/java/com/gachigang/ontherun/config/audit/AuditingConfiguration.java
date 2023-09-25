package com.gachigang.ontherun.config.audit;

import com.gachigang.ontherun.service.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuration class enabling JPA auditing features with a custom {@link AuditorAware} bean.
 *
 * @see AuditorAware
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfiguration {

    /**
     * Creates and configures an {@link AuditorAware} bean that determines the current user for auditing.
     *
     * @return An {@link AuditorAware} implementation ({@link AuditorAwareImpl}) for tracking the auditor.
     * @see AuditorAwareImpl
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
