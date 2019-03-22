package com.stackroute.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.userservice.Model.User;

public interface UserRepository extends JpaRepository<User, String> 
{
	User findByUserIdAndPassword(String userId, String password);
}
