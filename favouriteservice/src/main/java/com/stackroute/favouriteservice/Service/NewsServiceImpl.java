package com.stackroute.favouriteservice.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.Domain.NewsArticle;
import com.stackroute.favouriteservice.Exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.Exception.NewsNotFoundException;
import com.stackroute.favouriteservice.Repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService
{
	public static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public boolean saveNews(NewsArticle saveNews) throws NewsAlreadyExistsException
	{
		logger.info("Inside saveNews service");
		Optional<NewsArticle> news = newsRepository.findByUserIdAndTitle(saveNews.getUserId(), saveNews.getTitle());
		
		if(news.isPresent())
			throw new NewsAlreadyExistsException("Could not save news as favourite. News already exists!");
		
		newsRepository.save(saveNews);
		return true;
	}
	
	@Override
	public boolean deleteNewsById(int id) throws NewsNotFoundException
	{
		NewsArticle news = newsRepository.findById(id).orElse(null);
		if(news == null)
			throw new NewsNotFoundException("Could not delete news. News not found!");
		newsRepository.delete(news);
		return true;
	}
	
	@Override
	public NewsArticle getNewsById(int id) throws NewsNotFoundException
	{
		NewsArticle news = newsRepository.findById(id).orElse(null);
		if(news == null)
			throw new NewsNotFoundException("News not found!");
		return news;
	}
	
	@Override
	public List<NewsArticle> getMyFavouriteNews(String userId) throws NewsNotFoundException
	{
		List<NewsArticle> newsList = (List<NewsArticle>) newsRepository.findByUserId(userId);
		if(newsList.size()==0)
			throw new NewsNotFoundException("No favourite news found!");
		return newsList;
	}
}
