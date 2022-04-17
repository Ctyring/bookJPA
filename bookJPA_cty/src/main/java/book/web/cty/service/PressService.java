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

import book.web.cty.dao.PressDao;
import book.web.cty.pojo.Press;

/**
 * press服务层
 * 
 * @author Administrator
 *
 */
@Service
public class PressService {

	@Autowired
	private PressDao pressDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Press> findAll() {
		return pressDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Press> findSearch(Map whereMap, int page, int size) {
		Specification<Press> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return pressDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Press> findSearch(Map whereMap) {
		Specification<Press> specification = createSpecification(whereMap);
		return pressDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Press findById(Long id) {
		return pressDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param press
	 */
	public void add(Press press) {
		// press.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		pressDao.save(press);
	}

	/**
	 * 修改
	 * @param press
	 */
	public void update(Press press) {
		pressDao.save(press);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		pressDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Press> createSpecification(Map searchMap) {

		return new Specification<Press>() {

			@Override
			public Predicate toPredicate(Root<Press> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
