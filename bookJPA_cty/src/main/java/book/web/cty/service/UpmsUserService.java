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

import book.web.cty.dao.UpmsUserDao;
import book.web.cty.pojo.UpmsUser;

/**
 * upmsUser服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UpmsUserService {

	@Autowired
	private UpmsUserDao upmsUserDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<UpmsUser> findAll() {
		return upmsUserDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<UpmsUser> findSearch(Map whereMap, int page, int size) {
		Specification<UpmsUser> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return upmsUserDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<UpmsUser> findSearch(Map whereMap) {
		Specification<UpmsUser> specification = createSpecification(whereMap);
		return upmsUserDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public UpmsUser findById(Long id) {
		return upmsUserDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param upmsUser
	 */
	public void add(UpmsUser upmsUser) {
		// upmsUser.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		upmsUserDao.save(upmsUser);
	}

	/**
	 * 修改
	 * @param upmsUser
	 */
	public void update(UpmsUser upmsUser) {
		upmsUserDao.save(upmsUser);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		upmsUserDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<UpmsUser> createSpecification(Map searchMap) {

		return new Specification<UpmsUser>() {

			@Override
			public Predicate toPredicate(Root<UpmsUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // account
                if (searchMap.get("account")!=null && !"".equals(searchMap.get("account"))) {
                	predicateList.add(cb.like(root.get("account").as(String.class), "%"+(String)searchMap.get("account")+"%"));
                }
                // email
                if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
                	predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
                // password
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // phone
                if (searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))) {
                	predicateList.add(cb.like(root.get("phone").as(String.class), "%"+(String)searchMap.get("phone")+"%"));
                }
                // remark
                if (searchMap.get("remark")!=null && !"".equals(searchMap.get("remark"))) {
                	predicateList.add(cb.like(root.get("remark").as(String.class), "%"+(String)searchMap.get("remark")+"%"));
                }
                // white_ip
                if (searchMap.get("whiteIp")!=null && !"".equals(searchMap.get("whiteIp"))) {
                	predicateList.add(cb.like(root.get("whiteIp").as(String.class), "%"+(String)searchMap.get("whiteIp")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
