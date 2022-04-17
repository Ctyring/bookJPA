package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.UpmsMenu;
/**
 * upmsMenu数据访问接口
 * @author Administrator
 *
 */
public interface UpmsMenuDao extends JpaRepository<UpmsMenu,Long>,JpaSpecificationExecutor<UpmsMenu>{
	
}
