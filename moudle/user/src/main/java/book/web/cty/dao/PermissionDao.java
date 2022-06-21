package book.web.cty.dao;

import book.web.cty.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * permission数据访问接口
 * @author Administrator
 *
 */
public interface PermissionDao extends JpaRepository<Permission,Long>,JpaSpecificationExecutor<Permission>{
	
}
