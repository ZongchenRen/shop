package cn.gogosoft.mall.service.impl;

import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.enums.ResponseEnum;
import cn.gogosoft.mall.form.ShippingForm;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-03-02 14:12
 * @description 地址
 */
@Slf4j
public class ShippingServiceImplTest extends MallApplicationTests {
	@Autowired
	private ShippingServiceImpl shippingService;

	private Integer uid = 1;
	private ShippingForm form;
	private Integer shippingId;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Before
	public void before() {
		ShippingForm form = new ShippingForm();
		form.setReceiverName("李先生");
		form.setReceiverPhone("010-66288888");
		form.setReceiverMobile("1888888888");
		form.setReceiverProvince("北京市");
		form.setReceiverCity("北京市");
		form.setReceiverDistrict("朝阳区");
		form.setReceiverAddress("望京美团总部");
		form.setReceiverZip("100000");
		this.form = form;
		add();
	}

	public void add() {
		ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
		log.info("result={}" + responseVo);
		this.shippingId = responseVo.getData().get("shippingId");
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}

	@After
	public void delete() {
		ResponseVo responseVo = shippingService.delete(uid, this.shippingId);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}

	@Test
	public void update() {
		form.setReceiverName("二大爷");
		ResponseVo responseVo = shippingService.update(uid, this.shippingId, form);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}

	@Test
	public void list() {
		ResponseVo<PageInfo> responseVo = shippingService.list(uid, 1, 3);
		log.info("result={}" + gson.toJson(responseVo));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}
}