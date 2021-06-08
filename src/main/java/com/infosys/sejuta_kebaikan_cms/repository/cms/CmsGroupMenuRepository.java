package com.infosys.sejuta_kebaikan_cms.repository.cms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsGroupMenu;

@Repository
public interface CmsGroupMenuRepository extends JpaRepository<CmsGroupMenu, Long> {
	
	@Query(value = "select distinct a.* from cms_group_menus a left join cms_menus b on a.id=b.cms_group_menu_id left join cms_role_menus c on b.id=c.cms_menu_id left join cms_users d on c.cms_role_id=d.cms_role_id where d.id=?1", nativeQuery = true)
	List<CmsGroupMenu> findGroupMenu(Long userId);
}
