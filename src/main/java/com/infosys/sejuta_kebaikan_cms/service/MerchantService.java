package com.infosys.sejuta_kebaikan_cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.sejuta_kebaikan_cms.model.Merchant;
import com.infosys.sejuta_kebaikan_cms.repository.MerchantRepository;

@Service
public class MerchantService {
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	public Merchant getMerchantById(Long id) {
		return merchantRepository.findByIdAndActive(id, true);
	}
	
	public boolean emailExists(String email, Long id) {
		boolean emailExists = true;
		Merchant merchantDb = getMerchantById(id);
		if (merchantDb.getEmail().equals(email)) {
			emailExists = false;
		}
		
		if (emailExists) {
			emailExists = merchantRepository.findByEmail(email) == null ? false : true;
		}
		
		return emailExists;
	}
}
