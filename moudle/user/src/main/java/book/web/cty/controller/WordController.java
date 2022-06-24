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

import book.web.cty.pojo.Word;
import book.web.cty.service.WordService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * word控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/word")
public class WordController {

	@Autowired
	private WordService wordService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",wordService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long id){
		return new Result(true,StatusCode.OK,"查询成功",wordService.findById(id));
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
		Page<Word> pageList = wordService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Word>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",wordService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param word
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Word word  ){
		wordService.add(word);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param word
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Word word, @PathVariable Long id ){
		word.setId(id);
		wordService.update(word);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long id){
		wordService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
