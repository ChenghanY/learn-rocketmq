package com.james.leanrocketmq

import com.alibaba.fastjson.JSON
import com.james.leanrocketmq.domain.User
import groovy.util.logging.Slf4j
import org.apache.rocketmq.client.producer.SendResult
import org.apache.rocketmq.spring.core.RocketMQTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@Slf4j
@SpringBootTest(classes = LearnRocketmqApplication.class)
class EntitySpec extends Specification{
    /**
     * Groovy 语法的特点，注意使用单引号包起来
     */
    @Value('${demo.rocketmq.user.topic}')
    private String userTopic;

    @Value('${demo.rocketmq.tag}')
    private String tag;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    def "发送实体消息" () {
        expect:
        SendResult sendResult = rocketMQTemplate.syncSend(userTopic + ':' + tag, new User().setUserAge((byte) 18));
        log.info("sendResult : {}" ,JSON.toJSONString(sendResult))
    }
}
