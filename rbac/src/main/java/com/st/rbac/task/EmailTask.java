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
 * �����ʼ��Ķ�ʱ����
 * @author mi
 *
 *	@Controller		����SpringMVC�еĿ�����
 *	@Service		�����������
 *	@Repository		����Dao����
 *  @Component		�������
 *  
 *  ��Щע�Ȿ����һ�� ��. ��ע�����, ��ΪBean���뵽Spring������
 */

@Component
public class EmailTask {
	
	// ������
	// ���巽��, ��δ���   ÿ��0��
	@Scheduled(cron="* * 0 * * *")
	public void sendEmail() {
		
		System.out.println("�����ʼ�");
		
		// ͳ��������Ϣ
		
		// ��������Ϣ, ����HTML
		// ���
		// 
		
		// �����ʼ�
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");// ָ���ʼ��ķ��ͷ�������ַ  ����1
		props.put("mail.smtp.auth", "true");//�������Ƿ�Ҫ��֤�û��������Ϣ
		
		Session session = Session.getInstance(props);// �õ�Session
		session.setDebug(true);// ��������debugģʽ�������ڿ���̨���smtpЭ��Ӧ��Ĺ���
		
       
		try {
			//����һ��MimeMessage��ʽ���ʼ�
	        MimeMessage message = new MimeMessage(session);
	        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8"); // MimeMessageHelper-->spring��  ���Ӻ�����������������  
	       
	        //���÷�����
	        messageHelper.setFrom("273197103@qq.com");//���÷��͵��ʼ���ַ
			
			//���ý�����
	        messageHelper.setTo("273197103@qq.com");
	        
	        //�����ʼ�������
	        messageHelper.setSubject("xxxx");
	        //�����ʼ�������
	        messageHelper.setText("<h1>xxxxxxxx</h1>", true); // true��ʾ�����е�HTML��ǩ���ᱻ����
	        //�����ʼ�
	        //messageHelper.saveChanges();
	        
	        //�õ������ʼ��ķ�����(�����õ���smtp������)
			Transport transport = session.getTransport("smtp");

			// �����ߵ��˺����ӵ�smtp��������
			transport.connect("smtp.qq.com", "273197103@qq.com", "tpkkrnumboywbgda");
			// ������Ϣ
			transport.sendMessage(message, message.getAllRecipients());
			// �رշ�����ͨ��
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
	 * ��ʱ�����ļ�
	 * ��ȡ�ļ����е��ļ��б�  ->��File
	 */
	
}
