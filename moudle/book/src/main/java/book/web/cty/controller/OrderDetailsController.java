package book.web.cty.controller;

import book.web.cty.pojo.OrderDetails;
import book.web.cty.service.OrderDetailsService;
import book.web.cty.entity.PageResult;
import book.web.cty.entity.Result;
import book.web.cty.entity.StatusCode;
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
@RequestMapping("/order-details")
@Api(tags = "订单详情")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;


    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",orderDetailsService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable Long id){
        return new Result(true,StatusCode.OK,"查询成功",orderDetailsService.findById(id));
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
        Page<OrderDetails> pageList = orderDetailsService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<OrderDetails>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",orderDetailsService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param orderDetails
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody OrderDetails orderDetails  ){
        orderDetailsService.add(orderDetails);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param orderDetails
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody OrderDetails orderDetails, @PathVariable Long id ){
        orderDetails.setId(id);
        orderDetailsService.update(orderDetails);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable Long id){
        orderDetailsService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
