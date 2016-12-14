package com.cloudage.membercenter.repository;

//import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.cloudage.membercenter.entity.Review;

public interface IReviewRepository extends PagingAndSortingRepository<Review, Integer>{
	@Query("From Review  review where review.article.id = ?1")
	Page <Review> findAllOfArticleId(int articleId,Pageable page);

}
