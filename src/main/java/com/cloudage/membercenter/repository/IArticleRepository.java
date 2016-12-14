package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.User;

public interface IArticleRepository extends PagingAndSortingRepository<Article, Integer>{
	
	@Query("From User u where u = ?1")
	List<Article> findAllByAuthor(User user);
	
	@Query("From User u where u.id = ?1")
	List<Article> findAllByAuthorId(Integer accountId);

}
