package com.june.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.june.bean.CrawlerPost;
import com.june.dao.CrawlerPostDao;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component
public class PostPipeline implements PageModelPipeline<CrawlerPost>{

	@Autowired
	private CrawlerPostDao crawlerPostDao;
	
	@Override
	public void process(CrawlerPost t, Task task) {
		System.out.println("insert start");
		crawlerPostDao.insert(t);
		System.out.println("insert end");
	}

}
