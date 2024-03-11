package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserServiceImpl userserviceimpl;
	
	@Autowired
	SongServiceImpl service;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user)
	{
		//email taken from registration form
		String email = user.getEmail();

		//boolean if the email as entered in registration form is present in DB or not
		boolean status = userserviceimpl.emailExists(email);

		if(status == false)
		{
			userserviceimpl.addUser(user);
			System.out.println("User added");
		}
		else
		{
			System.out.println("User already exists.");
		}
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,@RequestParam("password") String password, HttpSession session, Model model)
	{
		if(userserviceimpl.validateUser(email,password)==true)
		{
			String role=userserviceimpl.getRole(email);

			session.setAttribute("email", email);

			if(role.equals("admin"))
			{
				return "adminhome";
			}
			else
			{ 
				User user=userserviceimpl.getUser(email);
				boolean sts=user.isIspremium();
				List<Song> fetch=service.fetchAllSongs();
				model.addAttribute("songs", fetch);

				model.addAttribute("ispremium",sts);

				return "customerhome";
			}
		}
		else
		{
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
}
