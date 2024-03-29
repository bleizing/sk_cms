package com.infosys.sejuta_kebaikan_cms.repository.cms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsRoleMenu;

@Repository
public interface CmsRoleMenuRepository extends JpaRepository<CmsRoleMenu, Long> {
	List<CmsRoleMenu> findByActive(Boolean active);
}
