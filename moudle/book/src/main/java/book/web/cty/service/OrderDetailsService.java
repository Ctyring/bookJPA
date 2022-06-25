package book.web.cty.service;


import book.web.cty.dao.OrderDetailsDao;

import book.web.cty.pojo.OrderDetails;
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

/**
 * @author cty
 * @date 2022/6/25
 */
@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     * @return
     */
    public List<OrderDetails> findAll() {
        return orderDetailsDao.findAll();
    }


    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<OrderDetails> findSearch(Map whereMap, int page, int size) {
        Specification<OrderDetails> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return orderDetailsDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<OrderDetails> findSearch(Map whereMap) {
        Specification<OrderDetails> specification = createSpecification(whereMap);
        return orderDetailsDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public OrderDetails findById(Long id) {
        return orderDetailsDao.findById(id).get();
    }

    /**
     * 增加
     * @param orderDetails
     */
    public void add(OrderDetails orderDetails) {
        // cart.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        orderDetailsDao.save(orderDetails);
    }

    /**
     * 修改
     * @param orderDetails
     */
    public void update(OrderDetails orderDetails) {
        orderDetailsDao.save(orderDetails);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(Long id) {
        orderDetailsDao.deleteById(id);
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<OrderDetails> createSpecification(Map searchMap) {

        return new Specification<OrderDetails>() {

            @Override
            public Predicate toPredicate(Root<OrderDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
