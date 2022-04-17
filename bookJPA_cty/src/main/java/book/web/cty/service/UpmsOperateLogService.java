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

import book.web.cty.dao.UpmsOperateLogDao;
import book.web.cty.pojo.UpmsOperateLog;

/**
 * upmsOperateLog服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UpmsOperateLogService {

	@Autowired
	private UpmsOperateLogDao upmsOperateLogDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<UpmsOperateLog> findAll() {
		return upmsOperateLogDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<UpmsOperateLog> findSearch(Map whereMap, int page, int size) {
		Specification<UpmsOperateLog> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return upmsOperateLogDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<UpmsOperateLog> findSearch(Map whereMap) {
		Specification<UpmsOperateLog> specification = createSpecification(whereMap);
		return upmsOperateLogDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public UpmsOperateLog findById(Long id) {
		return upmsOperateLogDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param upmsOperateLog
	 */
	public void add(UpmsOperateLog upmsOperateLog) {
		// upmsOperateLog.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		upmsOperateLogDao.save(upmsOperateLog);
	}

	/**
	 * 修改
	 * @param upmsOperateLog
	 */
	public void update(UpmsOperateLog upmsOperateLog) {
		upmsOperateLogDao.save(upmsOperateLog);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		upmsOperateLogDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<UpmsOperateLog> createSpecification(Map searchMap) {

		return new Specification<UpmsOperateLog>() {

			@Override
			public Predicate toPredicate(Root<UpmsOperateLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // api_name
                if (searchMap.get("apiName")!=null && !"".equals(searchMap.get("apiName"))) {
                	predicateList.add(cb.like(root.get("apiName").as(String.class), "%"+(String)searchMap.get("apiName")+"%"));
                }
                // ip
                if (searchMap.get("ip")!=null && !"".equals(searchMap.get("ip"))) {
                	predicateList.add(cb.like(root.get("ip").as(String.class), "%"+(String)searchMap.get("ip")+"%"));
                }
                // operate_user
                if (searchMap.get("operateUser")!=null && !"".equals(searchMap.get("operateUser"))) {
                	predicateList.add(cb.like(root.get("operateUser").as(String.class), "%"+(String)searchMap.get("operateUser")+"%"));
                }
                // region
                if (searchMap.get("region")!=null && !"".equals(searchMap.get("region"))) {
                	predicateList.add(cb.like(root.get("region").as(String.class), "%"+(String)searchMap.get("region")+"%"));
                }
                // req_addr
                if (searchMap.get("reqAddr")!=null && !"".equals(searchMap.get("reqAddr"))) {
                	predicateList.add(cb.like(root.get("reqAddr").as(String.class), "%"+(String)searchMap.get("reqAddr")+"%"));
                }
                // req_method
                if (searchMap.get("reqMethod")!=null && !"".equals(searchMap.get("reqMethod"))) {
                	predicateList.add(cb.like(root.get("reqMethod").as(String.class), "%"+(String)searchMap.get("reqMethod")+"%"));
                }
                // req_param
                if (searchMap.get("reqParam")!=null && !"".equals(searchMap.get("reqParam"))) {
                	predicateList.add(cb.like(root.get("reqParam").as(String.class), "%"+(String)searchMap.get("reqParam")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
