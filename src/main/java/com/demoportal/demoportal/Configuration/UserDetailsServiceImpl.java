package com.demoportal.demoportal.Configuration;

import com.demoportal.demoportal.Dao.TeacherRepo;
import com.demoportal.demoportal.Entities.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {
        Teacher teacher = this.teacherRepo.getTeacherByEmail(email);
        if(teacher == null)
        {
            throw new UsernameNotFoundException("Teacher with this email is not exists");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(teacher);
        return customUserDetails;
    }
    
    
}
