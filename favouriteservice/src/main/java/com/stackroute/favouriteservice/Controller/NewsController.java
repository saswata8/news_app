package com.stackroute.favouriteservice.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.Domain.*;
import com.stackroute.favouriteservice.Exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.Exception.NewsNotFoundException;
import com.stackroute.favouriteservice.Filter.JwtFilter;
import com.stackroute.favouriteservice.Service.NewsService;

import io.jsonwebtoken.Jwts;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(path = "/api/news")
public class NewsController 
{
	public static Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;
	
	@PostMapping
	public ResponseEntity<?> saveNewNews(@RequestBody NewsArticle news, HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("Inside saveNewNews");
		
		ResponseEntity<?> responseEntity;
		String authHeader = request.getHeader("authorization");
		String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getSubject();
		try
		{
			news.setUserId(userId);
			newsService.saveNews(news);
			responseEntity = new ResponseEntity<NewsArticle>(news,HttpStatus.CREATED);
		}
		catch(NewsAlreadyExistsException e)
		{
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNews(@PathVariable int id)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			newsService.deleteNewsById(id);
			responseEntity = new ResponseEntity<String>("News deleted successfully!",HttpStatus.OK);
		}
		catch(NewsNotFoundException e)
		{
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getNewsById(@PathVariable int id)
	{
		ResponseEntity<?> responseEntity;
		NewsArticle fetchedNews = null;
		try
		{
			fetchedNews = newsService.getNewsById(id);
			responseEntity = new ResponseEntity<NewsArticle>(fetchedNews,HttpStatus.OK);
		}
		catch(NewsNotFoundException e)
		{
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<?> getMyFavouriteNews(HttpServletRequest request, HttpServletResponse response)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			String authHeader = request.getHeader("authorization");
			String token = authHeader.substring(7);
			String userId = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getSubject();
			List<NewsArticle> newsList = newsService.getMyFavouriteNews(userId);
			responseEntity = new ResponseEntity<List<NewsArticle>>(newsList,HttpStatus.OK);
		}
		catch(NewsNotFoundException e)
		{
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
