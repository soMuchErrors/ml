package com.rainbow.machinelearning.util;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, String> APP_CLASS = new HashMap<>();
    {
        APP_CLASS.put("购物优惠","1");
        APP_CLASS.put("地图旅游","2");
        APP_CLASS.put("教育学习","3");
        APP_CLASS.put("金融理财","4");
        APP_CLASS.put("游戏娱乐","5");
    }
}
