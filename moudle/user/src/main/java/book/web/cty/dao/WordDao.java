package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.Word;
/**
 * word数据访问接口
 * @author Administrator
 *
 */
public interface WordDao extends JpaRepository<Word,Long>,JpaSpecificationExecutor<Word>{
	
}
