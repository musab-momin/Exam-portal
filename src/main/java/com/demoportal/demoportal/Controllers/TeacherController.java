package com.demoportal.demoportal.Controllers;

import java.security.Principal;
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
import com.demoportal.demoportal.Helper.ClassCodeGenrator;
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
public class TeacherController 
{
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ClassRoomRepo classRepo;
    @Autowired
    private PaperRepo paperRepo;
    @Autowired
    private AnswerRepo answerRepo;


    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @GetMapping("/register/teacher")
    public String registerPage()
    {
        return "teacher";
    }


    @GetMapping("/Teacher/teacherbase")
    public String tBase()
    {
        return "Teacher/teacherbase";
    }

    @GetMapping("/signin")
    public String loginTeacher(Model model)
    {
        model.addAttribute("title", "Login");
        return "tLogin";
    }
    
    @PostMapping("/save_teacher")
    public String saveTeacher(@ModelAttribute("Teacher") Teacher teacher, HttpSession session)
    {
        //System.out.println(teacher.gettName()+" "+teacher.gettEmail()+"  "+teacher.gettPassword());
       try 
       {
        Teacher savedTeacher = teacherRepo.save(teacher);
        ClassCodeGenrator obj = new ClassCodeGenrator();
        String code = obj.randomAlphaNumeric();
        ClassRoom cRoom = new ClassRoom(code, savedTeacher);
        savedTeacher.setRole("Teacher");
        savedTeacher.setClassRoom(cRoom);
        savedTeacher.settPassword(this.encoder.encode(teacher.gettPassword()));
        ClassRoom savedRoom = classRepo.save(cRoom);
        System.out.println("so far so good"+ savedRoom);
        session.setAttribute("message", new Messsage("success", "Registered! login you'll get your class code.."));
        return "redirect:/register/teacher";
       } 
       catch (Exception ae) 
       {
           session.setAttribute("message", new Messsage("error", "Something went wrong"));
           ae.printStackTrace();
           
       }
       return "redirect:/register/teacher";
    }

    /*
    ---------------------After login teahcer controllers are here...........!
    */
    //teacher home controller
    @GetMapping("/Teacher/home")
    public String teacherHome(Model model, Principal principal)
    {
        model.addAttribute("title", "Home");
        // try
        // {
        //     Teacher tempo = this.teacherRepo.getTeacherByEmail("suchita344@gmail.com");
        //     System.out.println(tempo.gettId()+" "+ tempo.gettName()+" "+ tempo.getClassRoom().getClassCode());

        // }
        // catch(Exception ae)
        // {
        //     ae.printStackTrace();
        // }
        String email = principal.getName();
        Teacher logedinTeacher = this.teacherRepo.getTeacherByEmail(email);
        int id = logedinTeacher.gettId();
        List<Student> students = this.studentRepo.getStudentByTeacherId(id);
        model.addAttribute("relatedStudents", students);
        //fetching classcode
        ClassRoom activeRoom = this.classRepo.findByTeacherId(logedinTeacher.gettId());
        model.addAttribute("activeRoom", activeRoom);
        System.out.println(activeRoom.getTeacher().gettEmail()+" "+activeRoom.getClassCode());


        // for(Student s: students)
        // {
        //     System.out.println(s.getsEmail()+"  "+s.getsName());
        // }
        return "Teacher/home";
    }

    //page for creating paper
    @GetMapping("/Teacher/question")
    public String makePaper(Model model)
    {
        model.addAttribute("title", "Create Paper");
        return "Teacher/question";
    }

    //for creating paper
    @PostMapping("/teacher/create_paper")
    public String createPaper(@ModelAttribute("Paper") Paper paper
    , HttpSession session, Principal principal)
    {
       // System.out.println(paper.getpName()+"________"+paper.getTeacher());
        try
        {
            Teacher logedinTeacher = this.teacherRepo.getTeacherByEmail(principal.getName());
            paper.setTeacher(logedinTeacher);
            Paper createdPaper = this.paperRepo.save(paper);
            System.out.println(createdPaper.getpId()+"    "+createdPaper.getpName()+"      "
            +createdPaper.getTeacher().gettEmail());
            session.setAttribute("message", new Messsage("success", "Created Successfully..."));
            return "redirect:/Teacher/question";

        }
        catch(Exception ae)
        {
            session.setAttribute("message", new Messsage("error", "something went wrong"));
            ae.printStackTrace();
            return "redirect:/Teacher/question";
        }
    }
    @GetMapping("/Teacher/showpaper")
    public String showPaper(Model model, Principal principal)
    {

        Teacher teacher = this.teacherRepo.getTeacherByEmail(principal.getName());
        List<Paper> papers = this.paperRepo.getPaperByTeacherId(teacher.gettId());
        // System.out.println("_______________________________________________");
        // for(Paper p: papers)
        //     System.out.println(p.getpName());
        // System.out.println("_______________________________________________");
        model.addAttribute("paperList", papers);
        model.addAttribute("title", "Show Paper");
        return "Teacher/show_papers";
    }

