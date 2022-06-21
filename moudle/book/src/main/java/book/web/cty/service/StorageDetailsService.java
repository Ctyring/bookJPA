package book.web.cty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import book.web.cty.dao.StorageDetailsDao;
import book.web.cty.pojo.StorageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

/**
 * details服务层
 * 
 * @author Administrator
 *
 */
@Service
public class StorageDetailsService {

	@Autowired
	private StorageDetailsDao storageDetailsDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<StorageDetails> findAll() {
		return storageDetailsDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<StorageDetails> findSearch(Map whereMap, int page, int size) {
		Specification<StorageDetails> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return storageDetailsDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<StorageDetails> findSearch(Map whereMap) {
		Specification<StorageDetails> specification = createSpecification(whereMap);
		return storageDetailsDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public StorageDetails findById(Long id) {
		return storageDetailsDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param details
	 */
	public void add(StorageDetails details) {
		// details.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		storageDetailsDao.save(details);
	}

	/**
	 * 修改
	 * @param details
	 */
	public void update(StorageDetails details) {
		storageDetailsDao.save(details);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		storageDetailsDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<StorageDetails> createSpecification(Map searchMap) {

		return new Specification<StorageDetails>() {

			@Override
			public Predicate toPredicate(Root<StorageDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
