package topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqProducer {

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
		
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		
		// 创建队列
		Destination destination = session.createTopic("firstTopic");
		// 创建生产者
		MessageProducer messageProducer = session.createProducer(destination);
		
		// 发送消息
		for (int i = 0; i < 10; i++) {
			TextMessage testMsg = session.createTextMessage("ActiveMq send MSg : " + i);
			System.out.println("消息发布 ： " + i);
			messageProducer.send(testMsg);
		}
		
		// 提交事务
		session.commit();
		
		// 关闭连接
		connection.close();
	}

}
