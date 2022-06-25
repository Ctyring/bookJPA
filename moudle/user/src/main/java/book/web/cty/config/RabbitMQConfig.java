package book.web.cty.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * @author cty
 * @date 2022/6/25
 */
public class RabbitMQConfig {
    @Bean
    public Queue queue() {
        return new Queue("queue", true);
    }
}
