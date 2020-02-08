package com.example.boxybox.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Artsiom Andryianau
 * @version 1.0
 */
@Entity
@Table(name="usr")
public class User implements UserDetails {

    /**
     * field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * field
     */
    private String username;

    /**
     * field
     */
    private String password;

    /**
     * field
     */
    private boolean active;

    /**
     * field
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    /*
     * getter/ setter method
     */
    public Long getId() {
        return id;
    }

    /*
     * getter/ setter method
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * getter/ setter method
     */
    public String getUsername() {
        return username;
    }

    /*
     * getter/ setter method
     */
    public boolean isAdmin(){
        return  roles.contains(Role.ADMIN);
    }

    /*
     * getter/ setter method
     */
    public boolean isDeliverer(){
        return  roles.contains(Role.DELIVERER);
    }

    /*
     * getter/ setter method
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * getter/ setter method
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * getter/ setter method
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * getter/ setter method
     */
    @Override
    public boolean isEnabled() {
        return isActive();
    }

    /*
     * getter/ setter method
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * getter/ setter method
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /*
     * getter/ setter method
     */
    public String getPassword() {
        return password;
    }
    /*
     * getter/ setter method
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * getter/ setter method
     */
    public boolean isActive() {
        return active;
    }

    /*
     * getter/ setter method
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /*
     * getter/ setter method
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /*
     * getter/ setter method
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
