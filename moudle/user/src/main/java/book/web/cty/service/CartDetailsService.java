package book.web.cty.service;

import book.web.cty.dao.CartDao;
import book.web.cty.dao.CartDetailsDao;
import book.web.cty.pojo.Cart;
import book.web.cty.pojo.CartDetails;
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
public class CartDetailsService {
    @Autowired
    private CartDetailsDao cartDetailsDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     * @return
     */
    public List<CartDetails> findAll() {
        return cartDetailsDao.findAll();
    }


    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<CartDetails> findSearch(Map whereMap, int page, int size) {
        Specification<CartDetails> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return cartDetailsDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<CartDetails> findSearch(Map whereMap) {
        Specification<CartDetails> specification = createSpecification(whereMap);
        return cartDetailsDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public CartDetails findById(Long id) {
        return cartDetailsDao.findById(id).get();
    }

    /**
     * 增加
     * @param cartDetails
     */
    public void add(CartDetails cartDetails) {
        // cart.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        cartDetailsDao.save(cartDetails);
    }

    /**
     * 修改
     * @param cartDetails
     */
    public void update(CartDetails cartDetails) {
        cartDetailsDao.save(cartDetails);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(Long id) {
        cartDetailsDao.deleteById(id);
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<CartDetails> createSpecification(Map searchMap) {

        return new Specification<CartDetails>() {

            @Override
            public Predicate toPredicate(Root<CartDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
