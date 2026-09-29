package com.securebank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securebank.entity.User;
import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByMobile(String mobile); 
	List<User> findByStatus(UsersStatus status);
	List<User> findByRolesAndStatus(RoleName role, UsersStatus status);
	List<User> findByRoles(RoleName role); 

}
