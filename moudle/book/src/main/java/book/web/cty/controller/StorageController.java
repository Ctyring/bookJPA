package book.web.cty.controller;
import java.util.List;
import java.util.Map;

import book.web.cty.pojo.StorageDetails;
import book.web.cty.service.StorageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import book.web.cty.pojo.Storage;
import book.web.cty.service.StorageService;

import book.web.cty.entity.PageResult;
import book.web.cty.entity.Result;
import book.web.cty.entity.StatusCode;
/**
 * storage控制器层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private StorageDetailsService storageDetailsService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",storageService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long id){
		return new Result(true,StatusCode.OK,"查询成功",storageService.findById(id));
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
		Page<Storage> pageList = storageService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Storage>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",storageService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param storage
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Storage storage  ){
		List<StorageDetails> list = storage.getDetailsList();
		for (StorageDetails details : list) {
			storageDetailsService.add(details);
		}
		storageService.add(storage);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param storage
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Storage storage, @PathVariable Long id ){
		storage.setId(id);
		storageService.update(storage);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long id){
		storageService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
