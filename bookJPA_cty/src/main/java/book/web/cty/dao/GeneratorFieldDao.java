package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.GeneratorField;
/**
 * generatorField数据访问接口
 * @author Administrator
 *
 */
public interface GeneratorFieldDao extends JpaRepository<GeneratorField,Long>,JpaSpecificationExecutor<GeneratorField>{
	
}
