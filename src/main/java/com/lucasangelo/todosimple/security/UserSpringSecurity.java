package com.lucasangelo.todosimple.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lucasangelo.todosimple.models.enums.ProfileEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String username, String password, Set<ProfileEnum> profileEnums) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = profileEnums.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(ProfileEnum profileEnum) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profileEnum.getDescription()));
    }

}
