package book.web.cty.ebus;

import cn.hutool.core.util.ObjectUtil;
import book.web.cty.entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import book.web.cty.rabbitmq.event.BusEventHandler;
import book.web.cty.rabbitmq.event.EventObj;
import book.web.cty.util.BaseMap;

/**
 * 消息处理器【发布订阅】
 */
@Slf4j
@Component(StatusCode.QUEUE_NAME)
public class DemoBusEvent implements BusEventHandler {


    @Override
    public void onMessage(EventObj obj) {
        if (ObjectUtil.isNotEmpty(obj)) {
            BaseMap baseMap = obj.getBaseMap();
            String orderId = baseMap.get("orderId");
            log.info("业务处理----订单ID:" + orderId);
        }
    }
}
