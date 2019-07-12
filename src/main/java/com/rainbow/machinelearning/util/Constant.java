package com.rainbow.machinelearning.util;

/**
 * @author ${xiami}
 * @version $Id: Constant.java, v 0.1 2019年05月09日 15:42 Exp $
 */
public class Constant {

    public interface UserCenterMq {

        /**
         * 用户系统exchange名
         */
        String MQ_EXCHANGE_USER = "user.topic.exchange";

        /**
         * 发送红包routing key
         */
        String ROUTING_KEY_POST_REDPACKET = "post.redpacket";
        //String ROUTING_KEY_POST_REDPACKET = "post.#";
        /**
         * 死信队列：
         */
        String deadRoutingKey = "dead_routing_key";
        String deadExchangeName = "dead_exchange";
    }
}
