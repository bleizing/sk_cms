package com.infosys.sejuta_kebaikan_cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
