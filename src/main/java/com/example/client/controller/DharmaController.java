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
import com.example.client.model.Dharma;
import com.example.client.service.DharmaService;

@Controller
public class DharmaController {
	

    @Autowired
	private DharmaService service; 
	

	
	@RequestMapping("/daftar_dharma.html")
	public String viewDaftarDharma(Model model) {
		List listdharma = service.listAll();
		model.addAttribute("listdharma", listdharma);
		
		return "daftar_dharma";
	}

    

    
    
}