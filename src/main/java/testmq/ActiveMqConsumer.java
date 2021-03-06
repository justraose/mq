package testmq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqConsumer {
	/**
	 * easy producer sample
	 * don't follow
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ConnectionFactory jmsFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
		
		Connection connection = jmsFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		
		// 创建队列
		Destination destination = session.createQueue("firstQueue");
		// 创建生产者
		MessageConsumer messageConsumer = session.createConsumer(destination);
		
		// 发送消息
		for (int i = 0; i < 10; i++) {
			TextMessage recvMsg = (TextMessage)messageConsumer.receive();
			System.out.println(recvMsg.getText());
		}
	}
}
