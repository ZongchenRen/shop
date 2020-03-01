package cn.gogosoft.mall.service;

import cn.gogosoft.mall.form.CartAddForm;
import cn.gogosoft.mall.form.CartUpdateForm;
import cn.gogosoft.mall.vo.CartVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-01 00:43
 * @description 购物车
 */
public interface ICartService {
	/**
	 * 添加购物车
	 * 
	 * @param cartAddForm
	 * @return
	 */
	ResponseVo<CartVo> add(Integer uid, CartAddForm cartAddForm);

	/**
	 *
	 * 购物车列表
	 * 
	 * @param uid
	 * @return
	 */
	ResponseVo<CartVo> list(Integer uid);

	/**
	 * 更新购物车
	 * 
	 * @param uid
	 * @param productId
	 * @param form
	 * @return
	 */
	ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm form);

	/**
	 * 移除购物车中的商品
	 * 
	 * @param uid
	 * @param productId
	 * @return
	 */
	ResponseVo<CartVo> delete(Integer uid, Integer productId);

	/**
	 * 全选中
	 *
	 * @param uid
	 * @return
	 */
	ResponseVo<CartVo> selectAll(Integer uid);

	/**
	 * 全不选中
	 *
	 * @param uid
	 * @return
	 */
	ResponseVo<CartVo> unSelectAll(Integer uid);

	/**
	 * 购物车数量总和
	 *
	 * @param uid
	 * @return
	 */
	ResponseVo<Integer> sum(Integer uid);
}
