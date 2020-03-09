package com.example.boxybox.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Artsiom Andryianau
 *
 */
public enum Role implements GrantedAuthority {
    USER, DELIVERER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
