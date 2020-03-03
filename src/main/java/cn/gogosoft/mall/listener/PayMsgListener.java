package cn.gogosoft.mall.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cn.gogosoft.mall.pojo.PayInfo;
import cn.gogosoft.mall.service.IOrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-03-03 17:28
 * @description 支付消息 接收; 关于PayInfo，正确姿势：pay项目提供client.jar，mall项目引用jar包
 */
@Component
@RabbitListener(queues = { "payNotify" })
@Slf4j
public class PayMsgListener {
	@Autowired
	private IOrderService orderService;

	@RabbitHandler
	public void process(String msg) {
		log.info("【接收到的消息】 => {}", msg);
		PayInfo payInfo = new Gson().fromJson(msg, PayInfo.class);
		if (payInfo.getPlatformStatus().equals("SUCCESS")) {
			// 修改订单里的状态
			orderService.paid(payInfo.getOrderNo());
		}
	}
}
