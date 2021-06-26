package com.demoportal.demoportal.Entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sId;
    private String sName;
    private String sEmail;
    private String sPassword;
    private String sRole;
    private String classCode;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", orphanRemoval = true)
    private List<Answer> answers;


    public int getsId() {
        return sId;
    }
    public void setsId(int sId) {
        this.sId = sId;
    }
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    public String getsEmail() {
        return sEmail;
    }
    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }
    public String getsPassword() {
        return sPassword;
    }
    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public String getsRole() {
        return sRole;
    }
    public void setsRole(String sRole) {
        this.sRole = sRole;
    }
    public String getClassCode() {
        return classCode;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    
    
}
