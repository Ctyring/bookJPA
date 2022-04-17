package book.web.cty.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import book.web.cty.dao.GeneratorFieldDao;
import book.web.cty.pojo.GeneratorField;

/**
 * generatorField服务层
 * 
 * @author Administrator
 *
 */
@Service
public class GeneratorFieldService {

	@Autowired
	private GeneratorFieldDao generatorFieldDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<GeneratorField> findAll() {
		return generatorFieldDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<GeneratorField> findSearch(Map whereMap, int page, int size) {
		Specification<GeneratorField> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return generatorFieldDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<GeneratorField> findSearch(Map whereMap) {
		Specification<GeneratorField> specification = createSpecification(whereMap);
		return generatorFieldDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public GeneratorField findById(Long id) {
		return generatorFieldDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param generatorField
	 */
	public void add(GeneratorField generatorField) {
		// generatorField.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		generatorFieldDao.save(generatorField);
	}

	/**
	 * 修改
	 * @param generatorField
	 */
	public void update(GeneratorField generatorField) {
		generatorFieldDao.save(generatorField);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		generatorFieldDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<GeneratorField> createSpecification(Map searchMap) {

		return new Specification<GeneratorField>() {

			@Override
			public Predicate toPredicate(Root<GeneratorField> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // field_name
                if (searchMap.get("fieldName")!=null && !"".equals(searchMap.get("fieldName"))) {
                	predicateList.add(cb.like(root.get("fieldName").as(String.class), "%"+(String)searchMap.get("fieldName")+"%"));
                }
                // link_class
                if (searchMap.get("linkClass")!=null && !"".equals(searchMap.get("linkClass"))) {
                	predicateList.add(cb.like(root.get("linkClass").as(String.class), "%"+(String)searchMap.get("linkClass")+"%"));
                }
                // show_name
                if (searchMap.get("showName")!=null && !"".equals(searchMap.get("showName"))) {
                	predicateList.add(cb.like(root.get("showName").as(String.class), "%"+(String)searchMap.get("showName")+"%"));
                }
                // type
                if (searchMap.get("type")!=null && !"".equals(searchMap.get("type"))) {
                	predicateList.add(cb.like(root.get("type").as(String.class), "%"+(String)searchMap.get("type")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
