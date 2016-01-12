package com.qihua.common.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.qihua.common.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
@Service
// @Component
public class MailService {

    private static final String DEFAULT_ENCODING = "utf-8";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfiguration;

    private Template template;

    /**
     * 密码重置邮件。
     *
     */
    @Async
    public void sendRestMail(Map<String, Object> map) throws Exception {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);

        helper.setFrom(Constants.DEFAULT_MAIL);
        helper.setTo((String) map.get("recipient"));
        msg.setSubject((String) map.get("subject"));

        String content = generateContent((String) map.get("templateName"), map);
        helper.setText(content, true);

        mailSender.send(msg);
    }

    private String generateContent(String templateName, Map<String, Object> map) throws MessagingException {
        try {
            template = freemarkerConfiguration.getTemplate(templateName, DEFAULT_ENCODING);
            Map<String, Object> context = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
        } catch (IOException e) {
            throw new MessagingException("FreeMarker模板不存在", e);
        } catch (TemplateException e) {
            throw new MessagingException("FreeMarker处理失败", e);
        }
    }

}
