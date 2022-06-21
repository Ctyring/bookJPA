package book.web.cty.controller;
import java.util.Map;

import book.web.cty.service.CategoryService;
import book.web.cty.service.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import book.web.cty.pojo.Book;
import book.web.cty.service.BookService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * book控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PressService pressService;


	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",bookService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param isbn ID
	 * @return
	 */
	@RequestMapping(value="/{isbn}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long isbn){
		return new Result(true,StatusCode.OK,"查询成功",bookService.findById(isbn));
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
		Page<Book> pageList = bookService.findSearch(searchMap, page, size);
		for (Book book: pageList) {
			if (!(book.getCategory() == null)){
				book.setCategoryName(categoryService.findById(Long.parseLong(book.getCategory())).getName());
			}
			if (!(book.getPress() == null)){
				book.setPressName(pressService.findById(Long.parseLong(book.getPress())).getName());
			}
		}
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Book>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",bookService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param book
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Book book  ){
		bookService.add(book);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param book
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Book book, @PathVariable Long isbn ){
		book.setIsbn(isbn);
		bookService.update(book);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param isbn
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long isbn){
		bookService.deleteById(isbn);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
