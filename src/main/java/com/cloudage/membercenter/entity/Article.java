package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article extends BaseEntity{
	User author;
	Date creataDate;
	Date editDate;
	String title;
	String text;
	
	@ManyToOne(optional=false)
	@JsonIgnore
	public User getAuthor() {
		return author;
	}
	@Transient
	public String getAuthorName()
	{
		return author.name;
	}
	@Transient
	public String getAuthorAvater()
	{
		return author.avatar;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getCreataDate() {
		return creataDate;
	}
	public void setCreataDate(Date creataDate) {
		this.creataDate = creataDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@PreUpdate
	void onPreUpdate()
	{
		editDate=new Date();
	}
	@PrePersist
	void onPrePersist()
	{
		creataDate=new Date();
		editDate=new Date();
				
	}
	

}
