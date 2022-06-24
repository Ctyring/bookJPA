package book.web.cty.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import book.web.cty.pojo.Category;
import book.web.cty.service.CategoryService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * category控制器层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",categoryService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long id){
		return new Result(true,StatusCode.OK,"查询成功",categoryService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Category> pageList = categoryService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Category>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",categoryService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param category
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Category category  ){
		categoryService.add(category);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param category
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Category category, @PathVariable Long id ){
		category.setId(id);
		categoryService.update(category);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long id){
		categoryService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
