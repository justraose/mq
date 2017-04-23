package topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener implements MessageListener {
	
	int no;
	
	

	public MyListener(int no) {
		super();
		this.no = no;
	}



	public void onMessage(Message message) {
		try {
			System.out.println("收到消息（监听）(订阅) " + no + ": " + ((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
