package entity;

/**
 *  状态
 */
public class StatusCode {

    public static final int OK=200;//成功
    public static final int ERROR=400;// 错误
    public static final int UNAUTHORIZED = 401; // 权限
    public static final int FAILED =402;// 失败
    public static final int NOTFOUND =404;// 失败

    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    public static final String SMS_TPL_TYPE_0  = "0";
    public static final String SMS_TPL_TYPE_1  = "1";
    public static final String SMS_TPL_TYPE_2  = "2";

    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";
    /** Token缓存时间：3600秒即一小时 */
    public static final int  TOKEN_EXPIRE_TIME  = 3600;

    /**
     * 是否用户已被冻结 0正常(解冻) 1冻结
     */
    public static final Integer USER_UNFREEZE = 0;
    public static final Integer USER_FREEZE = 1;

    /**
     * 是否用户注销 0未注销 1注销
     */
    public static final Integer DEL_FLAG_1 = 1;
    public static final Integer DEL_FLAG_0 = 0;
}
