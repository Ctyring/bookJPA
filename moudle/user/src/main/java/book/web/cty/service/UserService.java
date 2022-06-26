package book.web.cty.service;

import book.web.cty.dao.UserDao;
import book.web.cty.pojo.User;
import book.web.cty.entity.Result;
import book.web.cty.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import book.web.cty.util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * user服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;

	public User findUserByEmail(String email){
		return userDao.findUserByEmail(email);
	}

	public User findUserByUsername(String username){
		return userDao.findUserByUsername(username);
	}

	public Result checkUserIsEffective(User user){
		if (user == null){
			return new Result(false, StatusCode.ERROR, "请先注册");
		}
		if (StatusCode.DEL_FLAG_1.equals(user.getDelFlag())){
			return new Result(false, StatusCode.ERROR, "该用户已经注销！");
		}
		if (StatusCode.USER_FREEZE.equals(user.getStatus())){
			return new Result(false, StatusCode.ERROR, "该用户已经冻结！");
		}
		return new Result(true, StatusCode.OK, "");
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return userDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param user
	 */
	public void add(User user) {
		// user.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		userDao.save(user);
	}

	/**
	 * 修改
	 * @param user
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // birthday
                if (searchMap.get("birthday")!=null && !"".equals(searchMap.get("birthday"))) {
                	predicateList.add(cb.like(root.get("birthday").as(String.class), "%"+(String)searchMap.get("birthday")+"%"));
                }
                // role
                if (searchMap.get("role")!=null && !"".equals(searchMap.get("role"))) {
                	predicateList.add(cb.like(root.get("role").as(String.class), "%"+(String)searchMap.get("role")+"%"));
                }
                // name
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // password
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // picture
                if (searchMap.get("picture")!=null && !"".equals(searchMap.get("picture"))) {
                	predicateList.add(cb.like(root.get("picture").as(String.class), "%"+(String)searchMap.get("picture")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
