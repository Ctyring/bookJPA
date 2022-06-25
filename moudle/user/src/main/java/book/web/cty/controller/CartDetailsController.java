package book.web.cty.controller;

import book.web.cty.pojo.Cart;
import book.web.cty.pojo.CartDetails;
import book.web.cty.service.CartDetailsService;
import book.web.cty.service.CartService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author cty
 * @date 2022/6/25
 */
@RestController
@RequestMapping("/cart-details")
@Api(tags = "购物车详情")
public class CartDetailsController {
    @Autowired
    private CartDetailsService cartDetailsService;


    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",cartDetailsService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable Long id){
        return new Result(true,StatusCode.OK,"查询成功",cartDetailsService.findById(id));
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
        Page<CartDetails> pageList = cartDetailsService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<CartDetails>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",cartDetailsService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param cartDetails
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody CartDetails cartDetails  ){
        cartDetailsService.add(cartDetails);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param cartDetails
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody CartDetails cartDetails, @PathVariable Long id ){
        cartDetails.setId(id);
        cartDetailsService.update(cartDetails);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable Long id){
        cartDetailsService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
