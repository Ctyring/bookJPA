package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.Cart;
/**
 * cart数据访问接口
 * @author Administrator
 *
 */
public interface CartDao extends JpaRepository<Cart,Long>,JpaSpecificationExecutor<Cart>{
	
}
