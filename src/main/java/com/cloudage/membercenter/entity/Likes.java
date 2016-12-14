package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Likes {
	@Embeddable
	public static class Key implements Serializable
	{
		User user;
		Article article;
		@ManyToOne(optional=false)
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@ManyToOne(optional=false)
		public Article getArticle() {
			return article;
		}
		public void setArticle(Article article) {
			this.article = article;
		}
	}
	Key id;
	Date creataDate;
	@EmbeddedId
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	@Column(updatable=false)
	public Date getCreataDate() {
		return creataDate;
	}
	public void setCreataDate(Date creataDate) {
		this.creataDate = creataDate;
	}
	@PreUpdate
	void onPreUpdate()
	{
		creataDate=new Date();
	}
	@PrePersist
	void onPrePersist()
	{
		creataDate=new Date();
		
				
	}
	

}
