package ${package}.amqp.sender;

public interface ISenderAmqp {
	
	void sendMessage(String queueName, Object inputMessage);
	void sendMessageObject(String queueName, Object inputObject);
	void sendMessage(String queueName, String messageString);
}
