package com.june.bean;

import java.util.Date;
import java.util.List;



public class PostVO {

	private String avatar;
	private String uuid;
	private Long postId;
	private String imgUrl;
	private String content;
	private String shortContent;
	private Date createTime;
	private String title;
	private int viewCount;
	private int commentCount;
	private int supportCount;
	private int kind;
	private int system;
	private String nickname;//昵称
	private String logoFileName;//头像	
	private String[] imgList;//图片
	private Long userId;
	
	
	public String[] getImgList() {
		return imgList;
	}

	

	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public void setImgList(String[] imgList) {
		this.imgList = imgList;
	}
	private List<ImageVO> imgUrlList; 
	
	public String getImages() {
		if(imgUrlList==null || imgUrlList.size() < 1) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(ImageVO image:imgUrlList) {
			sb.append(image.getUrl()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	

	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public List<ImageVO> getImgUrlList() {
		return imgUrlList;
	}
	public void setImgUrlList(List<ImageVO> imgUrlList) {
		this.imgUrlList = imgUrlList;
	}
	public String getLogoFileName() {
		return logoFileName;
	}
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getShortContent() {
		return shortContent;
	}
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getSupportCount() {
		return supportCount;
	}
	public void setSupportCount(int supportCount) {
		this.supportCount = supportCount;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getSystem() {
		return system;
	}
	public void setSystem(int system) {
		this.system = system;
	}
	
	


}
