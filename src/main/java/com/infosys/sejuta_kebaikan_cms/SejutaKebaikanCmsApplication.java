package com.infosys.sejuta_kebaikan_cms;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.infosys.sejuta_kebaikan_cms.model.Campaign;
import com.infosys.sejuta_kebaikan_cms.model.CampaignCategory;
import com.infosys.sejuta_kebaikan_cms.model.Merchant;
import com.infosys.sejuta_kebaikan_cms.model.User;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsGroupMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsRole;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsRoleMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.repository.CampaignCategoryRepository;
import com.infosys.sejuta_kebaikan_cms.repository.CampaignRepository;
import com.infosys.sejuta_kebaikan_cms.repository.MerchantRepository;
import com.infosys.sejuta_kebaikan_cms.repository.UserRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsGroupMenuRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsMenuRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsRoleMenuRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsRoleRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsUserRepository;
import com.infosys.sejuta_kebaikan_cms.util.PasswordUtil;

@SpringBootApplication
@EnableJpaAuditing
public class SejutaKebaikanCmsApplication {
	private static final Logger logger = LoggerFactory.getLogger(SejutaKebaikanCmsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SejutaKebaikanCmsApplication.class, args);
	}
	
	@Bean
    ApplicationRunner init(CampaignCategoryRepository campaignCategoryRepository, UserRepository userRepository, CampaignRepository campaignRepository, MerchantRepository merchantRepository, CmsUserRepository cmsUserRepository, CmsGroupMenuRepository cmsGroupMenuRepository, CmsMenuRepository cmsMenuRepository, CmsRoleRepository cmsRoleRepository, CmsRoleMenuRepository cmsRoleMenuRepository) {
        return (ApplicationArguments args) ->  initData(campaignCategoryRepository, userRepository, campaignRepository, merchantRepository, cmsUserRepository, cmsGroupMenuRepository, cmsMenuRepository, cmsRoleRepository, cmsRoleMenuRepository);
        
    } 
	
	private void initData(CampaignCategoryRepository campaignCategoryRepository, UserRepository userRepository, CampaignRepository campaignRepository, MerchantRepository merchantRepository, CmsUserRepository cmsUserRepository, CmsGroupMenuRepository cmsGroupMenuRepository, CmsMenuRepository cmsMenuRepository, CmsRoleRepository cmsRoleRepository, CmsRoleMenuRepository cmsRoleMenuRepository) {
		if (!isDataLoaded(userRepository)) {
			logger.info("Init Data");
			CampaignCategory campaignCategory = initCampaignCategory(campaignCategoryRepository);
			User user = initUser(userRepository);
			initCampaign(campaignRepository, campaignCategory, user);
			Merchant merchant = initMerchant(merchantRepository);
			CmsGroupMenu cmsGroupMenu = initCmsGroupMenu(cmsGroupMenuRepository);
			CmsMenu cmsMenu = initCmsMenu(cmsMenuRepository, cmsGroupMenu);
			CmsRole cmsRole = initCmsRole(cmsRoleRepository);
			initCmsRoleMenu(cmsRoleMenuRepository, cmsMenu, cmsRole);
			initCmsUser(cmsUserRepository, merchant, cmsRole);
		}
		logger.info("Data Available");
	}
	
	private boolean isDataLoaded(UserRepository userRepository) {
		boolean isDataLoaded = false;
		
		if (userRepository.findById(1L).isPresent()) {
			isDataLoaded = true;
		}
		
		return isDataLoaded;
	}
	
	private CampaignCategory initCampaignCategory(CampaignCategoryRepository campaignCategoryRepository) {
		CampaignCategory campaignCategory = new CampaignCategory();
		campaignCategory.setActive(true);
		campaignCategory.setCode("DNT");
		campaignCategory.setLogo("url logo");
		campaignCategory.setName("Donasi");
		
		campaignCategoryRepository.save(campaignCategory);
		
		logger.info("Init Campaign Cateogry Success");
		
		return campaignCategory;
	}
	
	private User initUser(UserRepository userRepository) {
		User user = new User();
		user.setActive(true);
		user.setName("Admin");
		user.setPhoneNumber("081234567890");
		user.setEmail("admin@test.com");
		user.setPassword(PasswordUtil.hashPassword("admin"));
		user.setAddress("Jakarta");
		user.setChannel(1);
		
		userRepository.save(user);

		logger.info("Init User Success");
		
		return user;
	}
	
	private void initCampaign(CampaignRepository campaignRepository, CampaignCategory campaignCategory, User user) {
		Campaign campaign = new Campaign();
		campaign.setActive(true);
		campaign.setTitle("Test Donasi");
		campaign.setDescription("Untuk keperluan testing");
		campaign.setTargetAmount(new BigDecimal(1000000));
		campaign.setStartTime(new Date());
		
		// convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        // manipulate date
        c.add(Calendar.YEAR, 0);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 1);
        c.add(Calendar.MINUTE, 1);
        c.add(Calendar.SECOND, 1);

        // convert calendar to date
        Date endDate = c.getTime();
        
        campaign.setEndTime(endDate);
        
        campaign.setBannerImage("bannerImageUrl");
        campaign.setCampaignCategory(campaignCategory);
        campaign.setUser(user);
        
        campaignRepository.save(campaign);
        
		logger.info("Init Campaign Success");
	}

	private Merchant initMerchant(MerchantRepository merchantRepository) {
		Merchant merchant = new Merchant();
		merchant.setName("Merchant");
		merchant.setActive(true);
		merchant.setEmail("merchant@test.com");
		merchant.setDescription("For Testing Purpose");
		merchant.setAddress("Jakarta Pusat");
		merchant.setCallCenter("021-1234567890");
		merchant.setWebUrl("webUrl");
		merchant.setLogo("logoUrl");
		
		merchantRepository.save(merchant);

		logger.info("Init Merchant Success");
		
		return merchant;
		
	}
	
	private CmsGroupMenu initCmsGroupMenu(CmsGroupMenuRepository cmsGroupMenuRepository) {
		CmsGroupMenu cmsGroupMenu = new CmsGroupMenu();
		cmsGroupMenu.setActive(true);
		cmsGroupMenu.setName("CMS User");
		
		cmsGroupMenuRepository.save(cmsGroupMenu);

		logger.info("Init Cms Group Menu Success");
		
		return cmsGroupMenu;
	}
	
	private CmsMenu initCmsMenu(CmsMenuRepository cmsMenuRepository, CmsGroupMenu cmsGroupMenu) {
		CmsMenu cmsMenu = new CmsMenu();
		cmsMenu.setActive(true);
		cmsMenu.setName("User CMS");
		cmsMenu.setUrl("/pages/admin/list_user_cms");
		cmsMenu.setLevel(1);
		cmsMenu.setCmsGroupMenu(cmsGroupMenu);
		
		cmsMenuRepository.save(cmsMenu);

		logger.info("Init Cms Menu Success");
		
		return cmsMenu;
	}
	
	private CmsRole initCmsRole(CmsRoleRepository cmsRoleRepository) {
		CmsRole cmsRole = new CmsRole();
		cmsRole.setActive(true);
		cmsRole.setName("Super Admin");
		cmsRole.setCategory("ADMIN");
		cmsRole.setMaster(true);
		
		cmsRoleRepository.save(cmsRole);

		logger.info("Init Cms Role Success");
		
		return cmsRole;
	}
	
	private void initCmsRoleMenu(CmsRoleMenuRepository cmsRoleMenuRepository, CmsMenu cmsMenu, CmsRole cmsRole) {
		CmsRoleMenu cmsRoleMenu = new CmsRoleMenu();
		cmsRoleMenu.setActive(true);
		cmsRoleMenu.setCmsMenu(cmsMenu);
		cmsRoleMenu.setCmsRole(cmsRole);
		cmsRoleMenu.setNeedApproval(false);
		
		cmsRoleMenuRepository.save(cmsRoleMenu);
		
		logger.info("Init Cms Role Menu Success");
	}
	
	private void initCmsUser(CmsUserRepository cmsUserRepository, Merchant merchant, CmsRole cmsRole) {
		CmsUser cmsUser = new CmsUser();
		cmsUser.setActive(true);
		cmsUser.setName("SuperAdmin");
		cmsUser.setPhoneNumber("081234567891");
		cmsUser.setEmail("su_admin@test.com");
		cmsUser.setPassword(PasswordUtil.hashPassword("suadmin"));
		cmsUser.setMerchant(merchant);
		cmsUser.setCmsRole(cmsRole);
		
		cmsUserRepository.save(cmsUser);

		logger.info("Init CMS User Success");
	}
}
