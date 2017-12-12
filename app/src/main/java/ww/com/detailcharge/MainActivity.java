package ww.com.detailcharge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import ww.com.detailcharge.db.UserInfo;
import ww.com.detailcharge.viewutis.ToastUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_find).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                final UserInfo userInfo = new UserInfo();
                userInfo.setUsername("ww");
                userInfo.setPassword("123455");
                userInfo.setDescribe("this describe");
                userInfo.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            ToastUtils.show(MainActivity.this, "添加数据成功，返回objectId为：" + userInfo.getObjectId());
                        } else {
                            ToastUtils.show(MainActivity.this, "创建数据失败：" + e.getMessage());
                        }
                    }
                });
                break;

            case R.id.btn_find:

                BmobQuery<UserInfo> bmobQuery =new BmobQuery<>();
                bmobQuery.addWhereEqualTo("id",50);
                bmobQuery.findObjects(new FindListener<UserInfo>() {
                    @Override
                    public void done(List<UserInfo> list, BmobException e) {
                        for (UserInfo info : list) {
                            System.out.println(info);
                        }
                    }
                });
                break;

            case R.id.btn_update:

                UserInfo info = new UserInfo();
                info.setUsername("this update name");
                info.update("6a713bcc47", new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                         ToastUtils.show(MainActivity.this,"更新成功");
                    }
                });

                break;

            case R.id.btn_delete:
                UserInfo uinfo = new UserInfo();

                uinfo.delete("6a713bcc47", new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        ToastUtils.show(MainActivity.this,"删除成功");
                    }

                }) ;

                break;
            default:
                break;
        }
    }
}
