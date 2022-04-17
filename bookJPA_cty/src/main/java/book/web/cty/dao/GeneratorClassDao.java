package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.GeneratorClass;
/**
 * generatorClass数据访问接口
 * @author Administrator
 *
 */
public interface GeneratorClassDao extends JpaRepository<GeneratorClass,Long>,JpaSpecificationExecutor<GeneratorClass>{
	
}
