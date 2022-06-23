package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;

	public void basicModify(BlogVo vo) {
		sqlSession.update("blog.modify", vo);
	}

	public BlogVo getBlog(String id) {
		return sqlSession.selectOne("blog.getBlog", id);
	}

	public void insert(String id) {
		sqlSession.insert("blog.insertDefault", id);
	}
}