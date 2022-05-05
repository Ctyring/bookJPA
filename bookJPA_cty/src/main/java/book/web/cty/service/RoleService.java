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

import book.web.cty.dao.RoleDao;
import book.web.cty.pojo.Role;

/**
 * role服务层
 * 
 * @author Administrator
 *
 */
@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Role> findSearch(Map whereMap, int page, int size) {
		Specification<Role> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return roleDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Role> findSearch(Map whereMap) {
		Specification<Role> specification = createSpecification(whereMap);
		return roleDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Role findById(Long id) {
		return roleDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param role
	 */
	public void add(Role role) {
		// role.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		roleDao.save(role);
	}

	/**
	 * 修改
	 * @param role
	 */
	public void update(Role role) {
		roleDao.save(role);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		roleDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Role> createSpecification(Map searchMap) {

		return new Specification<Role>() {

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ch_name
                if (searchMap.get("chName")!=null && !"".equals(searchMap.get("chName"))) {
                	predicateList.add(cb.like(root.get("chName").as(String.class), "%"+(String)searchMap.get("chName")+"%"));
                }
                // des
                if (searchMap.get("des")!=null && !"".equals(searchMap.get("des"))) {
                	predicateList.add(cb.like(root.get("des").as(String.class), "%"+(String)searchMap.get("des")+"%"));
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
