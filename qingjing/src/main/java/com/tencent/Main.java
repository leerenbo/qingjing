package com.tencent;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.lrb.saas.core.util.UUIDUtil;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.protocol.pay_protocol.PayReqData;
import com.tencent.protocol.pay_query_protocol.PayQueryReqData;

public class Main {

    public static void main(String[] args) {

        try {

            //--------------------------------------------------------------------
            //温馨提示，第一次使用该SDK时请到com.tencent.common.Configure类里面进行配置
            //--------------------------------------------------------------------
        	Configure.setAppID("wxfa07fba7ff145959");
        	Configure.setCertLocalPath("/Users/lirenbo/qingjing/apiclient_cert.p12");
        	Configure.setCertPassword("1381227902");
        	Configure.setIp("118.198.13.15");
//        	Configure.setKey(key);
        	Configure.setMchID("1381227902");
        	
        	
            //--------------------------------------------------------------------
            //PART One:基础组件测试
            //--------------------------------------------------------------------

            PayReqData scanPayReqData=new PayReqData("轻井株式会社-家用电器类", UUIDUtil.randomUUIDString(), 1, "118.198.83.104", "http://mdat.qinjuu.com/shop/notify", "JSAPI", "ofsU3wgVEfNf_de9tN4PlsZcdDng",DateFormatUtils.format(DateUtils.addMinutes(new Date(), 15), "yyyyMMddHHmmss"));
			PayQueryReqData scanPayQueryReqData=new PayQueryReqData("asdf", "asdf");
			//1）https请求可用性测试
//            HTTPSPostRquestWithCert.test();
			String re=WXPay.requestScanPayService(scanPayReqData);
//        	String re = WXPay.requestScanPayQueryService(scanPayQueryReqData);
        	System.out.println("re:"+re);
            //2）测试项目用到的XStream组件，本项目利用这个组件将Java对象转换成XML数据Post给API
            //XStreamTest.test();


            //--------------------------------------------------------------------
            //PART Two:基础服务测试
            //--------------------------------------------------------------------

            //1）测试被扫支付API
            //PayServiceTest.test();

            //2）测试被扫订单查询API
            //PayQueryServiceTest.test();

            //3）测试撤销API
            //温馨提示，测试支付API成功扣到钱之后，可以通过调用PayQueryServiceTest.test()，将支付成功返回的transaction_id和out_trade_no数据贴进去，完成撤销工作，把钱退回来 ^_^v
            //ReverseServiceTest.test();

            //4）测试退款申请API
            //RefundServiceTest.test();

            //5）测试退款查询API
            //RefundQueryServiceTest.test();

            //6）测试对账单API
            //DownloadBillServiceTest.test();


            //本地通过xml进行API数据模拟的时候，先按需手动修改xml各个节点的值，然后通过以下方法对这个新的xml数据进行签名得到一串合法的签名，最后把这串签名放到这个xml里面的sign字段里，这样进行模拟的时候就可以通过签名验证了
           // Util.log(Signature.getSignFromResponseString(Util.getLocalXMLString("/test/com/tencent/business/refundqueryserviceresponsedata/refundquerysuccess2.xml")));

            //Util.log(new Date().getTime());
            //Util.log(System.currentTimeMillis());

        } catch (Exception e){
        	e.printStackTrace();
            Util.log(e.getMessage());
        }

    }

}
