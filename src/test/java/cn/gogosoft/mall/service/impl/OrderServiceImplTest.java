package cn.gogosoft.mall.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.vo.OrderVo;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-03-02 21:07
 * @description 订单
 */
@Slf4j
public class OrderServiceImplTest extends MallApplicationTests {

	@Autowired
	private OrderServiceImpl orderService;
	private Integer uid = 1;
	private Integer shippingId = 4;

	@Test
	public void create() {
		ResponseVo<OrderVo> responseVo = orderService.create(uid, shippingId);
		// Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}
}