package book.web.cty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import book.web.cty.redis.util.RedisUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import book.web.cty.util.IdWorker;
import book.web.cty.util.MinioUtils;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
