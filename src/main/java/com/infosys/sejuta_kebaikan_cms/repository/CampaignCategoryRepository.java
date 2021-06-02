package com.infosys.sejuta_kebaikan_cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.CampaignCategory;

@Repository
public interface CampaignCategoryRepository extends JpaRepository<CampaignCategory, Long> {

}
