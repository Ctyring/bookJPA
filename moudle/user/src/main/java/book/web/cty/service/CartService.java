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

import book.web.cty.dao.CartDao;
import book.web.cty.pojo.Cart;

/**
 * cart服务层
 * 
 * @author Administrator
 *
 */
@Service
public class CartService {

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Cart> findAll() {
		return cartDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Cart> findSearch(Map whereMap, int page, int size) {
		Specification<Cart> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return cartDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Cart> findSearch(Map whereMap) {
		Specification<Cart> specification = createSpecification(whereMap);
		return cartDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Cart findById(Long id) {
		return cartDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param cart
	 */
	public void add(Cart cart) {
		// cart.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		cartDao.save(cart);
	}

	/**
	 * 修改
	 * @param cart
	 */
	public void update(Cart cart) {
		cartDao.save(cart);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		cartDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Cart> createSpecification(Map searchMap) {

		return new Specification<Cart>() {

			@Override
			public Predicate toPredicate(Root<Cart> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
