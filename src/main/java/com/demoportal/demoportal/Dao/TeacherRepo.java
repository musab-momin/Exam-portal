package com.demoportal.demoportal.Dao;

import com.demoportal.demoportal.Entities.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TeacherRepo extends JpaRepository<Teacher, Integer> 
{
    @Query(value = "select * from teacher where t_email = ?1", nativeQuery=true)
    public Teacher getTeacherByEmail(String email);


    
}