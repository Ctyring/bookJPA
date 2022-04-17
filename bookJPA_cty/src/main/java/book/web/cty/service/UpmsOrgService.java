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

import book.web.cty.dao.UpmsOrgDao;
import book.web.cty.pojo.UpmsOrg;

/**
 * upmsOrg服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UpmsOrgService {

	@Autowired
	private UpmsOrgDao upmsOrgDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<UpmsOrg> findAll() {
		return upmsOrgDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<UpmsOrg> findSearch(Map whereMap, int page, int size) {
		Specification<UpmsOrg> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return upmsOrgDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<UpmsOrg> findSearch(Map whereMap) {
		Specification<UpmsOrg> specification = createSpecification(whereMap);
		return upmsOrgDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public UpmsOrg findById(Long id) {
		return upmsOrgDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param upmsOrg
	 */
	public void add(UpmsOrg upmsOrg) {
		// upmsOrg.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		upmsOrgDao.save(upmsOrg);
	}

	/**
	 * 修改
	 * @param upmsOrg
	 */
	public void update(UpmsOrg upmsOrg) {
		upmsOrgDao.save(upmsOrg);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		upmsOrgDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<UpmsOrg> createSpecification(Map searchMap) {

		return new Specification<UpmsOrg>() {

			@Override
			public Predicate toPredicate(Root<UpmsOrg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // code
                if (searchMap.get("code")!=null && !"".equals(searchMap.get("code"))) {
                	predicateList.add(cb.like(root.get("code").as(String.class), "%"+(String)searchMap.get("code")+"%"));
                }
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
