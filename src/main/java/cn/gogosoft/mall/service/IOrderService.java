package cn.gogosoft.mall.service;

import cn.gogosoft.mall.vo.OrderVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-02 20:19
 * @description 订单
 */
public interface IOrderService {
	ResponseVo<OrderVo> create(Integer uid, Integer shippingId);
}
