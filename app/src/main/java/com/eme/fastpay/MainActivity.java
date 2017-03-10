package com.eme.fastpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eme.fastintegrationpay.FastPay;
import com.eme.fastintegrationpay.FastPayCallBack.FastPayCallBack;
import com.eme.fastintegrationpay.alipay.AliPay;
import com.eme.fastintegrationpay.alipay.AliPayInfoImpli;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAliPay= (Button) findViewById(R.id.btn_alipay);
        btnAliPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPay();
            }
        });

    }

    private void testPay(){
        //此号为支付宝沙箱环境测试账号
        String APPID = "2016080400164054";
        String RSA2_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCXWRrOVpLrn1BKk+22TXOSEjJCoqDM+vjF/NW0JPp0LQydCHZUGcMac894QVwVerraM0mJl3DxA+rE5Shzn+0OHotszYNTzu29ngCPYufRMIZ1/1q7L5XOAExM6bWgZjv/Jo0DFRmIlDjFVR6LK33IUeC6DE3hHi3b9ceALTxzY8gNV7tltMcqxS3SgqvHo4pBMyRjirkXaG1QqcHUYMab3Xp6+EiN+cR3T4qBsZss0NMpOxLHoFX/geJWFAmniWjUqXhafzSRQhbdSGQms1Ybhqgbrkv5rFaWFtYnEz8DGNsEYZXjRQsfLEH3GjJGLZf72szffDDDZYeGhtKXoZ8RAgMBAAECggEAP86zPHbHbks2YJrJR2iT5uuBARLrVEAjDeWwe0hzxvMPNE1TBdXuqYJV388+cVfS2xdwU2/jj9kk62npigXMvX0nLvHeAkFlfeHVPp5mOY2JwsmriOtg7v61LGAD0OIJPWMVZE1K/Ifw/1EJlYb6eq/6XLW/R7+fefK+700jBUpOAtvAsuMjPzrL/oegAej93XLbgaYX0e84RhWdUcNCF42ok0H12gUitJ6jUwtvMyFo3p0Ib/2IQsDoRssav8EqiNkFYJj9dVfhAdl9LVJcL3pkuB78gI4/pic2S0n80ptfBwCB8+W5JWWEc47L6qaJyOLHTMh3wpAc44u95HxuWQKBgQDmyokQTBwcQ5JAQ5q8QOpGh4J7JnovScMfrjcwVsIoGyJm3y9bARPnU/EayqcEmJ/rE7iCu0t/WNfnk0uTuT4FD6aMDPS5eP1TWLGWGt6LgDP2JKORcBHjXBEJsaLyGhsRbWjxwyj8jl3WdNqdfD4uNESDRBMAnu4Bj0ULa7u0qwKBgQCn4Sc/p1tZQQQp+WLhjvqkfOvue42uJDrAH3ly3YwNh9SOEZQt+z0GY7aRf8tvB4UNz+Q6R3Z4Gvg5ghk4n6uGEc5nwdXhmldFxZu5eEIIpgv/yed1jWnl6KDbwqcrH5Sl+HJJGXXKdJP+k3hh3zRAEpf42tfdvvUg8LW15hvjMwKBgGitgl9m9fUH3OTTBPLPyrK0scTjndWm8h6DaTq+jw+bxw27uwHeykVI+Hl7YyedhiMcbbq18rdO95ZsY4IcKfonsBWjprgdHWFZQCriAwBo5KiG072ahDKIRp4L+OkwWAiehFPrfcKXXA5yL9nwSU+xm0Wica/9ZdxFPD7C3X9DAoGAfAzwvqWA57wWQpnoSjon1WIFyKx9rd9RlmPrj0hMVDc5hl1Q/IU4u770yiD/hySsWd9ziukBjpCHOZ972Y6K5T/FIIBn9hPhR0pU3sERMmjYVwOvx/pd9ShnrNvyQPhOFDI46PEqTBbGwQfhnZiceHgE2juD5XRUmCmmbCoEE2cCgYBLREyDHsW2XKIGTYFDj0JXy55MycMnftFwJFbG/4Vua9Hx7+rmpqtSUbII0NGvAYGfmPdSW29QYAhx9RKzDJRNkn1Z5BKjdyAfXcyGEbhbGKU+JRYpD8aH8SMMZHtV2MGX8sNi+jgVXDZnxGIULSHTdEhzgaf4Lkfhk4JGwO/raA==";
        String RSA_PRIVATE = "";
        boolean rsa2 = (RSA2_PRIVATE.length() >0);
        Map<String,String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE:RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params,privateKey,rsa2);
        final String orderInfo = orderParam + "&"+ sign;


        //-----------------------支付宝支付逻辑------------------------
        AliPay aliPay = new AliPay();
        AliPayInfoImpli payInfoImpli = new AliPayInfoImpli();
        payInfoImpli.setOrderInfo(orderInfo);
        FastPay.pay(aliPay, this, payInfoImpli, new FastPayCallBack() {
            @Override
            public void success() {
                Toast.makeText(getBaseContext(),"支付成功",Toast.LENGTH_SHORT);
            }

            @Override
            public void faild() {
                Toast.makeText(getBaseContext(),"支付失败",Toast.LENGTH_SHORT);
            }

            @Override
            public void cancel() {
                Toast.makeText(getBaseContext(),"支付取消",Toast.LENGTH_SHORT);
            }
        });
    }
}
