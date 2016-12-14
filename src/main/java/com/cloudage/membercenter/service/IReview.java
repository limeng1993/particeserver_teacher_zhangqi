package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Review;

public interface IReview {
	Review save(Review review);
	Page<Review>findAllOfArticleId(int article_Id,int page);

}
