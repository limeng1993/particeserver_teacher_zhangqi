package com.cloudage.membercenter.entity;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.cloudage.membercenter.util.DateRecord;

@Entity
public class Review extends DateRecord {
	Article article;
	User author;
	String textReview;
	@ManyToOne(optional=false)
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	@ManyToOne(optional=false)
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTextReview() {
		return textReview;
	}
	public void setTextReview(String textReview) {
		this.textReview = textReview;
	}
	

}
