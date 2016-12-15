package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cloudage.membercenter.entity.Review;

public interface IReview {
	Review save(Review review);
	Page<Review>findAllOfArticleId(int article_Id,int page);
	Page<Review>findAllOfReview(int authorId,int page);
	Page<Review>findAllOfMyreview(String name,int page);

}
