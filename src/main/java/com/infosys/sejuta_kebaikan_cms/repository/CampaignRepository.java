package com.infosys.sejuta_kebaikan_cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}
