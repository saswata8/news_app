package com.stackroute.favouriteservice.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="news_article")
public class NewsArticle 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_id")
	private String userId;
	
	private String title;

	@Size(max = 350)
	private String description;

	@Size(max = 1100)
	private String content;

	@Column(name = "published_at")
	private String publishedAt;

	@Column(name = "source_website_name")
	private String sourceWebsiteName;

	@Size(max = 350)
	private String url;

	@Column(name = "url_to_image")
	@Size(max = 1000)
	private String urlToImage;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getSourceWebsiteName() {
		return sourceWebsiteName;
	}

	public void setSourceWebsiteName(String sourceWebsiteName) {
		this.sourceWebsiteName = sourceWebsiteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
}
