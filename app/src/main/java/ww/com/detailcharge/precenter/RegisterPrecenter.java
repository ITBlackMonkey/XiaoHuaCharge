package ww.com.detailcharge.precenter;

import android.text.TextUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import ww.com.detailcharge.db.UserInfo;
import ww.com.detailcharge.precenter.iview.IRegisterView;
import ww.com.detailcharge.utils.threadutil.ThreadUtils;

/**
 * @author: WANGWEI on 2017/12/4 0004.
 */

public class RegisterPrecenter {

    IRegisterView iRegisterView;

    public RegisterPrecenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    public void registe(final String name, final String password, final String phone, final String email) {
        ThreadUtils.runOnSubthread(new Runnable() {
            @Override
            public void run() {
                final UserInfo info = new UserInfo();
                info.setUsername(name);
                info.setPassword(password);
                info.setEmail(email);
                info.setPhoneNumber(phone);
                info.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            //注册成功
                            iRegisterView.registerSucc();
                        } else {
                            //注册失败
                            iRegisterView.registerFail(e.getMessage());
                        }
                    }
                });
            }
        });

    }

    public void checkNameExist(final String name, final String password, final String phone, final String email) {

        if (emptyChecked(name, password, phone)) {
            ThreadUtils.runOnSubthread(new Runnable() {
                @Override
                public void run() {
                    BmobQuery<UserInfo> bmobQuery = new BmobQuery<>();
                    bmobQuery.addWhereEqualTo("username", name);
                    bmobQuery.findObjects(new FindListener<UserInfo>() {
                        @Override
                        public void done(List<UserInfo> list, BmobException e) {
                            if (list.size() > 0) {
                                iRegisterView.nameExist();
                            } else {
                                registe(name, password, phone, email);
                            }
                        }
                    });
                }
            });
        }
    }

    public boolean emptyChecked(String name, String password, String phone) {
        if (TextUtils.isEmpty(name)) {
            iRegisterView.usernameEmpty();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            iRegisterView.passwordEmpty();
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            iRegisterView.phoneEmpty();
            return false;
        }

        return true;
    }
}
