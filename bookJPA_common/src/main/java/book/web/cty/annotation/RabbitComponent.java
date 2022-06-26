package book.web.cty.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author:cty
 * @Date:2022-06-26 10:43
 * @Description: 消息队列初始化注解
 **/
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RabbitComponent {
    @AliasFor(
            annotation = Component.class
    )
    String value();
}
