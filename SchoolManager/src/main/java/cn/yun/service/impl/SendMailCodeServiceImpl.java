package cn.yun.service.impl;

import cn.yun.service.SendMailCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class SendMailCodeServiceImpl implements SendMailCodeService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String from = "310895302@qq.com";

    private String subject = "验证码";

    private String conText = "你的本次验证码为：";

    private String endText = "验证码60s有效，请尽快输入，过期请重新获取。";


    //发送的验证码
    @Override
    public void studentCode() {
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        value.set("mailCode",String.valueOf(ThreadLocalRandom.current().nextInt(100000,1000000)),60, TimeUnit.SECONDS);
    }

    //发送邮件
    @Override
    public String sendCode(String to) {
        studentCode();
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String code = value.get("mailCode");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(conText+code+endText);
        javaMailSender.send(message);
        return code;
    }
}
