# FastPay 封装了支付宝支付，微信支付，银联支付
Usage(使用)
-------------------------------------------------------------------------------------------------------------

1.在 build.gradle中使用
	
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        compile 'com.github.LiuZhiPeng1214:FastPay:v1.0.1'
	}
2.下载库后导入

	compile project(':fastintegrationpay')
