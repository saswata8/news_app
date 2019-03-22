package com.stackroute.favouriteservice.Service;

import java.util.List;

import com.stackroute.favouriteservice.Domain.NewsArticle;
import com.stackroute.favouriteservice.Exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.Exception.NewsNotFoundException;

public interface NewsService 
{
	boolean saveNews(NewsArticle news) throws NewsAlreadyExistsException;
	
	boolean deleteNewsById(int id) throws NewsNotFoundException;
	
	NewsArticle getNewsById(int id) throws NewsNotFoundException;
	
	List<NewsArticle> getMyFavouriteNews(String userId) throws NewsNotFoundException;
}
