package book.web.cty.dao;

import book.web.cty.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * user数据访问接口
 *
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findUserByEmail(String email);

    User findUserByUsername(String email);
}
