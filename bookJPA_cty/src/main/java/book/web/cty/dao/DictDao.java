package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.Dict;
/**
 * dict数据访问接口
 * @author Administrator
 *
 */
public interface DictDao extends JpaRepository<Dict,Long>,JpaSpecificationExecutor<Dict>{
	
}
