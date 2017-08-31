package com.june.dao;

import org.springframework.stereotype.Repository;

import com.june.bean.CrawlerPost;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CrawlerPostDao extends Mapper<CrawlerPost>{

}
