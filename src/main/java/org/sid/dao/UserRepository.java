package org.sid.dao;

import java.io.Serializable;

import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u where u.username like :x")
	User findByUsername(@Param("x")String username);
}
