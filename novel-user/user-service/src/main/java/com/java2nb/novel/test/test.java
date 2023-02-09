package com.java2nb.novel.test;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;


@SpringBootTest
public class test {
    public JavaMailSenderImpl JavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("1084083934@qq.com");
        mailSender.setPassword("pmidayxwkguxhdhh");
        return  mailSender;
    }

    @Test
    public void test(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("1084083934@qq.com");
        simpleMailMessage.setTo("1084083934@qq.com");
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText("验证码：123456");
        JavaMailSender mailSender = JavaMailSender();
        mailSender.send(simpleMailMessage);
    }

}
