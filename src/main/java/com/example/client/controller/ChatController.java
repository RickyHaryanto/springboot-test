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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.example.client.model.Notif;
import com.example.client.repository.NotifRepository;
import com.example.client.service.NotifService;

import com.example.client.model.Chat;
import com.example.client.model.User;
import com.example.client.repository.UserRepository;
import com.example.client.service.ChatService;
import com.example.client.service.UserService;
import javax.servlet.http.HttpSession;

@Controller
public class ChatController {
    @Autowired
	private ChatService service; 
	@Autowired
	private NotifService notifservice;
	@Autowired
	private NotifRepository notifrepo;	
	@Autowired
	private UserService service2;

	@RequestMapping("/list_chat.html")
	public String viewDaftarChat(Model model,HttpSession session) {
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		List<Chat> listchat = service.listAll(usr.getUser_id()+"");
		model.addAttribute("listchat", listchat);
		
		
		
		model.addAttribute("test",usr.getUser_id());
		return "list_chat";
	}

	
	@RequestMapping("/ask_chat.html")
	public String showNewChatPage(Model model) {
		Chat chat = new Chat();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("test",usr.getUser_id());

		
		
		
		model.addAttribute("chat", chat);
		
		return "ask_chat";
	}
	

    @RequestMapping(value = "/save_chat", method = RequestMethod.POST)
	public String saveChat(RedirectAttributes redirectAttributes, 
	ModelMap modelMap, 
	@RequestParam("chat_pertanyaan") String chat_pertanyaan,
	@RequestParam("chat_jawaban") String chat_jawaban
	) {

		Chat chat = new Chat();

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		
		modelMap.addAttribute("chat_pertanyaan", chat_pertanyaan);
		modelMap.addAttribute("chat_jawaban", chat_jawaban);
		

		chat.setChat_pertanyaan(chat_pertanyaan);
		chat.setChat_jawaban(chat_jawaban);
		chat.setUser_id(usr.getUser_id());
		
		service.save(chat);
		
		//Notif
		List lc = notifrepo.notifchat();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Pertanyaan baru diterima");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}

		return "redirect:/ask_chat.html?success";
	}
	
	

}