package topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqConsumer2 {

	public static void main(String[] args) throws Exception {
		ConnectionFactory jmsFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,
				ActiveMQConnection.DEFAULT_BROKER_URL);

		Connection connection = jmsFactory.createConnection();
		connection.start();

		Session session = connection.createSession(Boolean.FALSE,
				Session.AUTO_ACKNOWLEDGE);

		// 创建队列
		Destination destination = session.createTopic("firstTopic");
		// 创建消费者
		MessageConsumer messageConsumer = session.createConsumer(destination);

		// 监听
		messageConsumer.setMessageListener(new MyListener(2));
	}

}
