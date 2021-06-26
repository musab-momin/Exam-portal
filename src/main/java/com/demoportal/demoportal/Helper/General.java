package com.demoportal.demoportal.Helper;


import java.util.List;

import com.demoportal.demoportal.Entities.Answer;
import com.demoportal.demoportal.Entities.Paper;
import com.demoportal.demoportal.Entities.Questions;
import com.demoportal.demoportal.Entities.Student;

public class General 
{
    public boolean identifyPaper(List<Paper> paperList, int pId)
    {
        boolean flag = false;
        for(int i=0; i<paperList.size(); i++)
        {
            Paper p = paperList.get(i);
            if(p.getpId()==pId)
            {
                flag = true;
                return flag;
            }
        }
        return flag;
    }
    public boolean identifyStudent(List<Student> sList, int sId)
    {
        boolean flag = false;
        for(int i=0; i<sList.size(); i++)
        {
            Student s = sList.get(i);
            if(s.getsId()==sId)
            {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    public int marksCounter(List<Answer> a, List<Questions> q)
    {
        int marks = 0;
        for(int i=0; i<a.size(); i++)
        {
            Answer an = a.get(i);
            for(int j=0; j<q.size(); j++)
            {
                Questions que = q.get(j);
                System.out.println(an.getAns()+" "+que.getOptionC());
                if(an.getAns().equals(que.getOptionC()))
                    marks++;
            }
        }


        return marks;
    }

    
}
