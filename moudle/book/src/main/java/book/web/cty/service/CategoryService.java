package book.web.cty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import book.web.cty.util.IdWorker;

import book.web.cty.dao.CategoryDao;
import book.web.cty.pojo.Category;

/**
 * category服务层
 * 
 * @author Administrator
 *
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Category> findSearch(Map whereMap, int page, int size) {
		Specification<Category> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return categoryDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Category> findSearch(Map whereMap) {
		Specification<Category> specification = createSpecification(whereMap);
		return categoryDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Category findById(Long id) {
		return categoryDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param category
	 */
	public void add(Category category) {
		// category.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		categoryDao.save(category);
	}

	/**
	 * 修改
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.save(category);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		categoryDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Category> createSpecification(Map searchMap) {

		return new Specification<Category>() {

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
