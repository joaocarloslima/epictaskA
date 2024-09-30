package br.com.fiap.epictaska.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public Queue queue(){
        return new Queue("email-queue", true);
    }
}
