package com.eme.fastintegrationpay.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.eme.fastintegrationpay.unionpay.UnionPay;
import com.eme.fastintegrationpay.unionpay.UnionPayInfoImpli;

/**
 * Created by eme on 2017/3/13.
 * 银联辅助类，因为回调必须在onActivityForResult中获取，为了使用方便，所以拉起一个透明的activity来处理
 */

public class UnionAuxiliraryMeansActivity extends AppCompatActivity {
    UnionPayInfoImpli unionPayInfoImpli;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        unionPayInfoImpli = getIntent().getParcelableExtra(UnionPay.EXTRA_UNIONPAYINFO);
        if (unionPayInfoImpli == null) {
            finish();
        }else {
            UnionPay.fastPay(this,unionPayInfoImpli);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UnionPay.handleResult(this,data);
    }
}
