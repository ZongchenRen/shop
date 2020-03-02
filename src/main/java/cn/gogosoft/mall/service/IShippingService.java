package cn.gogosoft.mall.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.form.ShippingForm;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-02 13:22
 * @description 地址接口
 */
public interface IShippingService {
	/**
	 * 添加地址
	 * 
	 * @param uid
	 * @param form
	 */
	ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

	/**
	 * 删除地址
	 *
	 * @param uid
	 * @param shippingId
	 */
	ResponseVo delete(Integer uid, Integer shippingId);

	/**
	 * 修改地址
	 *
	 * @param uid
	 * @param shippingId
	 * @param form
	 */
	ResponseVo update(Integer uid, Integer shippingId, ShippingForm form);

	/**
	 * 地址列表
	 *
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 */
	ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);
}
