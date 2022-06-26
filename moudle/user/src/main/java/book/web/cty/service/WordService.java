package book.web.cty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import book.web.cty.util.IdWorker;

import book.web.cty.dao.WordDao;
import book.web.cty.pojo.Word;

/**
 * word服务层
 * 
 * @author Administrator
 *
 */
@Service
public class WordService {

	@Autowired
	private WordDao wordDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Word> findAll() {
		return wordDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Word> findSearch(Map whereMap, int page, int size) {
		Specification<Word> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return wordDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Word> findSearch(Map whereMap) {
		Specification<Word> specification = createSpecification(whereMap);
		return wordDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Word findById(Long id) {
		return wordDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param word
	 */
	public void add(Word word) {
		// word.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		wordDao.save(word);
	}

	/**
	 * 修改
	 * @param word
	 */
	public void update(Word word) {
		wordDao.save(word);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		wordDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Word> createSpecification(Map searchMap) {

		return new Specification<Word>() {

			@Override
			public Predicate toPredicate(Root<Word> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // tag
                if (searchMap.get("tag")!=null && !"".equals(searchMap.get("tag"))) {
                	predicateList.add(cb.like(root.get("tag").as(String.class), "%"+(String)searchMap.get("tag")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
