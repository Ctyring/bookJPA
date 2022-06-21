package book.web.handler;

import book.web.loader.DynamicRouteLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.listener.RedisListener;
import util.BaseMap;

import javax.annotation.Resource;

/**
 * 路由刷新监听
 */
@Slf4j
@Component
public class LoderRouderHandler implements RedisListener {

    @Resource
    private DynamicRouteLoader dynamicRouteLoader;


    @Override
    public void onMessage(BaseMap message) {
        dynamicRouteLoader.refresh(message);
    }

}