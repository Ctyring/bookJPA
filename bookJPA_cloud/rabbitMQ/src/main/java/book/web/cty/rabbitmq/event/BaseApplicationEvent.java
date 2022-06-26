package book.web.cty.rabbitmq.event;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import book.web.cty.util.SpringContextHolder;

/**
 * 监听远程事件,并分发消息到业务模块消息处理器
 */
@Component
public class BaseApplicationEvent implements ApplicationListener<CtyRemoteApplicationEvent> {

    @Override
    public void onApplicationEvent(CtyRemoteApplicationEvent jeecgRemoteApplicationEvent) {
        EventObj eventObj = jeecgRemoteApplicationEvent.getEventObj();
        if (ObjectUtil.isNotEmpty(eventObj)) {
            //获取业务模块消息处理器
            BusEventHandler busEventHandler = SpringContextHolder.getHandler(eventObj.getHandlerName(), BusEventHandler.class);
            if (ObjectUtil.isNotEmpty(busEventHandler)) {
                //通知业务模块
                busEventHandler.onMessage(eventObj);
            }
        }
    }

}
