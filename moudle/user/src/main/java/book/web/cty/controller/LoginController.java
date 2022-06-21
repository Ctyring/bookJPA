package book.web.cty.controller;

import book.web.cty.message.EmailSendMsgHandle;
import book.web.cty.pojo.User;
import book.web.cty.service.UserService;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.util.RedisUtil;
import util.oConvertUtils;

/**
 * @Author cty
 * @since 2022-6-21
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "用户登录")
@CrossOrigin
public class LoginController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    @ApiOperation("发送邮箱验证")
    @PostMapping(value = "/sms")
    public Result sms(@RequestBody JSONObject jsonObject) {
        // 验证信息
        String mail = jsonObject.get("mail").toString();
        String mode = jsonObject.get("mode").toString();
        if (oConvertUtils.isEmpty(mail)) {
            return new Result(false, StatusCode.ERROR, "邮箱不允许为空");
        }
        Object object = redisUtil.get(mail);
        if (object != null) {
            return new Result(false, StatusCode.ERROR, "验证码尚未过期！");
        }

        // 生成随机数
        String captcha = RandomUtil.randomNumbers(6);
        JSONObject obj = new JSONObject();
        obj.put("code", captcha);

        User user = userService.findUserByEmail(mail);
        // 注册
        if (StatusCode.SMS_TPL_TYPE_1.equals(mode)) {
            if (user != null) {
                return new Result(false, StatusCode.ERROR, "该邮箱已经注册，请直接登录");
            }
            new EmailSendMsgHandle().SendMsg(mail, "注册验证", "您正在注册小鸭收书平台账号，验证码为：" + captcha);

        } else {
            Result result = userService.checkUserIsEffective(user);
            if (!result.isFlag()) {
                return result;
            }
            if (StatusCode.SMS_TPL_TYPE_0.equals(mode)) {
                new EmailSendMsgHandle().SendMsg(mail, "登录验证", "您正在登录小鸭收书平台，验证码为：" + captcha);

            } else if (StatusCode.SMS_TPL_TYPE_2.equals(mode)) {
                new EmailSendMsgHandle().SendMsg(mail, "找回密码", "您正在小鸭收书平台找回密码，验证码为：" + captcha);

            }
        }
        redisUtil.set(mail, captcha, 600);
        return new Result(true, StatusCode.OK, "已经向邮箱发送验证码，请注意查收");
    }
}
