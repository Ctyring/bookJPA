package book.web.cty.dao;

import book.web.cty.pojo.Cart;
import book.web.cty.pojo.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author cty
 * @date 2022/6/25
 */
public interface CartDetailsDao extends JpaRepository<CartDetails,Long>, JpaSpecificationExecutor<CartDetails> {
}
