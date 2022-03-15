package ${package}.amqp.listener;

import org.springframework.amqp.core.Message;

public interface IListenerAmqp {
	
	void serviceListenerAmqp(final Message receive);
}
