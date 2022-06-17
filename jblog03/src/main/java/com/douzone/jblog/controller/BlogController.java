package com.douzone.jblog.controller;

import java.util.Optional;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2) {
		
		Long categoryNo = 0L;
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		}
		
		categoryService.getCategories(id, categoryNo);
		
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		
	}
}
