package book.web.cty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import book.web.cty.pojo.Book;
import book.web.cty.pojo.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import book.web.cty.util.IdWorker;

import book.web.cty.dao.OrderDao;
import book.web.cty.pojo.Order;

/**
 * order服务层
 * 
 * @author Administrator
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private BookService bookService;

	public void addOrder(Order order){
		for (OrderDetails orderDetails : order.getOrderDetails()){
			Book book = bookService.findById(orderDetails.getId());
			book.setInventory(book.getInventory() - orderDetails.getInventory());
			bookService.update(book);
		}
		add(order);
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Order> findSearch(Map whereMap, int page, int size) {
		Specification<Order> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return orderDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Order> findSearch(Map whereMap) {
		Specification<Order> specification = createSpecification(whereMap);
		return orderDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Order findById(Long id) {
		return orderDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param order
	 */
	public void add(Order order) {
		// order.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		orderDao.save(order);
	}

	/**
	 * 修改
	 * @param order
	 */
	public void update(Order order) {
		orderDao.save(order);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		orderDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Order> createSpecification(Map searchMap) {

		return new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
