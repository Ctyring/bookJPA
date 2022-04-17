package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.DictItem;
/**
 * dictItem数据访问接口
 * @author Administrator
 *
 */
public interface DictItemDao extends JpaRepository<DictItem,Long>,JpaSpecificationExecutor<DictItem>{
	
}
