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

import book.web.cty.dao.UpmsLoginLogDao;
import book.web.cty.pojo.UpmsLoginLog;

/**
 * upmsLoginLog服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UpmsLoginLogService {

	@Autowired
	private UpmsLoginLogDao upmsLoginLogDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<UpmsLoginLog> findAll() {
		return upmsLoginLogDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<UpmsLoginLog> findSearch(Map whereMap, int page, int size) {
		Specification<UpmsLoginLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return upmsLoginLogDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<UpmsLoginLog> findSearch(Map whereMap) {
		Specification<UpmsLoginLog> specification = createSpecification(whereMap);
		return upmsLoginLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public UpmsLoginLog findById(Long id) {
		return upmsLoginLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param upmsLoginLog
	 */
	public void add(UpmsLoginLog upmsLoginLog) {
		// upmsLoginLog.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		upmsLoginLogDao.save(upmsLoginLog);
	}

	/**
	 * 修改
	 * @param upmsLoginLog
	 */
	public void update(UpmsLoginLog upmsLoginLog) {
		upmsLoginLogDao.save(upmsLoginLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		upmsLoginLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<UpmsLoginLog> createSpecification(Map searchMap) {

		return new Specification<UpmsLoginLog>() {

			@Override
			public Predicate toPredicate(Root<UpmsLoginLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // browser
                if (searchMap.get("browser")!=null && !"".equals(searchMap.get("browser"))) {
                	predicateList.add(cb.like(root.get("browser").as(String.class), "%"+(String)searchMap.get("browser")+"%"));
                }
                // device_type
                if (searchMap.get("deviceType")!=null && !"".equals(searchMap.get("deviceType"))) {
                	predicateList.add(cb.like(root.get("deviceType").as(String.class), "%"+(String)searchMap.get("deviceType")+"%"));
                }
                // ip
                if (searchMap.get("ip")!=null && !"".equals(searchMap.get("ip"))) {
                	predicateList.add(cb.like(root.get("ip").as(String.class), "%"+(String)searchMap.get("ip")+"%"));
                }
                // region
                if (searchMap.get("region")!=null && !"".equals(searchMap.get("region"))) {
                	predicateList.add(cb.like(root.get("region").as(String.class), "%"+(String)searchMap.get("region")+"%"));
                }
                // system_name
                if (searchMap.get("systemName")!=null && !"".equals(searchMap.get("systemName"))) {
                	predicateList.add(cb.like(root.get("systemName").as(String.class), "%"+(String)searchMap.get("systemName")+"%"));
                }
                // token
                if (searchMap.get("token")!=null && !"".equals(searchMap.get("token"))) {
                	predicateList.add(cb.like(root.get("token").as(String.class), "%"+(String)searchMap.get("token")+"%"));
                }
                // user_name
                if (searchMap.get("userName")!=null && !"".equals(searchMap.get("userName"))) {
                	predicateList.add(cb.like(root.get("userName").as(String.class), "%"+(String)searchMap.get("userName")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
