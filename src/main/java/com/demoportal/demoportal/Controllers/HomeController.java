package com.demoportal.demoportal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{
    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("title", "VE-portal");
        return "index";

    }
    @GetMapping("/base")
    public String base()
    {
        return "base";
    }

}