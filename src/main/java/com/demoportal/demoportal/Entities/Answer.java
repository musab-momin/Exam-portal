package com.demoportal.demoportal.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aId;
    private String questionName;
    private String ans;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Paper paper;


    
    public int getaId() {
        return aId;
    }
    public void setaId(int aId) {
        this.aId = aId;
    }
    public String getQuestionName() {
        return questionName;
    }
    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
    public String getAns() {
        return ans;
    }
    public void setAns(String ans) {
        this.ans = ans;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Paper getPaper() {
        return paper;
    }
    public void setPaper(Paper paper) {
        this.paper = paper;
    }


    

    
}
