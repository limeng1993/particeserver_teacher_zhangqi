package com.cloudage.membercenter.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.User;

public interface IArticleRepository extends PagingAndSortingRepository<Article, Integer>{
	
	@Query("From User u where u = ?1")
	List<Article> findAllByAuthor(User user);
	
	@Query("From User u where u.id = ?1")
	List<Article> findAllByAuthorId(Integer accountId);
	@Query("From Article article where article.text like %?1%")
	Page<Article> searchTextWithKeyword(String keyword,Pageable page);

}
