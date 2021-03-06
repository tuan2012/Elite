package com.example.demo.security;

import com.example.demo.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
public class UserDetailImpl implements UserDetails {

    private User user;

    public UserDetailImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        //list of permission
        if (this.user.getRole().getRoleDetails() != null)
            this.user.getRole().getRoleDetails().forEach(roleDetail -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleDetail.getPermission().getName());
                list.add(grantedAuthority);
            });
//         list of roles
        if (this.user.getRole() != null) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + this.user.getRole().getName());
            list.add(grantedAuthority);
        }
        return list;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
}
