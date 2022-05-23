package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.Storage;
/**
 * storage数据访问接口
 * @author Administrator
 *
 */
public interface StorageDao extends JpaRepository<Storage,Long>,JpaSpecificationExecutor<Storage>{
	
}
