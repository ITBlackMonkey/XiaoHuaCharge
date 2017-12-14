package ww.com.detailcharge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import ww.com.detailcharge.R;
import ww.com.detailcharge.activity.HomeActivity;
import ww.com.detailcharge.adapter.MyRecyclerViewAdapter;
import ww.com.detailcharge.viewutis.ToastUtils;

/**
 * @author: WANGWEI on 2017/12/11 0011.
 */

public class AddChargeFragment extends Fragment implements View.OnClickListener {

    private RadioButton       rbExpense;
    private RadioButton       rbIncome;
    private HomeActivity      homeActivity;
    private RecyclerView      rcvView;
    private ArrayList<String> mDatas;
    private List<Integer>     picExpenseList;
    private List<String>      textExpenseList;
    private List<Integer>     picIncomeList;
    private List<String>      textIncomeList;
    int[] picExpenseArray = {
            R.drawable.cate1_1,
            R.drawable.cate2_1,
            R.drawable.cate3_1,
            R.drawable.cate4_1,
            R.drawable.cate5_1,
            R.drawable.cate6_1,
            R.drawable.cate7_1,
            R.drawable.cate8_1,
            R.drawable.cate9_1,
            R.drawable.cate10_1,
            R.drawable.cate11_1,
            R.drawable.cate12_1,
            R.drawable.cate13_1,
            R.drawable.cate14_1,
            R.drawable.cate15_1,
            R.drawable.cate16_1,
            R.drawable.cate17_1,
            R.drawable.cate18_1,
            R.drawable.cate19_1,
            R.drawable.cate20_1,
            R.drawable.cate21_1,
            R.drawable.cate22_1,
            R.drawable.cate23_1,
            R.drawable.cate24_1,
            R.drawable.cate25_1,
            R.drawable.cate26_1,
            R.drawable.cate27_1,
            R.drawable.cate35_1,
            R.drawable.cate36_1,
    };

    int[] picIncomeArray = {
            R.drawable.cate28_1,
            R.drawable.cate29_1,
            R.drawable.cate30_1,
            R.drawable.cate31_1,
            R.drawable.cate32_1,
            R.drawable.cate33_1,
            R.drawable.cate34_1,
    };

    String[] textExpenseArray = {
            "日常",
            "餐饮",
            "购物",
            "日用",
            "交通",
            "果蔬",
            "运动",
            "娱乐",
            "理财",
            "股票",
            "基金",
            "还款",
            "发红包",
            "医疗",
            "通讯",
            "服饰",
            "美容",
            "住房",
            "旅行",
            "烟酒",
            "汽车",
            "书籍",
            "学习",
            "宠物",
            "礼物",
            "办公",
            "彩票",
            "水电",
            "社交"
    };

    String[] textIncomeArray = {
            "工资",
            "兼职",
            "理财",
            "股票",
            "基金",
            "礼金",
            "其他",
    };
    public  ScrollView            scrollView;
    private LinearLayout          llBottom;
    private MyRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        rbExpense = (RadioButton) view.findViewById(R.id.rb_expense);
        rbIncome = (RadioButton) view.findViewById(R.id.rb_income);
        rcvView = (RecyclerView) view.findViewById(R.id.rcv_view);
        scrollView = (ScrollView) view.findViewById(R.id.scroll);
        llBottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
        rbExpense.setOnClickListener(this);
        rbIncome.setOnClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcvView.setLayoutManager(gridLayoutManager);
        initData();
        return view;
    }

    private void initData() {
        getData();
        adapter = new MyRecyclerViewAdapter(getActivity(), getPicExpenseList(), getTextExpenseList(), this);
        rcvView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeActivity = (HomeActivity) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_expense:
                ToastUtils.show(getActivity(), "expense");
                adapter = new MyRecyclerViewAdapter(getActivity(), getPicExpenseList(), getTextExpenseList(), this);
                adapter.notifyDataSetChanged();
                rcvView.setAdapter(adapter);
                break;

            case R.id.rb_income:
//                homeActivity.repleaceFragment(FragmentFactory.getFrament(6));
                adapter = new MyRecyclerViewAdapter(getActivity(), getPicIncomeList(), getTextIncomeList(), this);
                adapter.notifyDataSetChanged();
                rcvView.setAdapter(adapter);
                ToastUtils.show(getActivity(), "income");
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        rbExpense.setChecked(true);
    }

    protected void getData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private List<String> getTextExpenseList() {
        textExpenseList = new ArrayList<>();
        if (textExpenseArray.length != 0) {
            for (String text : textExpenseArray) {
                textExpenseList.add(text);
            }
        }
        return textExpenseList;
    }


    private List<String> getTextIncomeList() {
        textIncomeList = new ArrayList<>();
        if (textIncomeArray.length != 0) {
            for (String text : textIncomeArray) {
                textIncomeList.add(text);
            }
        }
        return textIncomeList;
    }


    private List<Integer> getPicExpenseList() {
        picExpenseList = new ArrayList<>();
        if (picExpenseArray.length != 0) {
            for (int picDrawable : picExpenseArray) {
                picExpenseList.add(picDrawable);
            }
        }
        return picExpenseList;
    }

    private List<Integer> getPicIncomeList() {
        picIncomeList = new ArrayList<>();
        if (picIncomeArray.length != 0) {
            for (int picDrawable : picIncomeArray) {
                picIncomeList.add(picDrawable);
            }
        }
        return picIncomeList;
    }


    public void setBottomVisibility(boolean aboolean) {
        if (aboolean) {
            llBottom.setVisibility(View.VISIBLE);
        } else {
            llBottom.setVisibility(View.GONE);
        }

    }
}
