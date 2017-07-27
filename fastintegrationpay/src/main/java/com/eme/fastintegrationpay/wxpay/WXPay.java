package com.eme.fastintegrationpay.wxpay;

import android.app.Activity;
import android.text.TextUtils;

import com.eme.fastintegrationpay.FastPayCallBack.FastPayCallBack;
import com.eme.fastintegrationpay.base.FastPayMethod;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.log.ViseLog;

/**
 * Created by eme on 2017/3/13.
 */

public class WXPay implements FastPayMethod<WXPayInfoImpli> {
    private static WXPay mWXPAY;
    private WXPayInfoImpli payInfoImpli;
    private static FastPayCallBack fastPayCallBack;
    private IWXAPI mWXApi;


    private WXPay(Activity mActivity,String wxAppId) {
        mWXApi = WXAPIFactory.createWXAPI(mActivity.getApplicationContext(),null);
        mWXApi.registerApp(wxAppId);
    }

    public static WXPay getInstance(Activity mActivity,String wxAppId) {
        if (mWXPAY == null) {
            synchronized (WXPay.class) {
                if (mWXPAY == null) {
                    mWXPAY = new WXPay(mActivity,wxAppId);
                }
            }
        }
        return mWXPAY;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }
    @Override
    public void fastPay(Activity activity, WXPayInfoImpli fastPayInfo, FastPayCallBack callBack) {
        this.payInfoImpli = fastPayInfo;
        fastPayCallBack = callBack;
        if (!isSupportWXPay()) {
            if (callBack != null) {
                callBack.faild();
            }
            return;
        }

        if (payInfoImpli == null || TextUtils.isEmpty(payInfoImpli.getAppid()) ||
                TextUtils.isEmpty(payInfoImpli.getPartnerid())
                || TextUtils.isEmpty(payInfoImpli.getPrepayId())
                || TextUtils.isEmpty(payInfoImpli.getPackageValue())
                || TextUtils.isEmpty(payInfoImpli.getNonceStr())
                || TextUtils.isEmpty(payInfoImpli.getTimestamp())
                || TextUtils.isEmpty(payInfoImpli.getSign())
                ){
            if (callBack != null) {
                callBack.faild();
            }
            return;
        }

        PayReq req = new PayReq();
        req.appId = payInfoImpli.getAppid();
        req.partnerId = payInfoImpli.getPartnerid();
        req.prepayId = payInfoImpli.getPrepayId();
        req.packageValue = payInfoImpli.getPackageValue();
        req.nonceStr = payInfoImpli.getNonceStr();
        req.timeStamp = payInfoImpli.getTimestamp();
        req.sign = payInfoImpli.getSign();

        mWXApi.sendReq(req);
    }

    //支付回调响应
    public void onResp(int error_code) {
        ViseLog.d("error_code:"+error_code);
        if (fastPayCallBack == null) {
            return;
        }

        if (error_code == 0) {
            //成功
            fastPayCallBack.success();
        }else if (error_code == -1) {
            //错误
            fastPayCallBack.faild();
        }else if (error_code == -2) {
            //取消
            fastPayCallBack.cancel();
        }
        fastPayCallBack = null;
    }

    public String LogExplanation(int error_code) {
       String message = "";
        switch (error_code) {
            case 0:
                message = "error_code:0\n  Success!!! ";
                break;
            case -1:
                message = "error:-1\n   Failed!!! \n可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。";
                break;
            case -2:
                message = "error_code:-2\n   Cancel";
                break;
        }
        return message;
    }

    //检测是否支持微信支付
    private boolean isSupportWXPay(){
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}