    /** 
     * challenge start's main functionalty controller
     * add question to perticular paper
    */
    @GetMapping("/Teacher/addquestions/{paperId}")
    public String addQuestions(@PathVariable("paperId") int paperId, Model model, Principal principal)
    {
        //System.out.println(paperId);
        // List<Questions> questions = this.questionsRepo.getByPaperId(paperId);
        Teacher logedTeacher = this.teacherRepo.getTeacherByEmail(principal.getName());
        List<Paper> paperList = this.paperRepo.getPaperByTeacherId(logedTeacher.gettId());
        General obj = new General();
        if(obj.identifyPaper(paperList, paperId)==true)
        {
            model.addAttribute("title", "Add Questions");
            model.addAttribute("currentPaperId", paperId);
            return "Teacher/addquestion";
        }
        
        return "Teacher/home";
    }

    @PostMapping("/Teacher/processQuestion")
    @ResponseBody
    public String processQuestions(@ModelAttribute("Questions") Questions question, 
    Model model, @RequestParam("currentPaperId") int currentPaperId, Principal principal)
    {
        try
        {
            System.out.println(currentPaperId+"    "+question.getqName()+"   "+
            question.getOption1()+"      "+ question.getOption2()+"     "+
            question.getOption3()+"      "+question.getOption4());

            Optional<Paper> op = this.paperRepo.findById(currentPaperId);
            question.setPaper(op.get());
            System.out.println(this.questionsRepo.save(question));
            return "success";
        }
        catch(Exception ae)
        {
            ae.printStackTrace();
            return "error";
        }
    }

    //showing question paper to teacher
    @GetMapping("/Teacher/showquestionpaper/{paperId}")
    public String showQuestionPaper(Model model, @PathVariable("paperId") int paperId, Principal principal)
    {

        Teacher logedTeacher = this.teacherRepo.getTeacherByEmail(principal.getName());
        List<Paper> paperList = this.paperRepo.getPaperByTeacherId(logedTeacher.gettId());
        General obj = new General();
        if(obj.identifyPaper(paperList, paperId)==true)
        {
            model.addAttribute("title", "Question paper");
            model.addAttribute("id", paperId);
            List<Questions> questions =  this.questionsRepo.getByPaperId(paperId);
            model.addAttribute("questions", questions);
            return "Teacher/show_question_paper";
        }

        
        
        // for(Questions q: questions)
        // {
        //     System.out.println(q.getqName());
        //     System.out.print(q.getOption1()+"  "+q.getOption2()+"  "+q.getOption3()+"  "+q.getOption4());
        //     System.out.println();
        // }
        
        return "redirect:/Teacher/home";
    }

    @GetMapping("/Teacher/view/{studentId}")
    public String view(@PathVariable("studentId") int studentId, Model model, Principal principal)
    {
        Teacher t = this.teacherRepo.getTeacherByEmail(principal.getName());
        List<Student> sList = t.getStudents();
        General obj = new General();
        if(obj.identifyStudent(sList, studentId)==true)
        {
            
            List<Paper> p = this.paperRepo.getPaperByTeacherId(t.gettId());
            model.addAttribute("sid", studentId);
            model.addAttribute("plist", p);
            return "Teacher/view";
        }
       
        return "redirect:/Teacher/home";
    }

    //view the single student answer sheet
    @GetMapping("/Teacher/answersheet/{paperId}/{studentId}")
    public String answerSheet(@PathVariable("paperId") int paperId,  
    @PathVariable("studentId") int studentId, Model model, Principal principal)
    {
        System.out.println(paperId+" "+studentId);
        Teacher logedTeacher = this.teacherRepo.getTeacherByEmail(principal.getName());
        List<Student> sList = logedTeacher.getStudents();
        List<Paper> paperList = this.paperRepo.getPaperByTeacherId(logedTeacher.gettId());
        General obj = new General();
        if(obj.identifyPaper(paperList, paperId)==true && obj.identifyStudent(sList, studentId))
        {
            List<Answer> a = this.answerRepo.getAnsByStudentId(studentId, paperId);
            List<Questions> q = this.questionsRepo.getByPaperId(paperId);



            int marks = obj.marksCounter(a, q);

            model.addAttribute("ansList", a);
            model.addAttribute("optionList", q);
            model.addAttribute("marks", marks);
            System.out.println("Total marks obtain: "+ marks);
            System.out.println("size of answer list " +  a.size()+" "+"Size of question list " + q.size());
           
           


            return "Teacher/answersheet";
        }

        return "redirect:/Teacher/home";
       
    }

    //profile contorller
    @GetMapping("/Teacher/profile")
    public String profile(Model model, Principal principal)
    {
        Teacher t = this.teacherRepo.getTeacherByEmail(principal.getName());
        List<Student> s = t.getStudents();
        System.out.println(s.size());

        //System.out.println(t);
        model.addAttribute("teacher", t);
        model.addAttribute("slist", s);
        model.addAttribute("title", "Profile");
        return "Teacher/profile";
    }

    //for logout




}
