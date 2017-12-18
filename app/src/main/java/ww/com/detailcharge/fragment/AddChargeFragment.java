package ww.com.detailcharge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

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
    private int lastPosition = 0;
    int[] picExpenseArray = {
            R.drawable.cate1_1,
            R.drawable.cate2_1,
            R.drawable.cate3_1,
            R.drawable.cate4_1,
            R.drawable.cate5_1,
            R.drawable.cate6_1,
            R.drawable.cate7_1,
            R.drawable.cate8_1,
            R.drawable.cate_37_1,
            R.drawable.cate12_1,
            R.drawable.cate13_1,
            R.drawable.cate14_1,
            R.drawable.cate15_1,
            R.drawable.cate16_1,
            R.drawable.cate17_1,
            R.drawable.cate18_1,
            R.drawable.cate19_1,
            R.drawable.cate21_1,
            R.drawable.cate22_1,
            R.drawable.cate23_1,
            R.drawable.cate24_1,
            R.drawable.cate25_1,
            R.drawable.cate26_1,
            R.drawable.cate35_1,
            R.drawable.cate36_1
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
            "房租",
            "还款",
            "发红包",
            "医疗",
            "通讯",
            "服饰",
            "美容",
            "住房",
            "旅行",
            "汽车",
            "书籍",
            "学习",
            "宠物",
            "礼物",
            "办公",
            "水电",
            "社交",

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
    private ImageView             ivTitleIcon;
    private TextView              tvTitleText;
    private TextView              tvAccountNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        rbExpense = (RadioButton) view.findViewById(R.id.rb_expense);
        rbIncome = (RadioButton) view.findViewById(R.id.rb_income);
        rcvView = (RecyclerView) view.findViewById(R.id.rcv_view);
        scrollView = (ScrollView) view.findViewById(R.id.scroll);
        llBottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
        ivTitleIcon = (ImageView) view.findViewById(R.id.iv_title_icon);
        tvTitleText = (TextView) view.findViewById(R.id.tv_title_text);
        tvAccountNum = (TextView) view.findViewById(R.id.tv_account_num);
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
        rcvView.setHasFixedSize(true);
        rcvView.setAdapter(adapter);
        setRecViewOnclick(adapter);
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
                if (adapter != null) {
                    rcvView.removeAllViews();
                    adapter.notifyDataSetChanged();
                }
                ToastUtils.show(getActivity(), "expense");
                adapter = new MyRecyclerViewAdapter(getActivity(), getPicExpenseList(), getTextExpenseList(), this);
                adapter.notifyDataSetChanged();
                ivTitleIcon.setBackground(null);
                ivTitleIcon.setImageDrawable(null);
                tvTitleText.setText("");
                setRecViewOnclick(adapter);
                rcvView.setAdapter(adapter);
                break;

            case R.id.rb_income:
                if (adapter != null) {
                    rcvView.removeAllViews();
                    adapter.notifyDataSetChanged();
                }
                //                homeActivity.repleaceFragment(FragmentFactory.getFrament(6));
                adapter = new MyRecyclerViewAdapter(getActivity(), getPicIncomeList(), getTextIncomeList(), this);
                adapter.notifyDataSetChanged();
                rcvView.setAdapter(adapter);
                ivTitleIcon.setBackground(null);
                ivTitleIcon.setImageDrawable(null);
                tvTitleText.setText("");
                setRecViewOnclick(adapter);
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

    private void setRecViewOnclick(final MyRecyclerViewAdapter adapter) {
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int postion) {
                View lastChildView = rcvView.getChildAt(lastPosition);
                View nowChildView = rcvView.getChildAt(postion);
                if (nowChildView != null && lastChildView != null) {
                    MyRecyclerViewAdapter.MyViewHolder last = (MyRecyclerViewAdapter.MyViewHolder) rcvView.getChildViewHolder(lastChildView);
                    MyRecyclerViewAdapter.MyViewHolder now = (MyRecyclerViewAdapter.MyViewHolder) rcvView.getChildViewHolder(nowChildView);
                    if (lastPosition != postion) {
                        now.llIconBg.setBackgroundResource(R.drawable.yuan_yello128);
                        last.llIconBg.setBackgroundResource(R.drawable.yuan_gray128);
                    } else {
                        now.llIconBg.setBackgroundResource(R.drawable.yuan_yello128);
                    }
                    Integer integer = adapter.getPicList().get(postion);
                    String text = adapter.getTextList().get(postion);
                    ivTitleIcon.setBackgroundResource(R.drawable.yuan_yello128);
                    ivTitleIcon.setImageResource(integer);
                    tvTitleText.setText(text);
                    lastPosition = postion;
                }   else {
                    ToastUtils.show(getContext(),"请重新选择！");
                }

            }
        });
    }
}
