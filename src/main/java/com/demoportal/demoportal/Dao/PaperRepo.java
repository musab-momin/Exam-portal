package com.demoportal.demoportal.Dao;

import java.util.List;

import com.demoportal.demoportal.Entities.Paper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaperRepo extends JpaRepository<Paper, Integer>
{
    @Query(value = "select * from paper where teacher_t_id=?1", nativeQuery = true)
    public List<Paper> getPaperByTeacherId(int id);
    
}
