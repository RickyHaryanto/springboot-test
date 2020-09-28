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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.model.User;
import com.example.client.service.UserService;
import com.example.client.model.Diklat;
import com.example.client.service.DiklatService;

import org.springframework.security.core.context.SecurityContextHolder;
@Controller
public class DiklatController {
	int currentID = 0;
	
	@Autowired
	private DiklatService service; 

	@Autowired
	private UserService service2;

	@RequestMapping("/daftar_diklat.html")
		public String viewDaftarDiklat(Model model) {
		List listDiklat = service.listAll();
		model.addAttribute("listDiklat", listDiklat);
		
		
		//show active diklat
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
			
		List listDiklat2 = service.listAll2(usr.getUser_id());
		model.addAttribute("listDiklat2", listDiklat2);
		
		return "daftar_diklat";
	}


	

}
