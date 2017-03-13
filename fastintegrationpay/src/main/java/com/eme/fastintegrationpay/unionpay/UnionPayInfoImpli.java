package com.eme.fastintegrationpay.unionpay;

import android.os.Parcel;
import android.os.Parcelable;

import com.eme.fastintegrationpay.base.FastPayInfo;

/**
 * Created by eme on 2017/3/13.
 */

public class UnionPayInfoImpli implements FastPayInfo,Parcelable {
    private Mode mode;
    private String tn;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mode == null ? -1 : this.mode.ordinal());
        dest.writeString(this.tn);
    }

    public UnionPayInfoImpli() {

    }
    protected UnionPayInfoImpli(Parcel in) {
        int tmpMode = in.readInt();
        this.mode = tmpMode == -1 ? null: Mode.values()[tmpMode];
        this.tn = in.readString();
    }

    public static final  Parcelable.Creator<UnionPayInfoImpli> CREATOR = new Parcelable.Creator<UnionPayInfoImpli>() {

        @Override
        public UnionPayInfoImpli createFromParcel(Parcel source) {
            return new UnionPayInfoImpli(source);
        }

        @Override
        public UnionPayInfoImpli[] newArray(int size) {
            return new UnionPayInfoImpli[size];
        }
    };
}
