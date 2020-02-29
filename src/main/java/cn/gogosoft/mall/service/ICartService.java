package cn.gogosoft.mall.service;

import cn.gogosoft.mall.form.CartAddForm;
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
	 * list
	 * 
	 * @param uid
	 * @return
	 */
	ResponseVo<CartVo> list(Integer uid);
}
