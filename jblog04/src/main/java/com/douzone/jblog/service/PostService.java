package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public void insert(String id, String categoryName, PostVo postvo) {
		postRepository.insert(id, categoryName, postvo);
	}

	public List<PostVo> findAll(String id, Long categoryNo) {
		return postRepository.findAll(id, categoryNo);
	}

	public PostVo findByNo(Long categoryNo, Long postNo) {
		return postRepository.findByNo(categoryNo, postNo);
	}
	

}
