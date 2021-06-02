package com.infosys.sejuta_kebaikan_cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.CmsUser;

@Repository
public interface CmsUserRepository extends JpaRepository<CmsUser, Long> {

}