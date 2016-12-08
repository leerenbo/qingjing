package com.lrb.saas.weixin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.core.message.request.query.filter.OperatorFilter;
import com.lrb.saas.core.model.qingjing.order.Address;
import com.lrb.saas.core.model.qingjing.order.OrderList;
import com.lrb.saas.core.model.qingjing.production.ProductionType;
import com.lrb.saas.core.util.UUIDUtil;
import com.lrb.saas.framework.service.BaseService;
import com.lrb.saas.weixin.OpenidSession;
import com.lrb.saas.weixin.interceptor.NeedOpenid;

@Controller
@RequestMapping("shop")
public class ShopController {

	@Resource(name = "baseHibernateServiceImpl")
	BaseService<ProductionType> productionTypeService;
	@Resource(name = "baseHibernateServiceImpl")
	BaseService<Address> addressService;

	
	@RequestMapping("showShoppingCartCount")
	@ResponseBody
	@NeedOpenid
	public Integer showShoppingCartCount(@RequestAttribute String openid, HttpServletRequest httpServletRequest) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		if (orderList == null) {
			orderList = new OrderList();
			orderList.setId(UUIDUtil.randomUUIDString());
			OpenidSession.put(openid, "orderList", orderList);
		}
		return orderList.getCount();
	}

	
	@RequestMapping("showShoppingCart")
	@ResponseBody
	@NeedOpenid
	public OrderList showShoppingCart(@RequestAttribute String openid, HttpServletRequest httpServletRequest) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		if (orderList == null) {
			orderList = new OrderList();
			orderList.setId(UUIDUtil.randomUUIDString());
			OpenidSession.put(openid, "orderList", orderList);
		}
		return orderList;
	}

	@RequestMapping("addShoppingCart")
	@ResponseBody
	@NeedOpenid
	public OrderList addShoppingCart(@RequestAttribute String openid, String productionTypeId, HttpServletRequest httpServletRequest) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		if (orderList == null) {
			orderList = new OrderList();
			orderList.setId(UUIDUtil.randomUUIDString());
			OpenidSession.put(openid, "orderList", orderList);
		}
		ProductionType productionType = productionTypeService.getById(ProductionType.class, productionTypeId);
		orderList.add(productionType);
		return orderList;
	}

	@RequestMapping("reduceShoppingCart")
	@ResponseBody
	@NeedOpenid
	public OrderList reduceShoppingCart(@RequestAttribute String openid, String productionTypeId, HttpServletRequest httpServletRequest) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		ProductionType productionType = productionTypeService.getById(ProductionType.class, productionTypeId);
		orderList.reduce(productionType);
		return orderList;
	}

	@RequestMapping("delShoppingCart")
	@ResponseBody
	@NeedOpenid
	public OrderList delShoppingCart(@RequestAttribute String openid, String productionTypeId, HttpServletRequest httpServletRequest) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		ProductionType productionType = productionTypeService.getById(ProductionType.class, productionTypeId);
		orderList.del(productionType);
		return orderList;
	}

	@RequestMapping("payMent")
	@NeedOpenid
	public ModelAndView payMent(String addressid, @RequestAttribute String openid, String productionTypeId, HttpServletRequest httpServletRequest) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		ModelAndView modelAndView = new ModelAndView("payMent");
		if (StringUtils.isNotBlank(productionTypeId) && (orderList == null || orderList.getCount() == 0)) {
			addShoppingCart(openid, productionTypeId, httpServletRequest);
			orderList = (OrderList) OpenidSession.get(openid, "orderList");
		}
		SAASQueryRequest queryRequest = new SAASQueryRequest();
		queryRequest.setClazz(Address.class);
		OperatorFilter filter = new OperatorFilter("openid", "$eq", openid);
		queryRequest.getFilters().add(filter);

		if (StringUtils.isNotEmpty(addressid) && orderList != null) {
			Address address = addressService.getById(Address.class, addressid);
			if (address != null) {
				orderList.setPhoneNumber(address.getPhoneNumber());
				orderList.setName(address.getName());
				orderList.setLocation(address.getLocation() + " " + address.getLocation2());
			}
		}
		modelAndView.addObject("orderList", orderList);
		modelAndView.addObject("openid", openid);return modelAndView;
	}

	@RequestMapping("addAddress")
	@NeedOpenid
	public ModelAndView addAddress(@RequestAttribute String openid, Address address) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		ModelAndView modelAndView = new ModelAndView("redirect:/shop/payMent");
		orderList.setName(address.getName());
		orderList.setLocation(address.getLocation() + " " + address.getLocation2());
		orderList.setPhoneNumber(address.getPhoneNumber());
		address.setOpenid(openid);
		addressService.save(address);
		modelAndView.addObject("openid", openid);return modelAndView;

	}

}
