package book.web.cty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import book.web.cty.redis.util.RedisUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import book.web.cty.util.IdWorker;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class SystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

//	@Bean RedisUtil redisUtil(){
//		return new RedisUtil();
//	}


}
