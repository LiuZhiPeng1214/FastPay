[![](https://jitpack.io/v/LiuZhiPeng1214/FastPay.svg)](https://jitpack.io/#LiuZhiPeng1214/FastPay)
FastPay
=======
      FastPay高度封装并集成了Android 端的支付宝支付、微信支付和银联支付。使用此库，可以简单高效的实现支付功能，节省了开发者看官方文档的时间
      
      FastPay拥有良好的扩展性，可以由用户自己集成其他支付方式，支付类实现FastMethod接口，支付的信息类实现FastPayInfo接口，在具体传到FastPay中。具体步骤参照示例代码。
      
Usage(使用)
---------------------------
step1
-


<br>在gradle中直接引用<br>>>在你项目根目录的build.gradle中添加<br>
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
在module下的build.gradle中添加<br>
```
dependencies {
		compile 'com.github.LiuZhiPeng1214:FastPay:v1.0.1'
	}
```

下载库后做为module导入

```
compile project(':fastintegrationpay')
```
step2
-
**支付宝支付：<br>**
**配置：无需配置<br>**
**调用方式<br>**
````
	private void testAliPay(){
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
        //-------------------以上数据由后台返回-------------------------

	
        //-----------------------支付宝支付步骤------------------------
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
````
**银联支付：<br>**
**配置：无需配置<br>**
**调用方式<br>**
```
 private void testUnionPay() {
        UnionPay unionPay = new UnionPay();

        //构造银联订单实体。一般都是由服务端直接返回。测试时可以用Mode.TEST,发布时用Mode.RELEASE。
        UnionPayInfoImpli uniPayInfoImpli = new UnionPayInfoImpli();
        uniPayInfoImpli.setTn("814144587819703061900");
        uniPayInfoImpli.setMode(Mode.TEST);
        FastPay.pay(unionPay, this, uniPayInfoImpli, new FastPayCallBack() {
            @Override
            public void success() {

            }

            @Override
            public void faild() {

            }

            @Override
            public void cancel() {

            }
        });
    }

```
**微信支付：<br>**
**配置：（参照DEMO）<br>**
	*在你的项目包名(applicationId：com.xxx.xxx)目录下建立.wxapi（如com.xxx.xxx.wxapi）目 录。<br>在目录下新建WXPayEntryActivity.java,继承WXPayEntryBaseActivity.java,实现getWXAppId()方法。<br>
	
	
	
	
	*在androidMainfest中添加<br>
	
	
	```
	 <activity
            android:name=".wxapi.WXPayEntryActivity"
            android:exported="true"
            android:launchMode="singleTop"
            android:theme="@android:style/Theme.Translucent.NoTitleBar"/>
	    
	```   
**调用方式<br>**
	
```
 private void testWXPay() {
        //实现微信支付策略
        String wxAppId = "";
        WXPay wxPay = WXPay.getInstance(this,wxAppId);
        WXPayInfoImpli wxPayInfoImpli = new WXPayInfoImpli();
        wxPayInfoImpli.setAppid("");
        wxPayInfoImpli.setNonceStr("");
        wxPayInfoImpli.setPackageValue("");
        wxPayInfoImpli.setPartnerid("");
        wxPayInfoImpli.setPrepayId("");
        wxPayInfoImpli.setSign("");
        wxPayInfoImpli.setTimestamp("");
        wxPay.fastPay(this, wxPayInfoImpli, new FastPayCallBack() {
            @Override
            public void success() {
                
            }

            @Override
            public void faild() {

            }

            @Override
            public void cancel() {

            }
        });
    }
    
 ````   
 
 就这样就搞定了。<br>
 ##注意：
    微信支付与友盟分享jar包会冲突，使用时删除友盟分享微信的jar包
    
 联系我
 --
 有问题欢迎ISSUES
 Email:`liuzhipeng1214@gmail.com`
    
