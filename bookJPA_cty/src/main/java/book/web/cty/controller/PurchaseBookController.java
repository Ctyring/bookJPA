package book.web.cty.controller;

import book.web.cty.pojo.PurchaseBook;
import book.web.cty.service.CategoryService;
import book.web.cty.service.PressService;
import book.web.cty.service.PurchaseBookService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/purchaseBook")
public class PurchaseBookController {
    @Autowired
    private PurchaseBookService purchaseBookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PressService pressService;


    @GetMapping("/test")
    @ApiOperation(value = "获取当前用户")
    public Result nowUser(HttpServletResponse response, HttpServletRequest request){
        return new Result(true, StatusCode.OK, "查询成功", request.getSession().getAttribute("user"));
    }

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",purchaseBookService.findAll());
    }

    /**
     * 根据ID查询
     * @param isbn ID
     * @return
     */
    @RequestMapping(value="/{isbn}",method= RequestMethod.GET)
    public Result findById(@PathVariable Long isbn){
        return new Result(true,StatusCode.OK,"查询成功",purchaseBookService.findById(isbn));
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
        Page<PurchaseBook> pageList = purchaseBookService.findSearch(searchMap, page, size);
        for (PurchaseBook book: pageList) {
            book.setCategoryName(categoryService.findById(Long.parseLong(book.getCategory())).getName());
            book.setPressName(pressService.findById(Long.parseLong(book.getPress())).getName());
        }
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<PurchaseBook>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",purchaseBookService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param book
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody PurchaseBook book  ){
        purchaseBookService.add(book);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param book
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody PurchaseBook book, @PathVariable Long isbn ){
        book.setIsbn(isbn);
        purchaseBookService.update(book);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param isbn
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable Long isbn){
        purchaseBookService.deleteById(isbn);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
