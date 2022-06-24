package book.web;

import book.web.loader.DynamicRouteLoader;
import cn.dev33.satoken.SaManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import redis.util.RedisUtil;

import javax.annotation.Resource;

/**
 * @author cty
 * @date 2022/6/18
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {

    @Resource
    private DynamicRouteLoader dynamicRouteLoader;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GatewayApplication.class, args);
//        String userName = applicationContext.getEnvironment().getProperty("jeecg.test");
//        System.err.println("user name :" +userName);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

    /**
     * 容器初始化后加载路由
     * @param strings
     */
    @Override
    public void run(String... strings) {
        dynamicRouteLoader.refresh(null);
    }

    @Bean
    RedisUtil redisUtil(){
        return new RedisUtil();
    }
}
