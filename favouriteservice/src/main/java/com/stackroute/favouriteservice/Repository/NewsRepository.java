package com.stackroute.favouriteservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.favouriteservice.Domain.NewsArticle;

public interface NewsRepository extends JpaRepository<NewsArticle, Integer> 
{
	List<NewsArticle> findByUserId(String userId);
	
	Optional<NewsArticle> findByUserIdAndTitle(String userId, String title);
}
