package com.eme.fastintegrationpay.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.eme.fastintegrationpay.wxpay.WXPay;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by eme on 2017/3/13.
 */

public abstract class WXPayEntryBaseActivity extends Activity implements IWXAPIEventHandler{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXPay.getInstance(this,getWXAppId()).getWXApi().handleIntent(getIntent(),this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){
            WXPay.getInstance(this,getWXAppId()).onResp(baseResp.errCode);
            finish();
        }

    }
    public abstract String getWXAppId();
}
