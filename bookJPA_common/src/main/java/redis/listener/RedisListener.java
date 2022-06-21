package redis.listener;

import util.BaseMap;

/**
 * 自定义消息监听
 */
public interface RedisListener {

    void onMessage(BaseMap message);
}
