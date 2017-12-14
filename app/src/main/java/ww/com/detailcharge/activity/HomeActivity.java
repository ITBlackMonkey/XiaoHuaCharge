package ww.com.detailcharge.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ww.com.detailcharge.MyApplication;
import ww.com.detailcharge.R;
import ww.com.detailcharge.db.UserInfo;
import ww.com.detailcharge.fragment.FragmentFactory;

public class HomeActivity extends BaseActivity {

    private NavigationView        navigationView;
    private DrawerLayout          drawerLayout;
    public Toolbar               toolbar;
    private ActionBarDrawerToggle toggle;
    private AppCompatTextView     tvHead;
    private AppCompatImageView    ivHead;
    private FrameLayout           content;
    public TextView              tvCenter;
    public ImageView             ivRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void initView() {
        repleaceFragment(FragmentFactory.getFrament(0));
        tvCenter = findView(R.id.tv_center);
        ivRight = findView(R.id.iv_right);
        toolbar = findView(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        navigationView = findView(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        tvHead = (AppCompatTextView) headerView.findViewById(R.id.tv_head);
        navigationView.setBackgroundColor(getResources().getColor(R.color.color0AA770));
        drawerLayout = findView(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        setupDrawerContent(navigationView);
        content = findView(R.id.content);

    }

    @Override
    public void initData() {
        super.initData();
        UserInfo userinfo = MyApplication.getInstance().getUserinfo();
        tvHead.setText(userinfo.getUsername() + "(" + userinfo.getPhoneNumber() + ")");
        //        ivHead.setImageResource(R.drawable.touxiang); //设置头像
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        drawerLayout.closeDrawers();
                        menuItem.setChecked(true);
                        tvCenter.setText(menuItem.getTitle());
                        switch (menuItem.getTitle().toString()) {
                            case "我的账本":
                                repleaceFragment(FragmentFactory.getFrament(0));
                                ivRight.setVisibility(View.VISIBLE);
                                break;
                            case "我要记账":
                                repleaceFragment(FragmentFactory.getFrament(5));
                                ivRight.setVisibility(View.GONE);
                                break;
                            case "我的信息":
                                repleaceFragment(FragmentFactory.getFrament(3));
                                ivRight.setVisibility(View.GONE);
                                break;

                            case "版本检查":
                                repleaceFragment(FragmentFactory.getFrament(1));
                                ivRight.setVisibility(View.GONE);
                                break;
                            case "关于我们":
                                repleaceFragment(FragmentFactory.getFrament(4));
                                ivRight.setVisibility(View.GONE);
                                break;

                            case "退出登录":
                                finish();
                                startActivity(LoginActivity.class);
                                break;

                            default:
                                break;
                        }

                        return true;
                    }
                });
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }


    public void repleaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, fragment);
        transaction.commit();
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
