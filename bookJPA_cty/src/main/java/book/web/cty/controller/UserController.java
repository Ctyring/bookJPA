package book.web.cty.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import book.web.cty.pojo.User;
import book.web.cty.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.client.RestTemplate;
import util.MD5Util;
import util.PasswordUtil;
import redis.util.RedisUtil;
import util.RandImageUtil;
import util.oConvertUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * user控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    private RestTemplate restTemplate = new RestTemplate();

    private RedisUtil redisUtil = new RedisUtil();

    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";
    /**
     * 后台生成图形验证码 ：有效
     * @param response
     * @param key
     */
    @ApiOperation("获取验证码")
    @GetMapping(value = "/randomImage/{key}")
    public Result randomImage(HttpServletResponse response, @PathVariable String key){
        try {
            String code = RandomUtil.randomString(BASE_CHECK_CODES,4);
            String lowerCaseCode = code.toLowerCase();
            String realKey = MD5Util.MD5Encode(lowerCaseCode+key, "utf-8");
            redisUtil.set(realKey, lowerCaseCode, 60);
            String base64 = RandImageUtil.generate(code);
            return new Result(true, StatusCode.OK, base64);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "获取验证码失败");
        }
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result userRegitser(@RequestBody JSONObject object, User user) {
        String email = user.getEmail();
        String smscode = object.getString("smscode");
        Object code = redisUtil.get(email);
        String username = object.getString("username");
        if (oConvertUtils.isEmpty(username)){
            username = email;
        }
        String password = object.getString("password");
        if (oConvertUtils.isEmpty(password)){
            password = RandomUtil.randomString(8);
        }
        User user1 = userService.findUserByUsername(username);
        if (user1 != null){
            return new Result(false, StatusCode.FAILED, "用户名已经被注册");
        }
        if (oConvertUtils.isNotEmpty(email)){
            User user2 = userService.findUserByEmail(email);
            if (user2 != null){
                return new Result(false, StatusCode.FAILED, "邮箱已经被注册");
            }
        }

        if(null == code){
            return new Result(false, StatusCode.FAILED, "邮箱验证码已经过期请重新获取");
        }
        if(!smscode.equals(code.toString())){
            return new Result(false, StatusCode.FAILED, "验证码错误");
        }

        try{
            user.setCreateTime(new Date());
            String salt = oConvertUtils.randomGen(8);
            String passwordEncode = PasswordUtil.encrypt(username, password, salt);
            user.setSalt(salt);
            user.setUsername(username);
            user.setPassword(passwordEncode);
            user.setEmail(email);
            user.setRole(2L);
            userService.add(user);
            return new Result(true, StatusCode.FAILED, "注册成功");
        }catch (Exception e){
            return new Result(false, StatusCode.FAILED, "注册失败");
        }
    }

    /**
     * 获取Session
     *
     * @param code code
     * @return session
     */
    @RequestMapping(value = "/code2session/{code}", method = RequestMethod.GET)
    public Result code2Session(@PathVariable String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";
        HashMap<String, String> map = new HashMap();
        map.put("js_code", code);
        map.put("grant_type", "uthorization_code");
        map.put("appid", "wx6f7aad37961ba5e9");
        map.put("secret", "65fd501c33566f78e4cd25223b95c38d");
        JSONObject res = JSONObject.parseObject(restTemplate.getForObject(url, String.class, map));
        return new Result(true, StatusCode.OK, "获取成功", res.get("openid"));
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable Long id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        userService.add(user);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
