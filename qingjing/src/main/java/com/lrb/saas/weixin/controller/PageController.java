package com.lrb.saas.weixin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lrb.saas.core.APP;
import com.lrb.saas.core.message.invoker.SAASHttpInvoker;
import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.core.message.request.query.filter.OperatorFilter;
import com.lrb.saas.core.message.response.SAASQueryResponse;
import com.lrb.saas.core.message.response.SAASResponse;
import com.lrb.saas.core.model.qingjing.mobile.IndexScrollPicture;
import com.lrb.saas.core.model.qingjing.order.Address;
import com.lrb.saas.core.model.qingjing.order.OrderList;
import com.lrb.saas.core.model.qingjing.production.Production;
import com.lrb.saas.framework.service.BaseService;
import com.lrb.saas.weixin.OpenidSession;
import com.lrb.saas.weixin.interceptor.NeedOpenid;

@Controller
public class PageController {

	@Resource(name = "baseHibernateServiceImpl")
	BaseService<Address> addressService;

	@Resource
	private BaseService<OrderList> orderListServer;

	@RequestMapping({ "index" })
	public ModelAndView index(HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView("index");
		SAASQueryResponse<IndexScrollPicture> indexScrollPictureResponse = SAASHttpInvoker.query(APP.domain + "/indexScrollPicture/query", new SAASQueryRequest());
		SAASQueryResponse<Production> productionResponse = SAASHttpInvoker.query(APP.domain + "/production/query", new SAASQueryRequest());
		// Collections.sort(productionResponse.getBody());
		modelAndView.addObject("indexScrollPictures", indexScrollPictureResponse.getBody());
		modelAndView.addObject("productions", productionResponse.getBody());
		return modelAndView;
	}

	@RequestMapping({ "weixin", "" })
	@NeedOpenid
	public ModelAndView weixinIndex(@RequestAttribute String openid, HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView("index");
		SAASQueryResponse<IndexScrollPicture> indexScrollPictureResponse = SAASHttpInvoker.query(APP.domain + "/indexScrollPicture/query", new SAASQueryRequest());
		SAASQueryResponse<Production> productionResponse = SAASHttpInvoker.query(APP.domain + "/production/query", new SAASQueryRequest());
		// Collections.sort(productionResponse.getBody());
		modelAndView.addObject("indexScrollPictures", indexScrollPictureResponse.getBody());
		modelAndView.addObject("productions", productionResponse.getBody());
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}

	@RequestMapping("creatAdr")
	@NeedOpenid
	public ModelAndView creatAdr(@RequestAttribute String openid) {
		ModelAndView modelAndView = new ModelAndView();
		SAASQueryRequest queryRequest = new SAASQueryRequest();
		queryRequest.setClazz(Address.class);
		queryRequest.getFilters().add(new OperatorFilter("openid", "$eq", openid));
		List<Address> addresses = addressService.query(queryRequest);
		modelAndView.addObject("addresses", addresses);
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}

	@RequestMapping("creatAdr2")
	public ModelAndView creatAdr2() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("coupon")
	public ModelAndView coupon() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("model")
	public ModelAndView model() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("orderCenter")
	@NeedOpenid
	public ModelAndView orderCenter(@RequestAttribute String openid) {
		ModelAndView modelAndView = new ModelAndView("orderCenter");
		SAASQueryRequest queryRequest = new SAASQueryRequest();
		queryRequest.setClazz(OrderList.class);
		queryRequest.getFilters().add(new OperatorFilter("openid", "$eq", openid));
		List<OrderList> orderLists = orderListServer.query(queryRequest);
		modelAndView.addObject("orderLists", orderLists);
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}

	// @RequestMapping("payMent")
	// public ModelAndView payMent(String addressid, @SessionAttribute(required
	// = false) OrderList orderList) {
	// ModelAndView modelAndView = new ModelAndView("payMent");
	// if (StringUtils.isNotEmpty(addressid) && orderList != null) {
	// Address address = addressService.getById(Address.class, addressid);
	// if (address != null) {
	// orderList.setPhoneNumber(address.getPhoneNumber());
	// orderList.setName(address.getName());
	// orderList.setLocation(address.getLocation() + " " +
	// address.getLocation2());
	// }
	// }
	// modelAndView.addObject("openid", openid);return modelAndView;
	// }

	@RequestMapping("proDetail")
	@NeedOpenid
	public ModelAndView proDetail(String openid,String id) {
		ModelAndView modelAndView = new ModelAndView();
		SAASResponse<Class<Production>> productionResponse = SAASHttpInvoker.get(APP.domain + "/production", id, Production.class);
		modelAndView.addObject("production", productionResponse.getBody());
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}

	@RequestMapping("QRCode")
	public ModelAndView QRCode() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("shoppingCar")
	@NeedOpenid
	public ModelAndView shoppingCar(@RequestAttribute String openid) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderList", orderList);
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}

}
