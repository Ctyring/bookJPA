package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.Book;
/**
 * book数据访问接口
 * @author Administrator
 *
 */
public interface BookDao extends JpaRepository<Book,Long>,JpaSpecificationExecutor<Book>{
	
}
