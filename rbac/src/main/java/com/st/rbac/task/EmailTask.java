package com.st.rbac.task;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 发送邮件的定时任务
 * @author mi
 *
 *	@Controller		定义SpringMVC中的控制器
 *	@Service		定义服务层的类
 *	@Repository		定义Dao层类
 *  @Component		定义组件
 *  
 *  这些注解本质是一样 的. 将注解的类, 作为Bean加入到Spring容器中
 */

@Component
public class EmailTask {
	
	// 做点事
	// 定义方法, 如何触发   每天0点
	@Scheduled(cron="* * 0 * * *")
	public void sendEmail() {
		
		System.out.println("发送邮件");
		
		// 统计销售信息
		
		// 将销售信息, 生成HTML
		// 表格
		// 
		
		// 发送邮件
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");// 指定邮件的发送服务器地址  参数1
		props.put("mail.smtp.auth", "true");//服务器是否要验证用户的身份信息
		
		Session session = Session.getInstance(props);// 得到Session
		session.setDebug(true);// 代表启用debug模式，可以在控制台输出smtp协议应答的过程
		
       
		try {
			//创建一个MimeMessage格式的邮件
	        MimeMessage message = new MimeMessage(session);
	        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8"); // MimeMessageHelper-->spring的  不加后面两个参数会乱码  
	       
	        //设置发送者
	        messageHelper.setFrom("273197103@qq.com");//设置发送的邮件地址
			
			//设置接收者
	        messageHelper.setTo("273197103@qq.com");
	        
	        //设置邮件的主题
	        messageHelper.setSubject("xxxx");
	        //设置邮件的内容
	        messageHelper.setText("<h1>xxxxxxxx</h1>", true); // true表示内容中的HTML标签将会被解析
	        //保存邮件
	        //messageHelper.saveChanges();
	        
	        //得到发送邮件的服务器(这里用的是smtp服务器)
			Transport transport = session.getTransport("smtp");

			// 发送者的账号连接到smtp服务器上
			transport.connect("smtp.qq.com", "273197103@qq.com", "tpkkrnumboywbgda");
			// 发送信息
			transport.sendMessage(message, message.getAllRecipients());
			// 关闭服务器通道
			transport.close();
	        
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 定时清理文件
	 * 获取文件夹中的文件列表  ->　File
	 */
	
}
