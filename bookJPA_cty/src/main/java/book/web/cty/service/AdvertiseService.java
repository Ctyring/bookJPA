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

import book.web.cty.dao.AdvertiseDao;
import book.web.cty.pojo.Advertise;

/**
 * advertise服务层
 * 
 * @author Administrator
 *
 */
@Service
public class AdvertiseService {

	@Autowired
	private AdvertiseDao advertiseDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Advertise> findAll() {
		return advertiseDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Advertise> findSearch(Map whereMap, int page, int size) {
		Specification<Advertise> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return advertiseDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Advertise> findSearch(Map whereMap) {
		Specification<Advertise> specification = createSpecification(whereMap);
		return advertiseDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Advertise findById(Long id) {
		return advertiseDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param advertise
	 */
	public void add(Advertise advertise) {
		// advertise.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		advertiseDao.save(advertise);
	}

	/**
	 * 修改
	 * @param advertise
	 */
	public void update(Advertise advertise) {
		advertiseDao.save(advertise);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		advertiseDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Advertise> createSpecification(Map searchMap) {

		return new Specification<Advertise>() {

			@Override
			public Predicate toPredicate(Root<Advertise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // picture
                if (searchMap.get("picture")!=null && !"".equals(searchMap.get("picture"))) {
                	predicateList.add(cb.like(root.get("picture").as(String.class), "%"+(String)searchMap.get("picture")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
