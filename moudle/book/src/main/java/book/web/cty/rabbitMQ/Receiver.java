package book.web.cty.rabbitMQ;

import book.web.cty.annotation.RabbitComponent;
import book.web.cty.entity.Result;
import book.web.cty.pojo.Book;
import book.web.cty.pojo.Order;
import book.web.cty.pojo.OrderDetails;
import book.web.cty.service.BookService;
import book.web.cty.service.OrderService;
import com.rabbitmq.client.Channel;
import book.web.cty.entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import book.web.cty.rabbitmq.core.BaseRabbiMqHandler;
import book.web.cty.rabbitmq.listenter.MqListener;
import book.web.cty.util.BaseMap;

/**
 * RabbitMq接受者1
 * （@RabbitListener声明类上，一个类只能监听一个队列）
 */
@Slf4j
@RabbitComponent(value = "receiver")
public class Receiver extends BaseRabbiMqHandler<BaseMap> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @RabbitListener(queues = StatusCode.QUEUE_NAME)
    public void onMessage(BaseMap baseMap, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        super.onMessage(baseMap, deliveryTag, channel, new MqListener<BaseMap>() {
            @Override
            public void handler(BaseMap map, Channel channel) {
                Order order = map.get("order");
                for (OrderDetails orderDetails : order.getOrderDetails()){
                    Book book = bookService.findById(orderDetails.getId());
                    if(book.getInventory() < orderDetails.getInventory()){
                        return;
                    }
                }

                orderService.addOrder(order);
            }
        });
    }

}