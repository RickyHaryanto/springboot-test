package com.example.client.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.client.model.User;
import com.example.client.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.model.SMN;
import com.example.client.service.SMNService;

@Controller
public class SMNController {
    @Autowired
	private SMNService service; 
	
		
	@Autowired
	private UserService service2;

	@RequestMapping(value = "/save_smn", method = RequestMethod.POST)
	public String saveSMN(@ModelAttribute("smn") SMN smn) {
		service.save(smn);
		
		return "redirect:/list_nilai.html";
	}

	@RequestMapping("/list_nilai.html")
	public String viewDaftarNilai(Model model) {
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		List listsmn = service.listAll(usr.getUser_id()+"");
		model.addAttribute("listsmn", listsmn);
		model.addAttribute("test",usr.getUser_id());
		return "list_nilai";
	}

    @RequestMapping("/add_SMN.html")
	public String showNewSMNPage(Model model) {
		SMN smn = new SMN();
		model.addAttribute("smn", smn);
		
		return "add_SMN";
    }
    
	@RequestMapping("/edit_smn.html/{smn_id}")
	public ModelAndView showEditSMNPage(@PathVariable(name = "smn_id") int smn_id) {
		ModelAndView mav = new ModelAndView("edit_smn.html");
		SMN smn = service.get(smn_id);
		mav.addObject("smn", smn);
		return mav;
	}
}