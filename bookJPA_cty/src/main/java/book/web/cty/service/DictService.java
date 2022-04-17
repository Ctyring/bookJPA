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

import book.web.cty.dao.DictDao;
import book.web.cty.pojo.Dict;

/**
 * dict服务层
 * 
 * @author Administrator
 *
 */
@Service
public class DictService {

	@Autowired
	private DictDao dictDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Dict> findAll() {
		return dictDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Dict> findSearch(Map whereMap, int page, int size) {
		Specification<Dict> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return dictDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Dict> findSearch(Map whereMap) {
		Specification<Dict> specification = createSpecification(whereMap);
		return dictDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Dict findById(Long id) {
		return dictDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param dict
	 */
	public void add(Dict dict) {
		// dict.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		dictDao.save(dict);
	}

	/**
	 * 修改
	 * @param dict
	 */
	public void update(Dict dict) {
		dictDao.save(dict);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		dictDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Dict> createSpecification(Map searchMap) {

		return new Specification<Dict>() {

			@Override
			public Predicate toPredicate(Root<Dict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // remark
                if (searchMap.get("remark")!=null && !"".equals(searchMap.get("remark"))) {
                	predicateList.add(cb.like(root.get("remark").as(String.class), "%"+(String)searchMap.get("remark")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
