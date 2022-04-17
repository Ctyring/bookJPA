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

import book.web.cty.dao.UpmsRoleDao;
import book.web.cty.pojo.UpmsRole;

/**
 * upmsRole服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UpmsRoleService {

	@Autowired
	private UpmsRoleDao upmsRoleDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<UpmsRole> findAll() {
		return upmsRoleDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<UpmsRole> findSearch(Map whereMap, int page, int size) {
		Specification<UpmsRole> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return upmsRoleDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<UpmsRole> findSearch(Map whereMap) {
		Specification<UpmsRole> specification = createSpecification(whereMap);
		return upmsRoleDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public UpmsRole findById(Long id) {
		return upmsRoleDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param upmsRole
	 */
	public void add(UpmsRole upmsRole) {
		// upmsRole.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		upmsRoleDao.save(upmsRole);
	}

	/**
	 * 修改
	 * @param upmsRole
	 */
	public void update(UpmsRole upmsRole) {
		upmsRoleDao.save(upmsRole);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		upmsRoleDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<UpmsRole> createSpecification(Map searchMap) {

		return new Specification<UpmsRole>() {

			@Override
			public Predicate toPredicate(Root<UpmsRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
