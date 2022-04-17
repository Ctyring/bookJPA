package book.web.cty.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import book.web.cty.pojo.UpmsPost;
import book.web.cty.service.UpmsPostService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * upmsPost控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/upmsPost")
public class UpmsPostController {

	@Autowired
	private UpmsPostService upmsPostService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",upmsPostService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long id){
		return new Result(true,StatusCode.OK,"查询成功",upmsPostService.findById(id));
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
		Page<UpmsPost> pageList = upmsPostService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<UpmsPost>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",upmsPostService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param upmsPost
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody UpmsPost upmsPost  ){
		upmsPostService.add(upmsPost);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param upmsPost
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody UpmsPost upmsPost, @PathVariable Long id ){
		upmsPost.setId(id);
		upmsPostService.update(upmsPost);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long id){
		upmsPostService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
