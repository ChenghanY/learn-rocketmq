package com.james.leanrocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.james.leanrocketmq.domain.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "${demo.rocketmq.user.topic}",
        consumerGroup = "${demo.rocketmq.userGroup}",
        selectorExpression = "${demo.rocketmq.tag}"
)
public class UserConsumer implements RocketMQListener<User> {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);
    @Override
    public void onMessage(User user) {
        LOGGER.info("user : {}", JSON.toJSONString(user));
    }
}