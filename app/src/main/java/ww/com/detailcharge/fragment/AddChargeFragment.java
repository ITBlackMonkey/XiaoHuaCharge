package ww.com.detailcharge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ww.com.detailcharge.R;
import ww.com.detailcharge.activity.HomeActivity;
import ww.com.detailcharge.adapter.MyRecyclerViewAdapter;
import ww.com.detailcharge.utils.tools.GlobalVariables;
import ww.com.detailcharge.viewutis.ToastUtils;

import static cn.bmob.v3.Bmob.getApplicationContext;

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
    public ScrollView scrollView;

    private MyRecyclerViewAdapter adapter;
    private ImageView             ivTitleIcon;
    private TextView              tvTitleText;
    private TextView              tvAccountNum;
    private LinearLayout          llBottom;
    float downY;
    float upY;
    private TextView moneyText;
    private SimpleDateFormat formatItem    = new SimpleDateFormat("yyyy年MM月dd日");
    private SimpleDateFormat formatSum     = new SimpleDateFormat("yyyy年MM月");
    private DecimalFormat    decimalFormat = new DecimalFormat("0.00");
    private String money;
    private LinearLayout moneyWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        rbExpense = (RadioButton) view.findViewById(R.id.rb_expense);
        rbIncome = (RadioButton) view.findViewById(R.id.rb_income);
        rcvView = (RecyclerView) view.findViewById(R.id.rcv_view);
        scrollView = (ScrollView) view.findViewById(R.id.scroll);

        ivTitleIcon = (ImageView) view.findViewById(R.id.iv_title_icon);
        tvTitleText = (TextView) view.findViewById(R.id.tv_title_text);
        tvAccountNum = (TextView) view.findViewById(R.id.tv_account_num);

        llBottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
        moneyText = (TextView) view.findViewById(R.id.tv_account_num);
        moneyWindow = (LinearLayout) view.findViewById(R.id.have_chosen);

        view.findViewById(R.id.one).setOnClickListener(this);
        view.findViewById(R.id.two).setOnClickListener(this);
        view.findViewById(R.id.three).setOnClickListener(this);
        view.findViewById(R.id.four).setOnClickListener(this);
        view.findViewById(R.id.five).setOnClickListener(this);
        view.findViewById(R.id.six).setOnClickListener(this);
        view.findViewById(R.id.seven).setOnClickListener(this);
        view.findViewById(R.id.eight).setOnClickListener(this);
        view.findViewById(R.id.nine).setOnClickListener(this);
        view.findViewById(R.id.zero).setOnClickListener(this);
        view.findViewById(R.id.clear).setOnClickListener(this);
        view.findViewById(R.id.dot).setOnClickListener(this);
        view.findViewById(R.id.calculator_banner).setOnClickListener(this);

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
                moneyWindow.setVisibility(View.GONE);
                llBottom.setVisibility(View.GONE);
                lastPosition = 0;
                moneyText.setText("0.00");
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
                moneyWindow.setVisibility(View.GONE);
                lastPosition = 0;
                moneyText.setText("0.00");
                llBottom.setVisibility(View.GONE);
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

            case R.id.add_finish:
                String moneyString = moneyText.getText().toString();
                if (moneyString.equals("0.00") || GlobalVariables.getmInputMoney().equals(""))
                    Toast.makeText(getApplicationContext(), "唔姆，你还没输入金额", Toast.LENGTH_SHORT).show();
                else {
                    calculatorClear();
                }
                break;
            case R.id.clear:
                calculatorClear();
                moneyText.setText("0.00");
                break;
            case R.id.dot:
                calculatorPushDot();
                break;
            default:
                calculatorNumOnclick(v);
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

        } else {

        }

    }

    private void setRecViewOnclick(final MyRecyclerViewAdapter adapter) {
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                moneyText.setText("0.00");
                money = "";
                GlobalVariables.setmInputMoney("");
                llBottom.setVisibility(View.VISIBLE);
                moneyWindow.setVisibility(View.VISIBLE);
                View lastChildView = rcvView.getLayoutManager().findViewByPosition(lastPosition);
                View nowChildView = rcvView.getLayoutManager().findViewByPosition(postion);
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
                    scrollToBottom(scrollView);
                } else {
                    ToastUtils.show(getContext(), "请重新选择！");
                }

            }
        });
    }

    private void scrollToBottom(final ScrollView scrollView) {
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });
    }

    private int getFirstPosition(RecyclerView rec) {
        RecyclerView.LayoutManager layoutManager = rec.getLayoutManager();
        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            //获取第一个可见view的位置
            return linearManager.findFirstVisibleItemPosition();

        }
        return 0;
    }

    private int getLastPostion(RecyclerView rec) {

        RecyclerView.LayoutManager layoutManager = rec.getLayoutManager();
        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            //获取最后一个可见view的位置
            return linearManager.findLastVisibleItemPosition();

        }

        return 0;
    }


    // 数字输入按钮
    public void calculatorNumOnclick(View v) {
        Button view = (Button) v;
        String digit = view.getText().toString();
        String money = GlobalVariables.getmInputMoney();
        this.money = money + digit;
        if (this.money.length() > 12) {
            Toast.makeText(getApplicationContext(), "唔，已经上千亿了", Toast.LENGTH_SHORT).show();
            return;
        }
        if (GlobalVariables.getmHasDot() && GlobalVariables.getmInputMoney().length() > 2) {
            String dot = money.substring(money.length() - 3, money.length() - 2);

            if (dot.equals(".")) {
                Toast.makeText(getApplicationContext(), "唔，已经不能继续输入了", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        GlobalVariables.setmInputMoney(money + digit);
        moneyText.setText(decimalFormat.format(Double.valueOf(GlobalVariables.getmInputMoney())));
    }


    // 小数点处理工作
    public void calculatorPushDot() {
        if (GlobalVariables.getmHasDot()) {
            Toast.makeText(getApplicationContext(), "已经输入过小数点了 ━ω━●", Toast.LENGTH_SHORT).show();
        } else {
            GlobalVariables.setmInputMoney(GlobalVariables.getmInputMoney() + ".");
            GlobalVariables.setHasDot(true);
        }
    }

    // 清零按钮
    public void calculatorClear() {
        GlobalVariables.setmInputMoney("");
        GlobalVariables.setHasDot(false);
    }

}
