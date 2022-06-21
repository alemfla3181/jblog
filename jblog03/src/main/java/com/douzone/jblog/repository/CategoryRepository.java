package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public void insert(CategoryVo categoryvo) {
		sqlSession.insert("category.insert", categoryvo);
	}

	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}
	
	public CategoryVo getCategory(String id) {
		return sqlSession.selectOne("category.getCategory", id);
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
	}

	public void insert(String id) {
		sqlSession.insert("category.insertDefault", id);
	}

}
