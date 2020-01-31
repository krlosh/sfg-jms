package guru.springframework.sfgjms.sender;

import guru.springframework.sfgjms.config.JmsConfiguration;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000L)
    public void sendMessage() {
        System.out.println("I'm sending a Message!!");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!!")
                .build();

        jmsTemplate.convertAndSend(JmsConfiguration.MY_QUEUE, message);
        System.out.println("Message Sent!!");
    }
}
