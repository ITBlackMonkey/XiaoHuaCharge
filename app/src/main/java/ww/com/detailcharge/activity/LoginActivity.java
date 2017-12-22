package ww.com.detailcharge.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ww.com.detailcharge.R;
import ww.com.detailcharge.db.UserInfo;
import ww.com.detailcharge.precenter.LoginPrecenter;
import ww.com.detailcharge.precenter.iview.ILoginView;
import ww.com.detailcharge.utils.tools.SPTools;
import ww.com.detailcharge.viewutis.LoadingDialog;
import ww.com.detailcharge.viewutis.ToastUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private EditText          etName;
    private EditText          etPasssword;
    private EditText          etPhone;
    private LoginPrecenter    loginPrecenter;
    private CoordinatorLayout container;
    private AppCompatCheckBox checkBox;
    private String            name;
    private String            password;
    private String            phone;
    private TextView          tvRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPrecenter = new LoginPrecenter(this);
    }

    @Override
    public void initView() {
        etName = findView(R.id.et_name);
        etPasssword = findView(R.id.et_paw);
        etPhone = findView(R.id.et_phone);
        container = findView(R.id.container);
        checkBox = findView(R.id.checkbox);
        tvRegister = findView(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        findView(R.id.btn_login).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login:
                name = etName.getText().toString().trim();
                password = etPasssword.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                if (loginPrecenter.emptyChecked(name, password, phone)) {
                    LoadingDialog.showProgress(this, "登录中。。。");
                    loginPrecenter.login(name, password, phone);
                }
                break;
            case R.id.tv_register:
                startActivity(RegisterActivity.class);
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    public void nameEmpty() {
        ToastUtils.showSneack(container, "用户名不能为空！");
    }

    @Override
    public void passwordEmpty() {
        ToastUtils.showSneack(container, "密码不能为空！");
    }

    @Override
    public void phoneEmpty() {
        ToastUtils.showSneack(container, "手机号不能为空！");
    }

    @Override
    public void nameNotExsit() {
        LoadingDialog.dismissprogress();
        ToastUtils.showSneack(container, "用户名不存在！请注册！");
    }

    @Override
    public void passwordErr() {
        LoadingDialog.dismissprogress();
        ToastUtils.show(this, "密码错误！");
    }

    @Override
    public void phoneErr() {
        LoadingDialog.dismissprogress();
        ToastUtils.show(this, "手机号错误！");
    }

    @Override
    public void loginErr(String errMsg) {
        LoadingDialog.dismissprogress();
        ToastUtils.show(this, errMsg);
    }

    @Override
    public void loginSucc() {
        finish();
        startActivity(HomeActivity.class);
        ToastUtils.show(this, "登陆成功");
        UserInfo info = new UserInfo();
        info.setUsername(name);
        info.setPassword(password);
        info.setPhoneNumber(phone);
        if (checkBox.isChecked()) {
            SPTools.spPutString(this, "uname", name);
            SPTools.spPutString(this, "upass", password);
            SPTools.spPutString(this, "uphone", phone);
        } else {
            SPTools.spClear(this);
        }
        LoadingDialog.dismissprogress();
    }

    @Override
    public void saveSucc() {

    }

    @Override
    public void saveFaill(String errMsg) {

    }

    @Override
    public void initData() {
        super.initData();
        etName.setText(SPTools.spGetString(this, "uname"));
        etPasssword.setText(SPTools.spGetString(this, "upass"));
        etPhone.setText(SPTools.spGetString(this, "uphone"));
    }


    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
