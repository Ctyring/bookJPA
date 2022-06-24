package book.web.cty.service;

import book.web.cty.dao.RoleDao;
import book.web.cty.dao.RolePermissionDao;
import book.web.cty.pojo.Role;
import book.web.cty.pojo.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cty
 * @date 2022/6/24
 */
@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionDao rolePermissionDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<RolePermission> findAll() {
        return rolePermissionDao.findAll();
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<RolePermission> findSearch(Map whereMap) {
        Specification<RolePermission> specification = createSpecification(whereMap);
        return rolePermissionDao.findAll(specification);
    }

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<RolePermission> findSearch(Map whereMap, int page, int size) {
        Specification<RolePermission> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return rolePermissionDao.findAll(specification, pageRequest);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<RolePermission> createSpecification(Map searchMap) {

        return new Specification<RolePermission>() {

            @Override
            public Predicate toPredicate(Root<RolePermission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
