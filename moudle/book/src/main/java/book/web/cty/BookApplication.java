package book.web.cty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
//import rabbitmq.client.RabbitMqClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import book.web.cty.util.IdWorker;
import book.web.cty.redis.util.RedisUtil;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }

//    @Bean
//    RedisUtil redisUtil() {
//        return new RedisUtil();
//    }

//    @Bean
//    ConnectionFactory connectionFactory(){
//        return new ConnectionFactory() {
//            @Override
//            public Connection createConnection() throws AmqpException {
//                return null;
//            }
//
//            @Override
//            public String getHost() {
//                return null;
//            }
//
//            @Override
//            public int getPort() {
//                return 0;
//            }
//
//            @Override
//            public String getVirtualHost() {
//                return null;
//            }
//
//            @Override
//            public String getUsername() {
//                return null;
//            }
//
//            @Override
//            public void addConnectionListener(ConnectionListener connectionListener) {
//
//            }
//
//            @Override
//            public boolean removeConnectionListener(ConnectionListener connectionListener) {
//                return false;
//            }
//
//            @Override
//            public void clearConnectionListeners() {
//
//            }
//        }
//    }
//
//    @Bean
//    RabbitAdmin rabbitAdmin(){
//        return new RabbitAdmin(connectionFactory());
//    }
//

}
