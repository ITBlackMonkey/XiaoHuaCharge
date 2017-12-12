package ww.com.detailcharge.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

import ww.com.detailcharge.R;
import ww.com.detailcharge.adapter.MyRecyclerViewAdapter;
import ww.com.detailcharge.fragment.BaseFragment;

public class AddChargeActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView               view;
    private ArrayList<String>          mDatas;
    private MyRecyclerViewAdapter      adapter;
    private RadioButton rbExpense;
    private RadioButton rbIncome;
    private BaseFragment baseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_charge);
    }

    @Override
    public void initView() {
        findView(R.id.iv_back).setOnClickListener(this);
//        view = findView(R.id.rcv_view);
        rbExpense = findView(R.id.rb_expense);
        rbIncome = findView(R.id.rb_income);
        rbExpense.setOnClickListener(this);
        rbIncome.setOnClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AddChargeActivity.this, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        this.view.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void initData() {
        super.initData();
        getData();
        adapter = new MyRecyclerViewAdapter(this, mDatas);
//        view.setAdapter(adapter);
        baseFragment = new BaseFragment(this);
    }


    //初始化数据

    protected void getData(){
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++){
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_back:
                finish();
            break;
            case R.id.rb_expense:
                baseFragment.repleaceFragment(R.id.fl_content,baseFragment.getFragment(0));
                break;
            case R.id.rb_income:
                baseFragment.repleaceFragment(R.id.fl_content,baseFragment.getFragment(1));
                break;
            default:
                break;
        }
    }
}
