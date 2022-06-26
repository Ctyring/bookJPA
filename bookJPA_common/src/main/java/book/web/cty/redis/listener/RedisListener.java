package book.web.cty.redis.listener;

import book.web.cty.util.BaseMap;

/**
 * 自定义消息监听
 */
public interface RedisListener {

    void onMessage(BaseMap message);
}
