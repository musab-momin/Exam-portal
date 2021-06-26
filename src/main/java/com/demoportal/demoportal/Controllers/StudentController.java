package com.demoportal.demoportal.Controllers;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;


import com.demoportal.demoportal.Dao.AnswerRepo;
import com.demoportal.demoportal.Dao.ClassRoomRepo;
import com.demoportal.demoportal.Dao.PaperRepo;
import com.demoportal.demoportal.Dao.QuestionsRepo;
import com.demoportal.demoportal.Dao.StudentRepo;
import com.demoportal.demoportal.Dao.TeacherRepo;
import com.demoportal.demoportal.Entities.Answer;
import com.demoportal.demoportal.Entities.ClassRoom;
import com.demoportal.demoportal.Entities.Paper;
import com.demoportal.demoportal.Entities.Questions;
import com.demoportal.demoportal.Entities.Student;
import com.demoportal.demoportal.Entities.Teacher;
import com.demoportal.demoportal.Helper.General;
import com.demoportal.demoportal.Helper.Messsage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController 
{
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ClassRoomRepo classRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private PaperRepo paperRepo;
    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private QuestionsRepo qeustionRepo;


    @Autowired
    private BCryptPasswordEncoder encoder;


    @GetMapping("/register/student")
    public String registerStudent()
    {
        return "student";
    }
    @PostMapping("/save_student")
    public String saveStudent(@ModelAttribute("Student") Student student, HttpSession session)
    {
        try
        {
            ClassRoom roomCode = this.classRepo.findByCode(student.getClassCode());
            int teacherId = roomCode.getTeacher().gettId();
            Optional<Teacher> optionalTeacher = this.teacherRepo.findById(teacherId);
            Teacher ogTeacher = optionalTeacher.get();
        //System.out.println(ogTeacher.gettName()+" " +ogTeacher.gettId());
        //System.out.println("we get that important id: " + roomCode.getTeacher().gettId());
            student.setsRole("STUDENT");
            student.setTeacher(ogTeacher);
            student.setsPassword(this.encoder.encode(student.getsPassword()));
            Student savedStudent = this.studentRepo.save(student);
            System.out.println("This is it :)");
            System.out.println(savedStudent);
            session.setAttribute("message", new Messsage("succsess", "Register successfully"));
            
        }
        catch(Exception ae)
        {
            ae.printStackTrace();
            session.setAttribute("message", new Messsage("error", "Something went wrong"));
        }
        
        return "redirect:/register/student";
    }

    /*
        --------------------this is student login and after longin controllers
    */
    //login page navigator
    @GetMapping("/login/student")
    public String loginPage()
    {
        return "login";
    }
    
    //DEFAULT SUCCESS URL


    //login controller
    @PostMapping("process_login")
    public String processLogin(@RequestParam("studentemail") String email,
    @RequestParam("studentpassword") String password,
    HttpSession session, Model model)
    {
        System.out.println(email+"_--------------____"+password);
        try 
        {
            Student logedinStudent = this.studentRepo
            .getStudentByEmailAndPassword(email);
            System.out.println(logedinStudent.getsPassword());
            // System.out.println();
            // System.out.println(encoder.encode(password));
            // System.out.println("_____________________________________________________");
            System.out.println();
            if(logedinStudent!=null)
            {

                if(encoder.matches(password, logedinStudent.getsPassword()))
                {
                    System.out.println("Password Match :)");
                    session.setAttribute("logedinStudent", logedinStudent);
                    // int id = logedinStudent.getTeacher().gettId();
                    // List<Paper> papers = this.paperRepo.getPaperByTeacherId(id);
                    // System.out.println("_________________________");
                    // for(Paper p: papers)
                    //     System.out.println(p.getpName());
                    // System.out.println(papers.size());
                    // System.out.println("_________________________");
                    // model.addAttribute("paperList", papers);
                   
                    return "redirect:/student/home";

                }
                
                else 
                {
                    System.out.println("Password not matches..... :(");
                    session.setAttribute("message", new Messsage("error", "Bad Crendential"));
                    return "redirect:/student/home";
                }
            }
           
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return "redirect:index";
    }

    @GetMapping("/student/home")
    public String studentHome(HttpSession session, Model model)
    {
        if(session.getAttribute("logedinStudent")==null)
        {
            return "redirect:/login/student";
        }
        System.out.println("this got called");
        Student st = (Student) session.getAttribute("logedinStudent");
        int id = st.getTeacher().gettId();
        List<Paper> papers = this.paperRepo.getPaperByTeacherId(id);
        for(Paper p: papers)
        {
            System.out.println(p.getpName());
        }        // session.setAttribute("listOfPapers", papers);
        model.addAttribute("listOfPapers", papers);
        model.addAttribute("name", "Jhon Snow");

       
        return "student/base";
    }
   

    //for logout
    @GetMapping("/student/logout")
    public String studentLogout(HttpSession session)
    {
        System.out.println(session.getAttribute("logedinStudent"));
        session.removeAttribute("logedinStudent");
        System.out.println(session.getAttribute("logedinStudent"));
        return "redirect:/login/student";
    }


    //profile controller
    @GetMapping("/student/profile")
    public String studentProfile(Model model, HttpSession session)
    {

        Student logedStudent = (Student)session.getAttribute("logedinStudent");
        Optional<Student> op =  this.studentRepo.findById(logedStudent.getsId());
        Student studentDetail = op.get();
        String name =  studentDetail.getTeacher().gettName();

        model.addAttribute("title", "Profile");
        model.addAttribute("studentDetail", studentDetail);
        model.addAttribute("name", name);
        return "student/s_profile";
    }




    //exam hall
    @GetMapping("/student/paper/{paperId}")
    public String examHall(@PathVariable("paperId") int paperId, Model model, HttpSession session)
    {
        Student st = (Student)session.getAttribute("logedinStudent");


        if(st==null)
        {
            return "redirect:/student/home";
        }
        Teacher t =  st.getTeacher();
        List<Paper> paperList =  this.paperRepo.getPaperByTeacherId(t.gettId());

        General obj = new General();
        if(obj.identifyPaper(paperList, paperId)==true)
        {
            model.addAttribute("title", "Exam Hall");
            List<Questions> questionList = this.qeustionRepo.getByPaperId(paperId);
            model.addAttribute("questionList", questionList);
            model.addAttribute("currentPaperId", paperId);
            return "Student/exam_hall";
        }

       
        return "redirect:/student/home";
    }

    @PostMapping("/student/savepaper")
    @ResponseBody
    public String processPaper(@ModelAttribute("Answer") Answer ans, 
    @RequestParam("paperId") int paperId,
    HttpSession session)
    {

        //this.answerRepo.getAnsByStudentId(sId, pId)
        System.out.println("This is papaer id:" + paperId);
        Student s = (Student) session.getAttribute("logedinStudent");
        List<Answer> al = this.answerRepo.getAnsByStudentId(s.getsId(), paperId);
        System.out.println("this is the flag value: " + al.size());
        for(Answer a: al)
            System.out.println("This is answer: " + a.getAns() + " "+a.getStudent().getsName());
        if(al.size()==0)
        {
            Optional<Paper> option = this.paperRepo.findById(paperId);
        //System.out.println(paperId+" "+ans.getQuestionName()+"  "+ans.getAns());
            Student st = (Student)session.getAttribute("logedinStudent");
            ans.setStudent(st);
            ans.setPaper(option.get());
            System.out.println(ans.getQuestionName()+" "+ans.getAns()+" "+ans.getPaper().getpId()+" "+ans.getStudent().getsName());

            Answer answer = this.answerRepo.save(ans);
            System.out.println(answer.getStudent().getsEmail());
            return "success";
        }
        
        return "error";
    }

    
}
