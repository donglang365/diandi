package com.june.bean;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://www.191.cn/news/*")
@ExtractBy("//div[@class='article']")
public class CrawlerPost {

	private Long id;
	
	@ExtractBy(value="//h2[@class='article-title']/html()",notNull=true)
	private String title;
	
	@ExtractBy(value="//div[@class='article-content']/html()",notNull=true)
	private String content;
	
//	@ExtractBy(value="//div[@class='article']/img/src")
	private String imgUrl;
	
	
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
