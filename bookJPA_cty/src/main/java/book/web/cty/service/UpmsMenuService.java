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

import book.web.cty.dao.UpmsMenuDao;
import book.web.cty.pojo.UpmsMenu;

/**
 * upmsMenu服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UpmsMenuService {

	@Autowired
	private UpmsMenuDao upmsMenuDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<UpmsMenu> findAll() {
		return upmsMenuDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<UpmsMenu> findSearch(Map whereMap, int page, int size) {
		Specification<UpmsMenu> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return upmsMenuDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<UpmsMenu> findSearch(Map whereMap) {
		Specification<UpmsMenu> specification = createSpecification(whereMap);
		return upmsMenuDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public UpmsMenu findById(Long id) {
		return upmsMenuDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param upmsMenu
	 */
	public void add(UpmsMenu upmsMenu) {
		// upmsMenu.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		upmsMenuDao.save(upmsMenu);
	}

	/**
	 * 修改
	 * @param upmsMenu
	 */
	public void update(UpmsMenu upmsMenu) {
		upmsMenuDao.save(upmsMenu);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		upmsMenuDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<UpmsMenu> createSpecification(Map searchMap) {

		return new Specification<UpmsMenu>() {

			@Override
			public Predicate toPredicate(Root<UpmsMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // create_by
                if (searchMap.get("createBy")!=null && !"".equals(searchMap.get("createBy"))) {
                	predicateList.add(cb.like(root.get("createBy").as(String.class), "%"+(String)searchMap.get("createBy")+"%"));
                }
                // update_by
                if (searchMap.get("updateBy")!=null && !"".equals(searchMap.get("updateBy"))) {
                	predicateList.add(cb.like(root.get("updateBy").as(String.class), "%"+(String)searchMap.get("updateBy")+"%"));
                }
                // code
                if (searchMap.get("code")!=null && !"".equals(searchMap.get("code"))) {
                	predicateList.add(cb.like(root.get("code").as(String.class), "%"+(String)searchMap.get("code")+"%"));
                }
                // icon
                if (searchMap.get("icon")!=null && !"".equals(searchMap.get("icon"))) {
                	predicateList.add(cb.like(root.get("icon").as(String.class), "%"+(String)searchMap.get("icon")+"%"));
                }
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // param
                if (searchMap.get("param")!=null && !"".equals(searchMap.get("param"))) {
                	predicateList.add(cb.like(root.get("param").as(String.class), "%"+(String)searchMap.get("param")+"%"));
                }
                // type
                if (searchMap.get("type")!=null && !"".equals(searchMap.get("type"))) {
                	predicateList.add(cb.like(root.get("type").as(String.class), "%"+(String)searchMap.get("type")+"%"));
                }
                // value
                if (searchMap.get("value")!=null && !"".equals(searchMap.get("value"))) {
                	predicateList.add(cb.like(root.get("value").as(String.class), "%"+(String)searchMap.get("value")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
