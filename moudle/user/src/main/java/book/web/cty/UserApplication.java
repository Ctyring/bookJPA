package book.web.cty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import redis.util.RedisUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import util.IdWorker;

@SpringBootApplication
@EnableSwagger2
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
	RedisUtil redisUtil(){
		return new RedisUtil();
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
