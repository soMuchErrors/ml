package com.rainbow.machinelearning.configuration;

import com.rainbow.machinelearning.util.Constant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ${xiami}
 * @version $Id: RabbitmqConfig.java, v 0.1 2019年05月09日 15:43 Exp $
 */
//@Configuration
public class RabbitmqConfig {

    /**
     * 红包队列名
     */
    public static final String RED_PACKET_QUEUE = "red.packet.queue";
    /**
     * 死信队列：
     */
    public final static String deadQueueName = "dead_queue";
    /**
     * 声明队列，此队列用来接收用户注册的消息
     *
     * @return
     */
    @Bean
    public Queue redPacketQueue() {
        Queue queue = new Queue(RED_PACKET_QUEUE);
        return queue;
    }
    /**
     * 死信队列：
     */

    @Bean
    public Queue deadQueue() {
        Queue queue = new Queue(deadQueueName, true);
        return queue;
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(Constant.UserCenterMq.deadExchangeName);
    }

    @Bean
    public Binding bindingDeadExchange() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(Constant.UserCenterMq.deadRoutingKey);
    }

    @Bean
    public Binding bindingRedPacketDeadExchange() {
        return BindingBuilder.bind(redPacketQueue()).to(deadExchange()).with(Constant.UserCenterMq.deadRoutingKey);
    }

    @Bean
    public TopicExchange userTopicExchange() {
        return new TopicExchange(Constant.UserCenterMq.MQ_EXCHANGE_USER);
    }

    /**
     * 将红包队列和用户的exchange做个绑定
     *
     * @return
     */
    @Bean
    public Binding bindingRedPacket() {
        Binding binding = BindingBuilder.bind(redPacketQueue()).to(userTopicExchange())
                .with(Constant.UserCenterMq.ROUTING_KEY_POST_REDPACKET);
        return binding;
    }
}