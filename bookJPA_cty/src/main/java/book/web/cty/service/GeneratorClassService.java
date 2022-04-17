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

import book.web.cty.dao.GeneratorClassDao;
import book.web.cty.pojo.GeneratorClass;

/**
 * generatorClass服务层
 * 
 * @author Administrator
 *
 */
@Service
public class GeneratorClassService {

	@Autowired
	private GeneratorClassDao generatorClassDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<GeneratorClass> findAll() {
		return generatorClassDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<GeneratorClass> findSearch(Map whereMap, int page, int size) {
		Specification<GeneratorClass> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return generatorClassDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<GeneratorClass> findSearch(Map whereMap) {
		Specification<GeneratorClass> specification = createSpecification(whereMap);
		return generatorClassDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public GeneratorClass findById(Long id) {
		return generatorClassDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param generatorClass
	 */
	public void add(GeneratorClass generatorClass) {
		// generatorClass.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		generatorClassDao.save(generatorClass);
	}

	/**
	 * 修改
	 * @param generatorClass
	 */
	public void update(GeneratorClass generatorClass) {
		generatorClassDao.save(generatorClass);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		generatorClassDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<GeneratorClass> createSpecification(Map searchMap) {

		return new Specification<GeneratorClass>() {

			@Override
			public Predicate toPredicate(Root<GeneratorClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // create_by
                if (searchMap.get("createBy")!=null && !"".equals(searchMap.get("createBy"))) {
                	predicateList.add(cb.like(root.get("createBy").as(String.class), "%"+(String)searchMap.get("createBy")+"%"));
                }
                // update_by
                if (searchMap.get("updateBy")!=null && !"".equals(searchMap.get("updateBy"))) {
                	predicateList.add(cb.like(root.get("updateBy").as(String.class), "%"+(String)searchMap.get("updateBy")+"%"));
                }
                // class_name
                if (searchMap.get("className")!=null && !"".equals(searchMap.get("className"))) {
                	predicateList.add(cb.like(root.get("className").as(String.class), "%"+(String)searchMap.get("className")+"%"));
                }
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // remark
                if (searchMap.get("remark")!=null && !"".equals(searchMap.get("remark"))) {
                	predicateList.add(cb.like(root.get("remark").as(String.class), "%"+(String)searchMap.get("remark")+"%"));
                }
                // table_name
                if (searchMap.get("tableName")!=null && !"".equals(searchMap.get("tableName"))) {
                	predicateList.add(cb.like(root.get("tableName").as(String.class), "%"+(String)searchMap.get("tableName")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
