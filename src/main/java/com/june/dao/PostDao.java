package com.june.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.june.bean.PostVO;

import tk.mybatis.mapper.common.Mapper;


@Repository
public interface PostDao/* extends Mapper<Post>*/{

	List<PostVO> getPosts();
}
