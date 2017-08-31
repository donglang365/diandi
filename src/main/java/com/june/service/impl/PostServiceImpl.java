package com.june.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.june.bean.PostVO;
import com.june.dao.PostDao;
import com.june.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	@Override
	public List<PostVO> getPosts() {
		return postDao.getPosts();
	}
	
	
}
