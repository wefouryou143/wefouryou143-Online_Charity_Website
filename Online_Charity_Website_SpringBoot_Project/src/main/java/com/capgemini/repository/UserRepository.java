package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "Select u from User u Where u.username =:username AND u.userPassword =:userPassword ")
	public User readByUsernameAndpassword(@Param("username") String name, @Param("userPassword") String Password);

	User findByUsername(String username);

}
