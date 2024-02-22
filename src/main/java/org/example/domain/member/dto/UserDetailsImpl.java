package org.example.domain.member.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsImpl extends User {
    public UserDetailsImpl(String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
