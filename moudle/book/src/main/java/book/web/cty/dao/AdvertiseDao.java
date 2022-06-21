package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.Advertise;
/**
 * advertise数据访问接口
 * @author Administrator
 *
 */
public interface AdvertiseDao extends JpaRepository<Advertise,Long>,JpaSpecificationExecutor<Advertise>{
	
}
