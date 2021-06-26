package com.demoportal.demoportal.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Paper 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pId;
    private String pName;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper", orphanRemoval = true)
    private List<Questions> questions;


    
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }
    public String getpName() {
        return pName;
    }
    public void setpName(String pName) {
        this.pName = pName;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public List<Questions> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    
    
}
