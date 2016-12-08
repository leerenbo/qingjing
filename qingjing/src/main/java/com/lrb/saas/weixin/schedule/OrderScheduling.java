package com.lrb.saas.weixin.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.core.message.request.query.filter.OperatorFilter;
import com.lrb.saas.core.model.qingjing.order.OrderList;
import com.lrb.saas.framework.service.BaseService;
import com.tencent.WXPay;
import com.tencent.common.Util;
import com.tencent.protocol.pay_query_protocol.PayQueryReqData;
import com.tencent.protocol.pay_query_protocol.PayQueryResData;

@Component
public class OrderScheduling {

	@Resource
	private BaseService<OrderList> orderListService;

	@Scheduled(cron="0 * * * * ?")
	public void queryOrder() {
		SAASQueryRequest queryRequest = new SAASQueryRequest();
		queryRequest.setClazz(OrderList.class);
		List<OperatorFilter> filters = new ArrayList<>();
		filters.add(new OperatorFilter("status", "$eq", "待支付"));
		queryRequest.setFilters(filters);
		List<OrderList> orderLists = orderListService.query(queryRequest);
		orderLists.stream().forEach(new Consumer<OrderList>() {

			@Override
			public void accept(OrderList orderList) {
				PayQueryReqData payQueryReqData = new PayQueryReqData(null, orderList.getId());
				try {
					String payQueryServiceResponseString=WXPay.requestPayQueryService(payQueryReqData);
					PayQueryResData payQueryResData = (PayQueryResData) Util.getObjectFromXML(payQueryServiceResponseString, PayQueryResData.class);
					if ("SUCCESS".equals(payQueryResData.getReturn_code())) {
						if ("SUCCESS".equals(payQueryResData.getResult_code())) {
							switch (payQueryResData.getTrade_state()) {
							case "SUCCESS":
								orderList.setStatus("支付成功");
								break;
							case "REFUND":
								orderList.setStatus("已退款");
								break;
//							case "NOTPAY":
//								orderList.setStatus("未支付");
//								break;
							case "CLOSED":
								orderList.setStatus("已关闭");
								break;
							case "PAYERROR":
								orderList.setStatus("支付失败");
								break;
							default:
								break;
							}
							orderListService.merge(orderList);
						}else{
							orderList.setStatus("订单失败");
							orderListService.merge(orderList);
						}
					}else{
						orderList.setStatus("订单失败");
						orderListService.merge(orderList);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
