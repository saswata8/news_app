package com.stackroute.favouriteservice.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.Domain.NewsArticle;
import com.stackroute.favouriteservice.Service.NewsService;

@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private NewsController newsController;

	@MockBean
	private NewsService newsService;

	private NewsArticle news;

	private ObjectMapper objectMapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectMapper = new ObjectMapper();
		news = new NewsArticle();
		mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
		
		news.setTitle("Pulwama");
		news.setContent("gfd hfgh gfg gdgrte");;
		news.setDescription("fdsdf fdgdg fdg");
		news.setPublishedAt("08/10/1996");
		news.setSourceWebsiteName("fhfh");
		news.setUrl("retye");
		news.setUrlToImage("nvcnv");
	}

	@Test
	public void testSaveNewsSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNTUzMDgwMTI5fQ.M1hsGHluffWIB3aaZTwCkohfheX_hKfikcDbbbSgtx8";
		when(newsService.saveNews(news)).thenReturn(true);
		mockMvc.perform(post("/api/news").header("authorization","Bearer "+token).content(objectMapper.writeValueAsString(news))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());

	}

//	@Test
//	public void testUpdateMovieSuccess() throws Exception {
//		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNTUyODg0MzMwfQ.TLjyBudZRJ6naSRPh-wikeV5EBDutzy3oT1gTs9-H30";
//		movie.setComments("saddada");
//		when(movieService.updateMovie(movie)).thenReturn(movie);
//		mockMvc.perform(put("/api/movie").header("authorization","Bearer "+token).content(objectMapper.writeValueAsString(movie))
//				.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());
//
//	}

	@Test
	public void testDeleteNewsByIdSuccess() throws Exception {
		when(newsService.deleteNewsById(news.getId())).thenReturn(true);
		mockMvc.perform(delete("/api/news/{id}", news.getId()).contentType("application/json;charset=UTF-8")).andExpect(status().isOk());

	}
	
//	@Test
//	public void testGetMovieByIdSuccess() throws Exception {
//		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNTUyODg0MzMwfQ.TLjyBudZRJ6naSRPh-wikeV5EBDutzy3oT1gTs9-H30";
//		when(movieService.getMovieById(movie.getId())).thenReturn(movie);
//		mockMvc.perform(get("/api/movie/{id}", movie.getId()))).header("authorization","Bearer "+token)).andExpect(status().isOk());
//
//	}
	
	@Test
	public void testGetMyFavouriteNewsSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNTUzMDgwMTI5fQ.M1hsGHluffWIB3aaZTwCkohfheX_hKfikcDbbbSgtx8";
		List<NewsArticle> news = new ArrayList<>();
		when(newsService.getMyFavouriteNews("John")).thenReturn(news);
		mockMvc.perform(get("/api/news").header("authorization","Bearer "+token).contentType("application/json;charset=UTF-8")).andExpect(status().isOk());

	}
}
