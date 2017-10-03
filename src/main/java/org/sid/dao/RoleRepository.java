package org.sid.dao;

import org.sid.entities.Role;
import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long>{
	@Query("select r from Role r where r.role like :x")
	Role findByRole(@Param("x") String role);
}
