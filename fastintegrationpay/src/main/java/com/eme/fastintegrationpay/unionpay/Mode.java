package com.eme.fastintegrationpay.unionpay;

/**
 * Created by eme on 2017/3/13.
 */

public enum Mode {
    RELEASE("00"),
    TEST("01");
    private String mMode;
    Mode(String mode){
        mMode = mode;
    }

    public String getmMode() {
        return mMode;
    }

}
