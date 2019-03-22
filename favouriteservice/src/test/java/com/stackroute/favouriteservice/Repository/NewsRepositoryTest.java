package com.stackroute.favouriteservice.Repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.Domain.NewsArticle;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NewsRepositoryTest 
{
	private static Logger logger = LoggerFactory.getLogger(NewsRepositoryTest.class);
	
	@Autowired
	private NewsRepository newsRepository;
	
	NewsArticle news = new NewsArticle();
	
	@After	
	public void tearDown()
	{
		logger.info(""+news.getId());
		newsRepository.deleteAllInBatch();
	}

	@Test
	public void testSaveMovieSuccess() throws Exception
	{	
		news.setTitle("Pulwama");
		news.setContent("gfd hfgh gfg gdgrte");;
		news.setDescription("fdsdf fdgdg fdg");
		news.setPublishedAt("08/10/1996");
		news.setSourceWebsiteName("fhfh");
		news.setUrl("retye");
		news.setUrlToImage("nvcnv");
		
		newsRepository.save(news);
		
		assertEquals(true,newsRepository.existsById(news.getId()));
		//movieRepository.delete(movie);
	}
	
	@Test
	public void testDeleteMovieSuccess()
	{
		news.setTitle("Pulwama");
		news.setContent("gfd hfgh gfg gdgrte");;
		news.setDescription("fdsdf fdgdg fdg");
		news.setPublishedAt("08/10/1996");
		news.setSourceWebsiteName("fhfh");
		news.setUrl("retye");
		news.setUrlToImage("nvcnv");
		
		newsRepository.save(news);
		assertEquals(true,newsRepository.existsById(news.getId()));
		newsRepository.delete(news);
		assertEquals(false,newsRepository.existsById(news.getId()));
	}
	
	@Test
	public void testGetMovieSuccess()
	{
		news.setTitle("Pulwama");
		news.setContent("gfd hfgh gfg gdgrte");;
		news.setDescription("fdsdf fdgdg fdg");
		news.setPublishedAt("08/10/1996");
		news.setSourceWebsiteName("fhfh");
		news.setUrl("retye");
		news.setUrlToImage("nvcnv");
		
		newsRepository.save(news);
		assertEquals("Pulwama",news.getTitle());
		//movieRepository.delete(movie);
	}
	
	@Test
	public void testGetMyMoviesSuccess()
	{
		news.setTitle("Pulwama");
		news.setContent("gfd hfgh gfg gdgrte");;
		news.setDescription("fdsdf fdgdg fdg");
		news.setPublishedAt("08/10/1996");
		news.setSourceWebsiteName("fhfh");
		news.setUrl("retye");
		news.setUrlToImage("nvcnv");
		newsRepository.save(news);
		
		NewsArticle news2 = new NewsArticle();
		news2.setTitle("Hiroshima");
		news2.setContent("ipuo gfregf");;
		news2.setDescription("gswgte htyjh");
		news2.setPublishedAt("07/09/2016");
		news2.setSourceWebsiteName("sfdghrbtr");
		news2.setUrl("fwetrv");
		news2.setUrlToImage("htrbhb");
		newsRepository.save(news2);
		
		List<NewsArticle> newsList = newsRepository.findAll();
		assertEquals(2,newsList.size());
//		movieRepository.delete(movie);
//		movieRepository.delete(movie2);
	}
}
