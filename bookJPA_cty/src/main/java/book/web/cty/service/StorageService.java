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

import book.web.cty.dao.StorageDao;
import book.web.cty.pojo.Storage;

/**
 * storage服务层
 * 
 * @author Administrator
 *
 */
@Service
public class StorageService {

	@Autowired
	private StorageDao storageDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Storage> findAll() {
		return storageDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Storage> findSearch(Map whereMap, int page, int size) {
		Specification<Storage> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return storageDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Storage> findSearch(Map whereMap) {
		Specification<Storage> specification = createSpecification(whereMap);
		return storageDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Storage findById(Long id) {
		return storageDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param storage
	 */
	public void add(Storage storage) {
		// storage.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		storageDao.save(storage);
	}

	/**
	 * 修改
	 * @param storage
	 */
	public void update(Storage storage) {
		storageDao.save(storage);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		storageDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Storage> createSpecification(Map searchMap) {

		return new Specification<Storage>() {

			@Override
			public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
