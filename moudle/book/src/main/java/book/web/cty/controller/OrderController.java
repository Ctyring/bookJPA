package book.web.cty.controller;
import java.util.Map;
import java.util.Objects;

import book.web.cty.pojo.Book;
import book.web.cty.pojo.OrderDetails;
import book.web.cty.service.BookService;
import book.web.cty.util.BaseMap;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import book.web.cty.pojo.Order;
import book.web.cty.service.OrderService;

import book.web.cty.entity.PageResult;
import book.web.cty.entity.Result;
import book.web.cty.entity.StatusCode;
import book.web.cty.rabbitmq.client.RabbitMqClient;
import book.web.cty.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * order控制器层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单控制")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BookService bookService;

	@Autowired
	private RabbitMqClient rabbitMqClient;

	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",orderService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long id){
		return new Result(true,StatusCode.OK,"查询成功",orderService.findById(id));
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
		Page<Order> pageList = orderService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Order>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",orderService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param order
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(HttpServletRequest httpServletRequest, @RequestBody Order order  ){
		String id = JwtUtil.getIdByToken(httpServletRequest);
		if (!StpUtil.isLogin()){
			return new Result(false, StatusCode.FAILED, "请先登录");
		}
		if (!Objects.equals(id, String.valueOf(order.getId()))){
			return new Result(false, StatusCode.FAILED, "购买失败");
		}
		// 订单逻辑
		for (OrderDetails orderDetails : order.getOrderDetails()){
			Book book = bookService.findById(orderDetails.getId());
			if(book.getInventory() < orderDetails.getInventory()){
				return new Result(true,StatusCode.FAILED,"库存不足");
			}
		}
		BaseMap map = new BaseMap();
		map.put("order", order);
		map.put("id", id);
		rabbitMqClient.sendMessage(StatusCode.QUEUE_NAME, map);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param order
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Order order, @PathVariable Long id ){
		order.setId(id);
		orderService.update(order);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long id){
		orderService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
