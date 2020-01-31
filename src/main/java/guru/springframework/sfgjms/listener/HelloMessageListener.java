package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfiguration;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfiguration.MY_QUEUE)
    public void listener(@Payload HelloWorldMessage helloWorldMessage,
                         @Headers MessageHeaders headers,
                         Message message){
//        System.out.println("I got a message!!!");
//
//        System.out.println(helloWorldMessage);

    }

    @JmsListener(destination = JmsConfiguration.MY_SEND_RCV_QUEUE)
    public void listeningForHello(@Payload HelloWorldMessage helloWorldMessage,
                         @Headers MessageHeaders headers,
                         Message message) throws JMSException {
//        System.out.println("I got a message!!!");

        HelloWorldMessage m = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), m);
//        System.out.println(helloWorldMessage);

    }
}
