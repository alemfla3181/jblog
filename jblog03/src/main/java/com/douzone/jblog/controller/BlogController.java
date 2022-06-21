package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;

	@Autowired
	private BlogService blogService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(
			@PathVariable("id") String id, 
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {

		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		} 
			

		
		List<CategoryVo> categorylist = categoryService.findAll(id);
		model.addAttribute("categorylist", categorylist);
//		System.out.println(categorylist);
		
		List<PostVo> postlist = postService.findAll(id, categoryNo);
		model.addAttribute("postlist", postlist);
//		System.out.println(postlist);
		
		if(categoryNo == 0L)
			categoryNo = categoryService.getCategory(id).getNo();
		PostVo postvo = postService.findByNo(categoryNo, postNo);
		model.addAttribute("postvo", postvo);
		
		BlogVo blogvo = blogService.getBlog(id);
//		model.addAttribute("blogvo", blogvo);
		servletContext.setAttribute("blogvo", blogvo);
//		System.out.println(blogvo);
		
		model.addAttribute("id", id);
		return "blog/main";				
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasicbasic(
			@PathVariable("id") String id) {
		return "blog/admin/basic";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminbasic(
			@RequestParam(value = "file") MultipartFile multipartFile, 
			@ModelAttribute BlogVo blogvo,
			Model model) {
		String url = fileUploadService.restore(multipartFile);
		blogvo.setLogo(url);
		blogService.basicModify(blogvo);
		model.addAttribute("blogvo", blogvo);
		return "redirect:/" + blogvo.getBlog_id();
	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String category(
			@PathVariable("id") String id, Model model) {
		List<CategoryVo> categorylist = categoryService.findAll(id);
		model.addAttribute("categorylist", categorylist);
		return "blog/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String category(
			@PathVariable("id") String id, 
			@ModelAttribute CategoryVo categoryvo) {
		categoryvo.setBlog_id(id);
		categoryService.insert(categoryvo);
		return "redirect:/" + id + "/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/category/delete/{no}", method = RequestMethod.GET)
	public String delete(
			@PathVariable("id") String id, 
			@PathVariable("no") Long no) {
		categoryService.delete(no);
		return "redirect:/" + id + "/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String write(
			@PathVariable("id") String id, Model model) {
		List<CategoryVo> categorylist = categoryService.findAll(id);
		model.addAttribute("categorylist", categorylist);
		return "blog/admin/write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(
			@PathVariable("id") String id, 
			@RequestParam("category") String category,
			@ModelAttribute PostVo postvo) {
		postService.insert(id, category, postvo);
		return "redirect:/" + id;
	}
}
