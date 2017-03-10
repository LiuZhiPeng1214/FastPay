package com.eme.fastintegrationpay.alipay;

import com.eme.fastintegrationpay.base.FastPayInfo;

/**
 * Created by eme on 2017/3/10.
 *
 * Describe :包含支付宝的支付类型和支付信息
 */

public class AliPayInfoImpli implements FastPayInfo {
    public String orderInfo;
    public String authInfo;

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
