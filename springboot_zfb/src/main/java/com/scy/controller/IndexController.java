package com.scy.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.scy.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2020/7/31.
 */
@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping("/pay")
    public String Pay(String out_trade_no, String subject, String total_amount, String body, String timeout_express, String product_code) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
//        1、封装RSA2签名方式
        //参数1. 请求网关
        //参数2.收款人Id
        //参数3.支付宝私钥
        //参数4.返回格式
        //参数5.字符编码格式
        //参数6.支付宝公钥
        //参数7.加密方式
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.URL,alipayConfig.APPID,alipayConfig.RSA_PRIVATE_KEY,
                alipayConfig.FORMAT,alipayConfig.CHARSET,alipayConfig.ALIPAY_PUBLIC_KEY,alipayConfig.SIGNTYPE);

//       2、创建request请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

//       3、封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);//商品ID
        model.setSubject(subject);//商品名称
        model.setTotalAmount(total_amount);//支付金额
        model.setBody(body);//商品描述
        model.setTimeoutExpress(timeout_express);//请求超时时间
        model.setProductCode(product_code);//商品code

//        设置参数
        request.setBizModel(model);
//        设置异步回调地址
        request.setNotifyUrl(alipayConfig.notif_url);
//        设置同步回调地址
        request.setReturnUrl(alipayConfig.return_url);
//       生成表单
        String form = alipayClient.pageExecute(request).getBody();
        System.out.println(form);

        return form;
    }

}
