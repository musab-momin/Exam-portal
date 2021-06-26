package com.demoportal.demoportal.Dao;

import java.util.List;


import com.demoportal.demoportal.Entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepo extends JpaRepository<Student, Integer>
{
    @Query(value = "select * from student where teacher_t_id=?1", nativeQuery = true)
    public List<Student> getStudentByTeacherId(int id);
    @Query(value = "select * from student where s_email=?1", nativeQuery = true)
    public Student getStudentByEmailAndPassword(String email);
    
}
