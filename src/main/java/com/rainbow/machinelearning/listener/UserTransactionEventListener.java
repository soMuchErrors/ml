package com.rainbow.machinelearning.listener;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.machinelearning.model.User;
import com.rainbow.machinelearning.send.MessageSender;
import com.rainbow.machinelearning.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author ${xiami}
 * @version $Id: UserTransactionListener.java, v 0.1 2019年05月09日 14:55 Exp $
 */
@Component
public class UserTransactionEventListener {

    @Autowired
    private MessageSender messageSender;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(PayloadApplicationEvent<User> event) {
        System.out.println("before commit, id: " + event.getPayload().getId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(PayloadApplicationEvent<User> event) {
        System.out.println("after commit, id: " + event.getPayload().getId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void afterCompletion(PayloadApplicationEvent<User> event) {
        System.out.println("after completion, id: " + event.getPayload().getId());
        messageSender.send(Constant.UserCenterMq.MQ_EXCHANGE_USER,
                Constant.UserCenterMq.ROUTING_KEY_POST_REDPACKET, JSONObject.toJSONString(event.getPayload()));

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(PayloadApplicationEvent<User> event) {
        System.out.println("after rollback, id: " + event.getPayload().getId());
    }
}