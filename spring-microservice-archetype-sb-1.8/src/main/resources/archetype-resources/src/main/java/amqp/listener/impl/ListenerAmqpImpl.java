package ${package}.amqp.listener.impl;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${package}.amqp.listener.IListenerAmqp;
import ${package}.annotation.TimeDetector;
import ${package}.repository.IRepository;
import ${package}.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ListenerAmqpImpl implements IListenerAmqp{
	
	@Autowired
	IRepository repository;
	
	@TimeDetector
	@RabbitListener(id = "${rabbitmq.in.queue}", queues = "${rabbitmq.in.queue}")
	public void serviceListenerAmqp(Message message) {
		log.info("Component::Impl::serviceListenerAmqp");
		
		try {
			String stringMessage = new String(message.getBody());
						
			Map<String, Object> map = Utils.convertJsonToHashMap(stringMessage);
			
			Integer rut = (Integer) map.get("rut");
			String dv = (String) map.get("dv");
			String name = (String) map.get("name");
			String messageMap = (String) map.get("message");
				
			repository.repositoryInsert(name, rut, dv, messageMap);
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
}
