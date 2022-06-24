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

import book.web.cty.dao.MessageDao;
import book.web.cty.pojo.Message;

/**
 * message服务层
 * 
 * @author Administrator
 *
 */
@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Message> findAll() {
		return messageDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Message> findSearch(Map whereMap, int page, int size) {
		Specification<Message> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return messageDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Message> findSearch(Map whereMap) {
		Specification<Message> specification = createSpecification(whereMap);
		return messageDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Message findById(Long id) {
		return messageDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param message
	 */
	public void add(Message message) {
		// message.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		messageDao.save(message);
	}

	/**
	 * 修改
	 * @param message
	 */
	public void update(Message message) {
		messageDao.save(message);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		messageDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Message> createSpecification(Map searchMap) {

		return new Specification<Message>() {

			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // content
                if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
                	predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
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
