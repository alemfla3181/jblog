package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(String id, String categoryName, PostVo postvo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("postvo", postvo);		
		map.put("categoryName", categoryName);
		sqlSession.insert("post.insert", map);
	}

	public List<PostVo> findAll(String id, Long categoryNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("category_no", categoryNo);		
		return sqlSession.selectList("post.findAll", map);
	}
	
	public PostVo findByNo(Long categoryNo, Long postNo) {
		PostVo vo = new PostVo();
		vo.setCategory_no(categoryNo);
		vo.setNo(postNo);
		return  sqlSession.selectOne("post.findByNo", vo);
	}
}
