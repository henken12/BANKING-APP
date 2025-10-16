package com.eazybytes.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImple implements AuditorAware<String> {
    /**
     * returns the current auditor of the application
     * @return the current auditor
     */

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Account_MS");
    }
}
