package book.web.cty.dao;

import book.web.cty.pojo.StorageDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * details数据访问接口
 * @author Administrator
 *
 */
public interface StorageDetailsDao extends JpaRepository<StorageDetails,Long>,JpaSpecificationExecutor<StorageDetails>{
	
}
