package com.eme.fastintegrationpay.unionpay;

import android.app.Activity;
import android.content.Intent;

import com.eme.fastintegrationpay.FastPayCallBack.FastPayCallBack;
import com.eme.fastintegrationpay.base.FastPayMethod;
import com.eme.fastintegrationpay.base.UnionAuxiliraryMeansActivity;
import com.unionpay.UPPayAssistEx;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by eme on 2017/3/13.
 */

public class UnionPay implements FastPayMethod<UnionPayInfoImpli> {
    public static final String EXTRA_UNIONPAYINFO  = "unionpay_info";
    private static final String R_SUCCESS  = "success";
    private static final String R_FAILD  = "fail";
    private static final String R_CANCEL  = "cancel";
    public static FastPayCallBack mCallback;
    @Override
    public void fastPay(Activity activity, UnionPayInfoImpli fastPayInfo, FastPayCallBack callBack) {
        mCallback = callBack;
        Intent intent = new Intent(activity, UnionAuxiliraryMeansActivity.class);
        intent.putExtra(EXTRA_UNIONPAYINFO,fastPayInfo);
        activity.startActivity(intent);
    }

    public static void fastPay(Activity activity,UnionPayInfoImpli payInfoImpli) {
        UPPayAssistEx.startPay(activity,null,null,payInfoImpli.getTn(),payInfoImpli.getMode().getmMode());
    }

    public static void handleResult(Activity activity,Intent data) {

        //处理银联手机支付控件返回的支付结果
        if (data == null) {
            activity.finish();
            return;
        }
        /**
         * 支付控件返回字符串：success、fail、cancel分别代表支付成功，支付失败，支付取消
         */

        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase(R_SUCCESS)) {
            if (data.hasExtra("result_data")) {
                String result = data.getExtras().getString("result_data");
                try {
                   JSONObject resultJson = new JSONObject(result);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");

                    //验签证书同后台延签证书
                    // 此处的verify建议送去商户后台做验签
                    boolean ret = verify(dataOrg,sign,"mode");
                    if (ret) {
                        //验证通过后，显示支付结果
                        if (mCallback != null) {
                            mCallback.success();
                        }
                    }else {
                        //验证不通过
                        //建议通过商户后台查询支付结果
                        if (mCallback != null) {
                            mCallback.faild();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {
                //未收到签名信息
                //建议通过商户后台查询支付结果
                if (mCallback != null) {
                    mCallback.success();
                }
            }
        }else if (str.equalsIgnoreCase(R_FAILD)){
            if (mCallback != null) {
                mCallback.faild();
            }
        }else if (str.equalsIgnoreCase(R_CANCEL)) {
            if (mCallback != null) {
                mCallback.cancel();
            }
        }
        activity.finish();
    }

    private static boolean verify(String msg,String sign64,String mode) {
        return true;
    }

}
