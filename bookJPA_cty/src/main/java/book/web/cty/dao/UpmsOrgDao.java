package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.UpmsOrg;
/**
 * upmsOrg数据访问接口
 * @author Administrator
 *
 */
public interface UpmsOrgDao extends JpaRepository<UpmsOrg,Long>,JpaSpecificationExecutor<UpmsOrg>{
	
}
