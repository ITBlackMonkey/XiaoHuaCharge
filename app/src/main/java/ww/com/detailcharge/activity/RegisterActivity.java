package ww.com.detailcharge.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ww.com.detailcharge.R;
import ww.com.detailcharge.precenter.RegisterPrecenter;
import ww.com.detailcharge.precenter.iview.IRegisterView;
import ww.com.detailcharge.viewutis.EditTextUtil;
import ww.com.detailcharge.viewutis.LoadingDialog;
import ww.com.detailcharge.viewutis.ToastUtils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {

    public  EditText          etName;
    private EditText          etPaw;
    private Button            btnRegister;
    private TextInputLayout   textnameInput;
    private TextInputLayout   textPasswordInput;
    private EditText          etPhone;
    private EditText          etEmail;
    private RegisterPrecenter precenter;
    private CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        precenter = new RegisterPrecenter(this);
    }


    @Override
    public void initView() {
        etName = findView(R.id.et_name);
        etPaw = findView(R.id.et_paw);
        textnameInput = findView(R.id.textName);
        textPasswordInput = findView(R.id.textPasswrd);
        etPhone = findView(R.id.et_phone);
        etEmail = findView(R.id.et_email);
        container = findView(R.id.container);
        findView(R.id.tv_login).setOnClickListener(this);

        btnRegister = findView(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        EditTextUtil.hiddenHint(etName, textnameInput, "用户名");
        EditTextUtil.hiddenHint(etPaw, textPasswordInput, "密码");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register) {
            String name = etName.getText().toString().trim();
            String paw = etPaw.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            if (precenter.emptyChecked(name, paw, phone)) {
                LoadingDialog.showProgress(this, "注册中。。。");
                precenter.checkNameExist(name, paw, phone, email);
            }

        } else if (v.getId() == R.id.tv_login) {
            startActivity(LoginActivity.class);
            finish();
        }
    }


    @Override
    public void usernameEmpty() {
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
    public void registerSucc() {
        LoadingDialog.dismissprogress();
        ToastUtils.show(this, "注册成功！");
        startActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void nameExist() {
        LoadingDialog.dismissprogress();
        ToastUtils.show(this, "用户名已存在！");
    }

    @Override
    public void registerFail(String msg) {
        LoadingDialog.dismissprogress();
        ToastUtils.show(this, msg);
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
