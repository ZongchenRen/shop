package cn.gogosoft.mall.service.impl;

import static cn.gogosoft.mall.enums.ResponseEnum.DELETE_SHIPPING_FAIL;
import static cn.gogosoft.mall.enums.ResponseEnum.ERROR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.dao.ShippingMapper;
import cn.gogosoft.mall.form.ShippingForm;
import cn.gogosoft.mall.pojo.Shipping;
import cn.gogosoft.mall.service.IShippingService;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-02 13:23
 * @description 地址信息服务
 */
@Service
public class ShippingServiceImpl implements IShippingService {
	@Autowired
	private ShippingMapper shippingMapper;

	/**
	 * 添加地址
	 *
	 * @param uid
	 * @param form
	 */
	@Override
	public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form) {
		Shipping shipping = new Shipping();
		BeanUtils.copyProperties(form, shipping);
		shipping.setUserId(uid);
		int row = shippingMapper.insertSelective(shipping);
		// 写入失败
		if (row == 0) {
			return ResponseVo.error(ERROR);
		}
		// 写入成功
		Map<String, Integer> map = new HashMap<>();
		map.put("shippingId", shipping.getId());

		return ResponseVo.success(map);
	}

	/**
	 * 删除地址
	 *
	 * @param uid
	 * @param shippingId
	 */
	@Override
	public ResponseVo delete(Integer uid, Integer shippingId) {
		int row = shippingMapper.deleteByIdAndUid(uid, shippingId);
		if (row == 0) {
			return ResponseVo.error(DELETE_SHIPPING_FAIL);
		}
		return ResponseVo.success();
	}

	/**
	 * 更新地址
	 *
	 * @param uid
	 * @param shippingId
	 * @param form
	 */
	@Override
	public ResponseVo update(Integer uid, Integer shippingId, ShippingForm form) {
		Shipping shipping = new Shipping();
		BeanUtils.copyProperties(form, shipping);
		shipping.setUserId(uid);
		shipping.setId(shippingId);
		int row = shippingMapper.updateByPrimaryKey(shipping);
		if (row == 0) {
			return ResponseVo.error(ERROR);
		}
		return ResponseVo.success();
	}

	/**
	 * 地址列表
	 *
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 */
	@Override
	public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Shipping> shippings = shippingMapper.selectByUid(uid);
		PageInfo pageInfo = new PageInfo(shippings);

		return ResponseVo.success(pageInfo);
	}
}
