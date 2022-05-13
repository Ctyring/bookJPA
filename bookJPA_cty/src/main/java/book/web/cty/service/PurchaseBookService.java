package book.web.cty.service;

import book.web.cty.dao.BookDao;
import book.web.cty.dao.PurchaseBookDao;
import book.web.cty.pojo.Book;
import book.web.cty.pojo.PurchaseBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseBookService {
    @Autowired
    private PurchaseBookDao purchaseBookDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     * @return
     */
    public List<PurchaseBook> findAll() {
        return purchaseBookDao.findAll();
    }


    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<PurchaseBook> findSearch(Map whereMap, int page, int size) {
        Specification<PurchaseBook> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return purchaseBookDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<PurchaseBook> findSearch(Map whereMap) {
        Specification<PurchaseBook> specification = createSpecification(whereMap);
        return purchaseBookDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     * @param isbn
     * @return
     */
    public PurchaseBook findById(Long isbn) {
        return purchaseBookDao.findById(isbn).get();
    }

    /**
     * 增加
     * @param purchaseBook
     */
    public void add(PurchaseBook purchaseBook) {
        // book.setIsbn( idWorker.nextId()+"" ); 雪花分布式ID生成器
        purchaseBookDao.save(purchaseBook);
    }

    /**
     * 修改
     * @param book
     */
    public void update(PurchaseBook book) {
        purchaseBookDao.save(book);
    }

    /**
     * 删除
     * @param isbn
     */
    public void deleteById(Long isbn) {
        purchaseBookDao.deleteById(isbn);
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<PurchaseBook> createSpecification(Map searchMap) {

        return new Specification<PurchaseBook>() {

            @Override
            public Predicate toPredicate(Root<PurchaseBook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
