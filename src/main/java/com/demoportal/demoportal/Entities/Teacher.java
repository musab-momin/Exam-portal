package com.demoportal.demoportal.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Teacher 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tId;
    private String tName;
    private String tEmail;
    private String tPassword;
    private String role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
    private ClassRoom classRoom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
    private List<Student> students;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", orphanRemoval = true)
    private List<Paper> papers;
    public int gettId() {
        return tId;
    }
    public void settId(int tId) {
        this.tId = tId;
    }
    public String gettName() {
        return tName;
    }
    public void settName(String tName) {
        this.tName = tName;
    }
    public String gettEmail() {
        return tEmail;
    }
    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }
    public String gettPassword() {
        return tPassword;
    }
    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }
    public ClassRoom getClassRoom() {
        return classRoom;
    }
    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public List<Paper> getPapers() {
        return papers;
    }
    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }
    @Override
    public String toString() {
        return "Teacher [classRoom=" + classRoom + ", papers=" + papers + ", students=" + students + ", tEmail="
                + tEmail + ", tId=" + tId + ", tName=" + tName + ", tPassword=" + tPassword + "]";
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    

    
}
