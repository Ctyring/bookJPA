package book.web.cty.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 设置静态参数初始化
 */
@Component
@Data
public class StaticConfig {

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

//    @Bean
//    public void initStatic() {
//       DySmsHelper.setAccessKeyId(accessKeyId);
//       DySmsHelper.setAccessKeySecret(accessKeySecret);
//       EmailSendMsgHandle.setEmailFrom(emailFrom);
//    }

}
