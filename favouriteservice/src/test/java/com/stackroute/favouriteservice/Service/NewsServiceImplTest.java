package com.stackroute.favouriteservice.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.Domain.NewsArticle;
import com.stackroute.favouriteservice.Exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.Exception.NewsNotFoundException;
import com.stackroute.favouriteservice.Repository.NewsRepository;

public class NewsServiceImplTest 
{
	@Mock
	private NewsRepository newsRepository;
	
	@InjectMocks
	private NewsServiceImpl newsServiceImpl;
	
	private NewsArticle news;
	
	Optional<NewsArticle> options;
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		news = new NewsArticle();
		news.setTitle("Pulwama");
		news.setContent("gfd hfgh gfg gdgrte");;
		news.setDescription("fdsdf fdgdg fdg");
		news.setPublishedAt("08/10/1996");
		news.setSourceWebsiteName("fhfh");
		news.setUrl("retye");
		news.setUrlToImage("nvcnv");
		
		options = Optional.of(news);
	}
	
	@Test
	public void testSaveNewsSuccess() throws NewsAlreadyExistsException
	{
		when(newsRepository.save(news)).thenReturn(news);
		boolean flag = newsServiceImpl.saveNews(news);
		assertEquals(true,flag);
		verify(newsRepository,times(1)).save(news);
		verify(newsRepository,times(1)).findByUserIdAndTitle(news.getUserId(), news.getTitle());
	}
	
	@Test(expected=NewsAlreadyExistsException.class)
	public void testSaveNewsFailure() throws NewsAlreadyExistsException
	{
		when(newsRepository.findByUserIdAndTitle(news.getUserId(), news.getTitle())).thenReturn(options);
		boolean flag = newsServiceImpl.saveNews(news);
		assertEquals(false,flag);
		verify(newsRepository,times(0)).save(news);
		verify(newsRepository,times(1)).findByUserIdAndTitle(news.getUserId(), news.getTitle());
	}
	
	@Test
	public void testDeleteNewsByIdSuccess() throws NewsNotFoundException
	{
		when(newsRepository.findById(news.getId())).thenReturn(options);
		doNothing().when(newsRepository).delete(news);
		boolean flag = newsServiceImpl.deleteNewsById(news.getId());
		assertEquals(true,flag);
		verify(newsRepository,times(1)).delete(news);
		verify(newsRepository,times(1)).findById(news.getId());
	}
	
	@Test
	public void testGetNewsByIdSuccess() throws NewsNotFoundException
	{
		when(newsRepository.findById(news.getId())).thenReturn(options);
		NewsArticle fetchedNews= newsServiceImpl.getNewsById(news.getId());
		assertEquals(news,fetchedNews);
		verify(newsRepository,times(1)).findById(news.getId());
	}
	
	@Test(expected=NewsNotFoundException.class)
	public void testGetMyFavouriteNewsSuccess() throws NewsNotFoundException
	{
		List<NewsArticle> expectedNewsList = new ArrayList<>();
		when(newsRepository.findByUserId("John")).thenReturn(expectedNewsList);
		List<NewsArticle> newsList = newsServiceImpl.getMyFavouriteNews("John");
		assertEquals(expectedNewsList,newsList);
		verify(newsRepository,times(1)).findByUserId("John");
	}
}
