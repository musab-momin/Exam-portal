package com.demoportal.demoportal.Configuration;

import java.util.Collection;
import java.util.List;
import com.demoportal.demoportal.Entities.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails
{
    private static final long serialVersionUID = 1L;
    private Teacher teacher;

    public CustomUserDetails(Teacher teacher)
    {
        this.teacher = teacher;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        SimpleGrantedAuthority simpleGrantedAuthority =  new SimpleGrantedAuthority(this.teacher.getRole());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {  
        return teacher.gettPassword();
    }

    @Override
    public String getUsername() {
        return teacher.gettEmail();
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
