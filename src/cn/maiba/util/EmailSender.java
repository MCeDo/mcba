package cn.maiba.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	//发件人邮箱的SMTP服务器
	private final String qqHost = "smtp.qq.com";
	
	//发件人的邮箱账号
	private String userName = "995011833";
	
	//发件人的邮箱密码
	private String password = "zdurlxiildzebbga";
	
	//发件人的邮箱
	private String from = "995011833@qq.com";
	
	//要发送的邮箱
	private String to;
	
	//邮箱标题
	private String title = "麦吧论坛通知";
	
	//邮箱内容
	private String message;

	public void send() {
		//创建邮件属性
		Properties p = new Properties();
		p.put("mail.smtp.host", qqHost);
		p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.smtp.auth", "true");
		Session mailSession = Session.getDefaultInstance(p);
		mailSession.setDebug(true);
		//创建一个消息
		Message msg = new MimeMessage(mailSession);
		try {
			//发件人地址
			InternetAddress fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			//收件人的地址
			InternetAddress toAddr = new InternetAddress(to);
			msg.setRecipient(Message.RecipientType.TO, toAddr);
			//添加邮件日期
			msg.setSentDate(new Date());
			msg.setSubject(title);
			//添加内容
			if(message != null && message.trim().length() > 0) {
				msg.setText(message);
			}else {
				msg.setText("No message to be sent!");
			}
			msg.saveChanges();
			
			int nMailPort = 465;
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(qqHost, nMailPort, userName, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("邮件发送成功");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public EmailSender() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailSender(String to, String message) {
		super();
		this.to = to;
		this.message = message;
	}

	public EmailSender(String userName, String password, String from, String to, String title, String message) {
		super();
		this.userName = userName;
		this.password = password;
		this.from = from;
		this.to = to;
		this.title = title;
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getQqHost() {
		return qqHost;
	}
	
	
}
