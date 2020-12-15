package com.self.mq.rabbitmqproducer.controller;

import com.self.mq.rabbitmqproducer.service.SendMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试消息发送", description = "rabbitmq send")
@RestController
@RequestMapping("rabbitmq/send")
public class SendMsgController {

    @Autowired
    private SendMsgService testSendMsgService;

    @ApiOperation(value = "发送direct消息", produces = "application/json")
    @PostMapping("sendDirectMsg")
    public String sendDirectMsg(@RequestParam String key, @RequestParam String msg){
        return testSendMsgService.sendDirectMsg(key, msg);
    }

    @ApiOperation(value = "发送topic消息", produces = "application/json")
    @PostMapping("sendTopicMsg")
    public String sendTopicMsg(@RequestParam String key, @RequestParam String msg){
        return testSendMsgService.sendTopicMsg(key, msg);
    }

    @ApiOperation(value = "发送topic消息", produces = "application/json")
    @PostMapping("sendFanoutMsg")
    public String sendFanoutMsg(@RequestParam String msg){
        return testSendMsgService.sendFanoutMsg(msg);
    }
}
