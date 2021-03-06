package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;	

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	

	@GetMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result,  Model model) {
		if(result.hasErrors()) {			
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(userVo);
		String id = userVo.getId();
		blogService.insert(id);
		categoryService.insert(id);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/auth")
	public void auth() {
	}

	@RequestMapping("/logout")
	public void logout() {
	}

	@Auth
	@GetMapping("/update")
	public String update(@AuthUser UserVo authUser, Model model) {
//		System.out.println(authUser);
		String id = authUser.getId();
		UserVo userVo = userService.getUser(id);
		model.addAttribute("userVo", userVo);
		return "user/update";
	}

	@Auth
	@PostMapping("/update")
	public String update(@AuthUser UserVo authUser, UserVo vo) {
		vo.setId(authUser.getId());
		userService.updateUser(vo);
		authUser.setName(vo.getName());

		return "redirect:/user/update";
	}
}
