package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.Role;
/**
 * role数据访问接口
 * @author Administrator
 *
 */
public interface RoleDao extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role>{
	
}
