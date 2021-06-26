package com.demoportal.demoportal.Entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ClassRoom 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    private String classCode;
    @OneToOne
    private Teacher teacher;

    public ClassRoom() {
    }


    public ClassRoom(String classCode, Teacher teacher) {
        this.classCode = classCode;
        this.teacher = teacher;
    }    


    public int getcId() {
        return cId;
    }
    public void setcId(int cId) {
        this.cId = cId;
    }
    public String getClassCode() {
        return classCode;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }




    
    
}
