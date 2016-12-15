package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.Review;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IReviewRepository;
import com.cloudage.membercenter.service.IArticleService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.IReview;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class APIController {
	@Autowired
	IUserService userService;
	@Autowired
	IArticleService articleService;
	@Autowired
	IReview reviewService;
	@Autowired
	ILikesService likesService;
	

	@RequestMapping(value = "/hello", method=RequestMethod.GET)
	public @ResponseBody String hello(){
		return "HELLO WORLD";
	}
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public User register(
			@RequestParam String account,
	        @RequestParam String passwordHash,
	        @RequestParam String email,
	        @RequestParam String name,
	        MultipartFile avatar,HttpServletRequest request)
	        {
		
		User user=new User();
		user.setAccount(account);
		user.setEmail(email);
		user.setPasswordHash(passwordHash);
		user.setName(name);
		if(avatar!=null)
		{
			try
			{
				String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath,account+".png"));
				user.setAvatar("upload/"+account+".png");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return userService.save(user);
		
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
		public User login(
				@RequestParam String account,
				@RequestParam String  passwordHash,
				HttpServletRequest request)
		{
			User user=userService.findByAccount(account);
			if(user!=null&&user.getPasswordHash().equals(passwordHash))
			{
				request.getSession().setAttribute("uid", user.getId());
				request.getSession().setAttribute("uname", user.getName());
				return user;
			}
			else
			{
				return null;
			}
		}
	@RequestMapping(value="/me",method=RequestMethod.GET)
	public User getCurrentUser(HttpServletRequest request)
	{
		Object obj=request.getSession().getAttribute("uid");
		if(obj instanceof Integer)
		{
			int uid = (Integer)obj;
			return userService.findByID(uid);
		}else
		{
			return null;
		}
	}
	@RequestMapping(value="/forgot_password",method=RequestMethod.POST)
	public boolean resetPassword(
		@RequestParam String email,
		@RequestParam String passwordHash,
		HttpServletRequest request)
	
	{
		User user=userService.findByAccount(email);
		if(user!=null)
		{
			user.setPasswordHash(passwordHash);
			userService.save(user);
			return true;
		}else
		{
			return false;
		}
	
	}
	@RequestMapping(value="/articles/{userId}")
	public List<Article>getArticlesByUserID(@PathVariable Integer userId)
	{
		return articleService.findAllByAuthorId(userId);
	}
	
	
	
	@RequestMapping(value="/article",method=RequestMethod.POST)
		public Article addArticle(
				@RequestParam String title,
				@RequestParam String text,
				HttpServletRequest request)
		{
			User currentUser=getCurrentUser(request);
			Article article=new Article();
			article.setAuthor(currentUser); 
		    article.setTitle(title); 
			article.setText(text); 
			return articleService.save(article); 

		}
	//page,是指可以获取url中的参数，然后根据这个数字进行翻页
	@RequestMapping(value="/feeds/{page}")
	public  Page<Article> getFeeds(@PathVariable int page)
	{
		return articleService.getFeeds(page);
	}
	@RequestMapping("/feeds")
	public Page<Article> getFeeds()
	{
		return getFeeds(0);
	}
	@RequestMapping("/article/{article_id}/review/{page}")
	public Page<Review> getReviewsOfArticle(
			@PathVariable int article_id,
			@PathVariable int page)
	{
		return reviewService.findAllOfArticleId(article_id, page);
	}
	@RequestMapping("/article/{article_id}/comments")
	public Page<Review> getReviewsOfArticle(@PathVariable int article_id)
	{
		return reviewService.findAllOfArticleId(article_id, 0);
	}
	@RequestMapping(value="/article/{article_id}/reviews",method=RequestMethod.POST)
	public Review postReview(
			@PathVariable int article_id,
			@RequestParam String text,
			HttpServletRequest request)
	{
		User me=getCurrentUser(request);
		Article article=articleService.findOne(article_id);
		Review review=new Review();
		review.setAuthor(me);
		review.setArticle(article);
		review.setTextReview(text);
		
		return reviewService.save(review);
		
	}
	 
		@RequestMapping("/article/{article_id}/likes") 
		public int countLikes(@PathVariable int article_id){ 
	 		return likesService.countLikes(article_id); 
	 		} 
	 	 
	
	@RequestMapping("/article/{article_id}/isliked")
	public boolean checkLiked(@PathVariable int article_id,HttpServletRequest request)
	{
		User me=getCurrentUser(request);
		return likesService.checkLiked(me.getId(),article_id);
		
	}
	 
	@RequestMapping(value="/article/{article_id}/likes",method = RequestMethod.POST) 
	public int changeLikes( 
	@PathVariable int article_id, 
	@RequestParam boolean likes, 
	HttpServletRequest request 
	){ 
	  User me = getCurrentUser(request); 
	   Article article = articleService.findOne(article_id); 
		if(likes) 
			likesService.addLike(me, article); 
		else 
				likesService.removeLike(me, article); 
		 
 		return likesService.countLikes(article_id); 
		} 
	  
	@RequestMapping("/article/s/{keyword}")
	public Page<Article> searhArticlesWithKeyword(
			@PathVariable String keyword,
			@RequestParam(defaultValue ="0") int page)
	{
		return articleService.searchTextWithKeyword(keyword,page);
	}
	//@RequestMapping("/review/{author_id}")
	@RequestMapping("/review")
	public Page<Review> findAllOfReview(
			@RequestParam(defaultValue="0") int page,
			HttpServletRequest request)
	{
		Object obj=request.getSession().getAttribute("uid");
		 int author_id = (int)obj;
		return reviewService.findAllOfReview(author_id, page);
	}
	
	@RequestMapping("/Ireview")
	public Page<Review> findAllOfMyreview(
			@RequestParam (defaultValue="0") int page,
			HttpServletRequest request)
	{
		Object obj=request.getSession().getAttribute("uname");
		String name=(String)obj;
		return reviewService.findAllOfMyreview(name, page);
		
	}
	
} 



	
				

