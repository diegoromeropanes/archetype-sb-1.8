package ${package}.amqp.sender.impl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ${package}.amqp.sender.ISenderAmqp;
import ${package}.annotation.TimeDetector;
import lombok.extern.slf4j.Slf4j;

@Service("amqpMessageSender")
@Slf4j
public class SenderAmqpImpl implements ISenderAmqp{
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	private static final  String STR_LOG = "Queue: %s ; Message: %s";
	
	@TimeDetector
	@Override
	public void sendMessage(String queueName, Object inputMessage) {
		
		log.debug("queueName {} - inputMessage {}", queueName, inputMessage);
		
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(inputMessage);
			oos.flush();
			Message message = MessageBuilder
					.withBody(bos.toByteArray())
					.setContentType(MessageProperties.CONTENT_TYPE_JSON)
					.build();
			log.info(String.format(STR_LOG, queueName, inputMessage));
			this.rabbitTemplate.convertAndSend(queueName, message);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@TimeDetector
	@Override
	public void sendMessageObject(String queueName, Object inputObject) {
		try {
			ObjectMapper mapper = new  ObjectMapper();
			String jsonInString = mapper.writeValueAsString(inputObject); 
			Message message = MessageBuilder
					.withBody(jsonInString.getBytes())
					.setContentType(MessageProperties.CONTENT_TYPE_JSON)
					.build();
			log.info(String.format(STR_LOG, queueName, message.toString()));
			this.rabbitTemplate.convertAndSend(queueName, message);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@TimeDetector
	@Override
	@Async
	public void sendMessage(String queueName, String messageString) {
		try {
			Message message = MessageBuilder
					.withBody(messageString.getBytes())
					.setContentType(MessageProperties.CONTENT_TYPE_JSON)
					.build();
			log.info(String.format(STR_LOG, queueName, message.toString()));
			this.rabbitTemplate.convertAndSend(queueName, message);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
