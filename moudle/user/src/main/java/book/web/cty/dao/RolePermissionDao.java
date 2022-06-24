package book.web.cty.dao;

import book.web.cty.pojo.Role;
import book.web.cty.pojo.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cty
 * @date 2022/6/24
 */
public interface RolePermissionDao extends JpaRepository<RolePermission,Long>, JpaSpecificationExecutor<RolePermission> {

}
