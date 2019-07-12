package com.rainbow.machinelearning.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rainbow.machinelearning.configuration.RabbitmqConfig;
import com.rainbow.machinelearning.model.RedPacket;
import com.rainbow.machinelearning.model.User;
import com.rainbow.machinelearning.service.RedPacketService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ${xiami}
 * @version $Id: PostRedPacketConsumer.java, v 0.1 2019年05月09日 16:23 Exp $
 */
@Component
public class PostRedPacketConsumer {
    @Autowired
    private RedPacketService redPacketService;

    @RabbitListener(queues = RabbitmqConfig.RED_PACKET_QUEUE)
    @RabbitHandler
    public void postRedPacket(String userStr, Channel channel, Message message) throws IOException {
        try {
            System.out.println("consumer");
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉；否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            User user = JSONObject.parseObject(userStr,User.class);
            RedPacket redPacket = new RedPacket();
            redPacket.setUserId(user.getId());
            redPacket.setRedPacketAmount((double)(Math.random() * 10));
            redPacketService.add(redPacket);
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("receiver fail");
        }
    }
}
