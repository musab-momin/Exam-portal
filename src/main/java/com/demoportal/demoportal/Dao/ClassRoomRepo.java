package com.demoportal.demoportal.Dao;

import com.demoportal.demoportal.Entities.ClassRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassRoomRepo extends JpaRepository<ClassRoom, Integer> 
{
    @Query(value = "insert into class_room(class_code, teacher_t_id) values(?1, ?2)", nativeQuery = true)
    public void roomEntry(String class_code, int t_id);

    @Query(value="select * from class_room where class_code=?1", nativeQuery = true)
    public ClassRoom findByCode(String code);

    @Query(value = "select * from class_room where teacher_t_id=?1", nativeQuery = true)
    public ClassRoom findByTeacherId(int tId);
}
