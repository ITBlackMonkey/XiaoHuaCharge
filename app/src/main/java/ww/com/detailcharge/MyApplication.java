package ww.com.detailcharge;

import android.app.Application;

import cn.bmob.v3.Bmob;
import ww.com.detailcharge.db.UserInfo;

/**
 * @author: WANGWEI on 2017/12/1 0001.
 */

public class MyApplication extends Application {

    private static MyApplication  mApplication;

    static UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "d2e24c4e642766535a82d848cde8d1ba");
        mApplication = this;
    }

    public static MyApplication getInstance() {
        return mApplication;
    }

    public UserInfo getUserinfo() {
        if (userInfo == null) {
            return new UserInfo();
        }
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


}
