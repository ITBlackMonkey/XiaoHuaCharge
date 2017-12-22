package ww.com.detailcharge.precenter;

import android.text.TextUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import ww.com.detailcharge.MyApplication;
import ww.com.detailcharge.bmob.MySaveListener;
import ww.com.detailcharge.db.DayClass;
import ww.com.detailcharge.db.UserInfo;
import ww.com.detailcharge.precenter.iview.ILoginView;
import ww.com.detailcharge.utils.threadutil.ThreadUtils;

/**
 * @author: WANGWEI on 2017/12/5 0005.
 */

public class LoginPrecenter {

    ILoginView iLoginView;

    public LoginPrecenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    public void login(final String name, final String password, final String phone) {
        if (emptyChecked(name, password, phone)) {   //不为空，进行密码校验
            ThreadUtils.runOnSubthread(new Runnable() {
                @Override
                public void run() {
                    BmobQuery<UserInfo> bmobQuery = new BmobQuery<>();
                    bmobQuery.addWhereEqualTo("username", name);
                    bmobQuery.findObjects(new FindListener<UserInfo>() {
                        @Override
                        public void done(List<UserInfo> list, BmobException e) {

                            if (e != null) {
                                iLoginView.loginErr(e.getMessage());
                                return;
                            }

                            if (list.size() == 0) {
                                iLoginView.nameNotExsit();
                                return;
                            }

                            UserInfo info = list.get(0);
                            String infoPassword = info.getPassword();
                            String infoPhoneNumber = info.getPhoneNumber();

                            if (!password.equals(infoPassword)) {
                                iLoginView.passwordErr();
                                return;
                            }

                            if (!phone.equals(infoPhoneNumber)) {
                                iLoginView.phoneErr();
                                return;
                            }
                            iLoginView.loginSucc();
                            MyApplication.getInstance().setUserInfo(info);
                        }
                    });

                }
            });
        }

    }

    public boolean emptyChecked(String name, String password, String phone) {

        if (TextUtils.isEmpty(name)) {
            iLoginView.nameEmpty();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            iLoginView.passwordEmpty();
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            iLoginView.phoneEmpty();
            return false;
        }

        return true;
    }


    public void getDayFromServer() {

    }

    public void setDay4Server(final String day) {
        ThreadUtils.runOnSubthread(new Runnable() {
            @Override
            public void run() {
                DayClass dayClass = new DayClass();
                dayClass.setDay(day);
                dayClass.save(new MySaveListener() {
                    @Override
                    public void succ() {
                        iLoginView.saveSucc();
                    }

                    @Override
                    public void faill(String errMsg) {
                        iLoginView.saveFaill(errMsg);
                    }
                });
            }
        });
    }
}
