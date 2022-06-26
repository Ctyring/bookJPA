package book.web.cty.message;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import book.web.cty.util.oConvertUtils;

import java.util.Properties;

public class EmailSendMsgHandle implements ISendMsgHandle {
    static String emailFrom;

//    @Resource
//    private JavaMailSender mailSender;

    public static void setEmailFrom(String emailFrom) {
        EmailSendMsgHandle.emailFrom = emailFrom;
    }

    @Override
    public void SendMsg(String es_receiver, String es_title, String es_content) {
//        JavaMailSender mailSender = (JavaMailSender) SpringContextUtils.getBean("mailSender");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("220.181.12.13");
        mailSender.setUsername("caotiyuan@163.com");
        mailSender.setPort(465);
        mailSender.setPassword("DZCDISXQEFZYEUXP");
        mailSender.setProtocol("smtp");
        mailSender.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.starttls.required", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        mailSender.setJavaMailProperties(properties);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        //update-begin-author：taoyan date:20200811 for:配置类数据获取
        if(oConvertUtils.isEmpty(emailFrom)){
//            StaticConfig staticConfig = SpringContextUtils.getBean(StaticConfig.class);
//            setEmailFrom(staticConfig.getEmailFrom());
            emailFrom = "caotiyuan@163.com";
        }
        //update-end-author：taoyan date:20200811 for:配置类数据获取
        try {
            helper = new MimeMessageHelper(message, true);
            // 设置发送方邮箱地址
            helper.setFrom(emailFrom);
            helper.setTo(es_receiver);
            helper.setSubject(es_title);
            helper.setText(es_content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
