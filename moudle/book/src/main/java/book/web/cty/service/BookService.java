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

import book.web.cty.dao.BookDao;
import book.web.cty.pojo.Book;

/**
 * book服务层
 * 
 * @author Administrator
 *
 */
@Service
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Book> findSearch(Map whereMap, int page, int size) {
		Specification<Book> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return bookDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Book> findSearch(Map whereMap) {
		Specification<Book> specification = createSpecification(whereMap);
		return bookDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param isbn
	 * @return
	 */
	public Book findById(Long isbn) {
		return bookDao.findById(isbn).get();
	}

	/**
	 * 增加
	 * @param book
	 */
	public void add(Book book) {
		// book.setIsbn( idWorker.nextId()+"" ); 雪花分布式ID生成器
		bookDao.save(book);
	}

	/**
	 * 修改
	 * @param book
	 */
	public void update(Book book) {
		bookDao.save(book);
	}

	/**
	 * 删除
	 * @param isbn
	 */
	public void deleteById(Long isbn) {
		bookDao.deleteById(isbn);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Book> createSpecification(Map searchMap) {

		return new Specification<Book>() {

			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // category
                if (searchMap.get("category")!=null && !"".equals(searchMap.get("category"))) {
                	predicateList.add(cb.like(root.get("category").as(String.class), "%"+(String)searchMap.get("category")+"%"));
                }
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // picture
                if (searchMap.get("picture")!=null && !"".equals(searchMap.get("picture"))) {
                	predicateList.add(cb.like(root.get("picture").as(String.class), "%"+(String)searchMap.get("picture")+"%"));
                }
                // press
                if (searchMap.get("press")!=null && !"".equals(searchMap.get("press"))) {
                	predicateList.add(cb.like(root.get("press").as(String.class), "%"+(String)searchMap.get("press")+"%"));
                }
				if (searchMap.get("isSale")!=null && !"".equals(searchMap.get("isSale"))) {
					if (searchMap.get("isSale").equals(true)){
						predicateList.add(cb.isTrue(root.get("isSale").as(Boolean.class)));
					}
					else{
						predicateList.add(cb.isFalse(root.get("isSale").as(Boolean.class)));
					}
				}
				if (searchMap.get("isPurchase")!=null && !"".equals(searchMap.get("isPurchase"))) {
					if (searchMap.get("isPurchase").equals(true)){
						predicateList.add(cb.isTrue(root.get("isPurchase").as(Boolean.class)));
					}
					else{
						predicateList.add(cb.isFalse(root.get("isPurchase").as(Boolean.class)));
					}
				}
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
