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

import book.web.cty.pojo.DictItem;
import book.web.cty.service.DictItemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * dictItem控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/dictItem")
public class DictItemController {

	@Autowired
	private DictItemService dictItemService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",dictItemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long id){
		return new Result(true,StatusCode.OK,"查询成功",dictItemService.findById(id));
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
		Page<DictItem> pageList = dictItemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<DictItem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",dictItemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param dictItem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody DictItem dictItem  ){
		dictItemService.add(dictItem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param dictItem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody DictItem dictItem, @PathVariable Long id ){
		dictItem.setId(id);
		dictItemService.update(dictItem);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long id){
		dictItemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
