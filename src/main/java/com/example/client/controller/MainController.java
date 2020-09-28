package com.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue.List;

import com.example.client.model.User;
import com.example.client.service.UserService;
import com.example.client.repository.NotifRepository;

@Controller
public class MainController {
	@Autowired
	private UserService service2;
    @Autowired
    private NotifRepository select2;
    
	@RequestMapping("/")
    public String root() {
        try{

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            boolean hasUserRole = authentication.getAuthorities().stream()
                      .anyMatch(r -> r.getAuthority().equals("Umat"));
            if (hasUserRole == true){
                return "home";
            }

            Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
            boolean hasUserRole2 = authentication2.getAuthorities().stream()
                      .anyMatch(r -> r.getAuthority().equals("User"));
            if (hasUserRole == true){
                return "welcome";
            }
                   
        }
        catch(Exception e){
        }
        return "login";
    }

    @GetMapping("/home.html")
    public String index(Model model,HttpSession session) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("halo",usr.getNama());
        return "home";
    }

    @GetMapping("/welcome.html")
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping("/index.html")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping("/service")
    @ResponseBody
    public java.util.List service() {
        //JSONObject obj=new JSONObject();
        //obj.putString("test","");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
        java.util.List lc = select2.select(usr.getUser_id()+"");
        return lc;
    }

    @GetMapping("/logout.html")
    public String logout(Model model,HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }

    //@RequestMapping("/sw.js")
    //public String ckejs(){
    //  return "home.html";
    //}
}
