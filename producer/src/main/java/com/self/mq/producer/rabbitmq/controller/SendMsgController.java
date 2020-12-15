package com.self.mq.producer.rabbitmq.controller;

import com.self.mq.producer.rabbitmq.service.SendMsgService;
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
    private SendMsgService sendMsgService;

    @ApiOperation(value = "发送direct消息", produces = "application/json")
    @PostMapping("sendDirectMsg")
    public String sendDirectMsg(@RequestParam String key, @RequestParam String msg){
        return sendMsgService.sendDirectMsg(key, msg);
    }

    @ApiOperation(value = "发送topic消息", produces = "application/json")
    @PostMapping("sendTopicMsg")
    public String sendTopicMsg(@RequestParam String key, @RequestParam String msg){
        return sendMsgService.sendTopicMsg(key, msg);
    }

    @ApiOperation(value = "发送topic消息", produces = "application/json")
    @PostMapping("sendFanoutMsg")
    public String sendFanoutMsg(@RequestParam String msg){
        return sendMsgService.sendFanoutMsg(msg);
    }
}
