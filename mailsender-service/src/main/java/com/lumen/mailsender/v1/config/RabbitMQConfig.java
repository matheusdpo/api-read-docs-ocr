package com.lumen.mailsender.v1.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey.invoice}")
    private String routingKeyInvoice;

    @Value("${rabbitmq.routingkey.reminder}")
    private String routingKeyReminder;

    @Value("${rabbitmq.queue.invoice}")
    private String invoiceQueueName;

    @Value("${rabbitmq.queue.reminder}")
    private String reminderQueueName;

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue invoiceQueue() {
        return new Queue(invoiceQueueName);
    }

    @Bean
    public Queue reminderQueue() {
        return new Queue(reminderQueueName);
    }

    @Bean
    public Binding invoiceBinding() {
        return BindingBuilder.bind(invoiceQueue())
                .to(emailExchange())
                .with(routingKeyInvoice);
    }

    @Bean
    public Binding reminderBinding() {
        return BindingBuilder.bind(reminderQueue())
                .to(emailExchange())
                .with(routingKeyReminder);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchangeName);
        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(
            RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}