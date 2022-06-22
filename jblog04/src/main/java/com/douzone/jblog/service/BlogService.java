package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	public void basicModify(BlogVo vo) {
		blogRepository.basicModify(vo);
	}
	
	public BlogVo getBlog(String id) {
		return blogRepository.getBlog(id);
	}

	public void insert(String id) {
		blogRepository.insert(id);
	}
	

}
