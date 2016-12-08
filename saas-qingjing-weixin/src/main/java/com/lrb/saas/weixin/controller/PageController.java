package com.lrb.saas.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lrb.saas.core.message.invoker.SAASHttpInvoker;
import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.core.message.response.SAASQueryResponse;
import com.lrb.saas.core.model.qingjing.mobile.IndexScrollPicture;

@Controller
@RequestMapping
public class PageController {
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		SAASQueryResponse<IndexScrollPicture> indexScrollPictureResponse = SAASHttpInvoker
				.query("http://localhost/saas-qingjing-shop/indexScrollPicture/query", new SAASQueryRequest());
		modelAndView.addObject("indexScrollPictures", indexScrollPictureResponse.getBody());
		return modelAndView;
	}

	@RequestMapping("creatAdr")
	public ModelAndView creatAdr() {
		ModelAndView modelAndView = new ModelAndView();
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
	public ModelAndView orderCenter() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("payMent")
	public ModelAndView payMent() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("proDetail")
	public ModelAndView proDetail() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("QRCode")
	public ModelAndView QRCode() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping("shoppingCar")
	public ModelAndView shoppingCar() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

}
