package com.eme.fastintegrationpay.base;

import android.app.Activity;

import com.eme.fastintegrationpay.FastPayCallBack.FastPayCallBack;

/**
 * Created by eme on 2017/3/10.
 *
 * Describe :统一的支付接口
 */

public interface FastPayMethod<T extends FastPayInfo> {
    void fastPay(Activity activity, T fastPayInfo, FastPayCallBack callBack);
}
