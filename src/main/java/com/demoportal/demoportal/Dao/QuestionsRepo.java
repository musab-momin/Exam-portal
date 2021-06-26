package com.demoportal.demoportal.Dao;

import java.util.List;

import com.demoportal.demoportal.Entities.Questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionsRepo extends JpaRepository<Questions, Integer> 
{
    @Query(value = "select * from questions where paper_p_id=?1", nativeQuery = true)
    public List<Questions> getByPaperId(int pId);
    
}
