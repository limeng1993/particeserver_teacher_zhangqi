package com.cloudage.membercenter.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Review;
import com.cloudage.membercenter.repository.IReviewRepository;


@Component
@Service
@Transactional
public class DefaultReviewService implements IReview{
	@Autowired
	IReviewRepository reviewRepo;

	@Override
	
	public Review save(Review review) {
		return reviewRepo.save(review);
	}

	@Override
	public Page<Review> findAllOfArticleId(int articleId, int page) {
		Sort sort=new Sort(Direction.DESC,"creataDate");
		PageRequest pageRequest=new PageRequest(page,10,sort);
		
		
		return reviewRepo.findAllOfArticleId(articleId, pageRequest);





	}

	@Override
	public Page<Review> findAllOfReview(int authorId, int  page) {
		Sort sort=new Sort(Direction.DESC,"creataDate");
		PageRequest pageRequest=new PageRequest(page,10,sort);
		
		
		return reviewRepo.findAllOfReview(authorId, pageRequest);
	}

	@Override
	public Page<Review> findAllOfMyreview(String name, int page) {
		Sort sort=new Sort(Direction.DESC,"creataDate");
		PageRequest pageRequest=new PageRequest(page,10, sort);
		return reviewRepo.findAllOfMyreview(name, pageRequest);
				
	}
}
