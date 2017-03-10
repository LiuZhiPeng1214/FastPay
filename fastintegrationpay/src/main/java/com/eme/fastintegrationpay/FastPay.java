package com.eme.fastintegrationpay;

import android.app.Activity;

import com.eme.fastintegrationpay.FastPayCallBack.FastPayCallBack;
import com.eme.fastintegrationpay.base.FastPayInfo;
import com.eme.fastintegrationpay.base.FastPayMethod;

/**
 * Created by eme on 2017/3/10.
 */

public class FastPay {
    public static <T extends FastPayInfo> void pay(FastPayMethod<T> payMethod, Activity mActivity, T payInfo, FastPayCallBack callBack) {
        payMethod.fastPay(mActivity,payInfo,callBack);
    }
}
