package book.web.cty.rabbitmq.event;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 自定义网关刷新远程事件
 *
 * @author : zyf
 * @date :2020-11-10
 */
@Data
public class CtyRemoteApplicationEvent extends RemoteApplicationEvent {

    private CtyRemoteApplicationEvent() {
    }

    private EventObj eventObj;

    public CtyRemoteApplicationEvent(EventObj source, String originService, String destinationService) {
        super(source, originService, destinationService);
        this.eventObj = source;
    }

    public CtyRemoteApplicationEvent(EventObj source, String originService) {
        super(source, originService, null);
        this.eventObj = source;
    }
}
