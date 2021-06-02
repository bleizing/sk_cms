package com.infosys.sejuta_kebaikan_cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
