package com.infosys.sejuta_kebaikan_cms.repository.cms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;

@Repository
public interface CmsMenuRepository extends JpaRepository<CmsMenu, Long> {

	@Query(value = "select url from cms_menus a left join cms_role_menus b on a.id = b.cms_menu_id left join cms_roles c on b.cms_role_id = c.id left join cms_users d on c.id = d.cms_role_id where d.id = ?1 and d.active = true", nativeQuery = true)
	List<String> findUrlMenuByUserId(Long userId);
}
