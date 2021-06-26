package com.demoportal.demoportal.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Questions 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qId;
    private String qName;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String optionC;
    @ManyToOne
    private Paper paper;

    
    public String getOptionC() {
        return optionC;
    }
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public int getqId() {
        return qId;
    }
    public void setqId(int qId) {
        this.qId = qId;
    }
    public String getqName() {
        return qName;
    }
    public void setqName(String qName) {
        this.qName = qName;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public String getOption4() {
        return option4;
    }
    public void setOption4(String option4) {
        this.option4 = option4;
    }
    public Paper getPaper() {
        return paper;
    }
    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    

    
}
