package com.demoportal.demoportal.Dao;

import java.util.List;

import com.demoportal.demoportal.Entities.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepo extends JpaRepository<Answer, Integer> 
{
    @Query(value = "select * from answer where student_s_id=?1 and paper_p_id=?2", nativeQuery = true)
    public List<Answer> getAnsByStudentId(int sId, int pId);


    @Query(value = "select * from answer where student_s_id=?", nativeQuery = true)
    public List<Answer> checkAnswer(int sId);
    
}
