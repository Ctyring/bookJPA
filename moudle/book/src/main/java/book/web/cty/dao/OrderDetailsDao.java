package book.web.cty.dao;

import book.web.cty.pojo.Order;
import book.web.cty.pojo.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cty
 * @date 2022/6/25
 */
public interface OrderDetailsDao extends JpaRepository<OrderDetails,Long>, JpaSpecificationExecutor<OrderDetails> {
}
