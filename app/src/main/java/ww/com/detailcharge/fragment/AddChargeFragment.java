package ww.com.detailcharge.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ww.com.detailcharge.MyApplication;
import ww.com.detailcharge.R;
import ww.com.detailcharge.activity.HomeActivity;
import ww.com.detailcharge.adapter.MyRecyclerViewAdapter;
import ww.com.detailcharge.db.AddCharge;
import ww.com.detailcharge.global.ChargeType;
import ww.com.detailcharge.precenter.AddChargePrecenter;
import ww.com.detailcharge.precenter.iview.IAddChargeView;
import ww.com.detailcharge.utils.tools.CaladarUtils;
import ww.com.detailcharge.utils.tools.GlobalVariables;
import ww.com.detailcharge.utils.tools.SPTools;
import ww.com.detailcharge.viewutis.LoadingDialog;
import ww.com.detailcharge.viewutis.MySrollView;
import ww.com.detailcharge.viewutis.ToastUtils;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static ww.com.detailcharge.R.id.tv_title_text;

/**
 * @author: WANGWEI on 2017/12/11 0011.
 */

public class AddChargeFragment extends Fragment implements View.OnClickListener, IAddChargeView {

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
    public MySrollView scrollView;

    private MyRecyclerViewAdapter adapter;
    private ImageView             ivTitleIcon;
    private TextView              tvTitleText;
    private TextView              tvAccountNum;
    private LinearLayout          llBottom;
    private TextView              moneyText;
    private SimpleDateFormat formatItem    = new SimpleDateFormat("yyyy年MM月dd日");
    private SimpleDateFormat formatSum     = new SimpleDateFormat("yyyy年MM月");
    private DecimalFormat    decimalFormat = new DecimalFormat("0.00");
    private String             money;
    private LinearLayout       moneyWindow;
    private AddChargePrecenter chargePrecenter;

    List<AddCharge> dayList = new ArrayList<>();
    private boolean               aBoolean;
    private EditText              etDes;
    private LinearLayout          llBanner;
    private PercentRelativeLayout plInput;
    private LinearLayout          llSidebar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        rbExpense = (RadioButton) view.findViewById(R.id.rb_expense);
        rbIncome = (RadioButton) view.findViewById(R.id.rb_income);
        rcvView = (RecyclerView) view.findViewById(R.id.rcv_view);
        scrollView = (MySrollView) view.findViewById(R.id.scroll);

        ivTitleIcon = (ImageView) view.findViewById(R.id.iv_title_icon);
        tvTitleText = (TextView) view.findViewById(tv_title_text);
        tvAccountNum = (TextView) view.findViewById(R.id.tv_account_num);

        llBottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
        moneyText = (TextView) view.findViewById(R.id.tv_account_num);
        moneyWindow = (LinearLayout) view.findViewById(R.id.have_chosen);
        plInput = (PercentRelativeLayout) view.findViewById(R.id.input_board);
        llSidebar = (LinearLayout) view.findViewById(R.id.calculator_sidebar);
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

        view.findViewById(R.id.add_finish).setOnClickListener(this);

        etDes = (EditText) view.findViewById(R.id.et_des);
        llBanner = (LinearLayout) view.findViewById(R.id.calculator_banner);
        rbExpense.setOnClickListener(this);
        rbIncome.setOnClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcvView.setLayoutManager(gridLayoutManager);
        initData();
        return view;
    }

    private void initData() {

        adapter = new MyRecyclerViewAdapter(getActivity(), getPicExpenseList(), getTextExpenseList(), this);
        rcvView.setHasFixedSize(true);
        rcvView.setAdapter(adapter);
        setRecViewOnclick(adapter, ChargeType.CHARGE_EXPENSE);
        chargePrecenter = new AddChargePrecenter(this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int screenHeight = metrics.heightPixels;

        llBottom.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() { //当界面大小变化时，系统就会调用该方法
                        Rect r = new Rect(); //该对象代表一个矩形（rectangle）
                        llBottom.getWindowVisibleDisplayFrame(r); //将当前界面的尺寸传给Rect矩形
                        int deltaHeight = screenHeight - r.bottom;  //弹起键盘时的变化高度，在该场景下其实就是键盘高度。
                        if (deltaHeight > 150) {  //该值是随便写的，认为界面高度变化超过150px就视为键盘弹起。
                            plInput.setVisibility(View.GONE);
                            llSidebar.setVisibility(View.GONE);
                            etDes.setFocusable(true);
                        } else {
                            plInput.setVisibility(View.VISIBLE);
                            llSidebar.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_expense:
                moneyWindow.setVisibility(View.GONE);
                llBottom.setVisibility(View.GONE);
                lastPosition = 0;
                moneyText.setText("0.00");
                calculatorClear();
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
                setRecViewOnclick(adapter, ChargeType.CHARGE_EXPENSE);
                rcvView.setAdapter(adapter);
                break;

            case R.id.rb_income:
                moneyWindow.setVisibility(View.GONE);
                lastPosition = 0;
                moneyText.setText("0.00");
                calculatorClear();
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
                setRecViewOnclick(adapter, ChargeType.CHARGE_INCOME);
                ToastUtils.show(getActivity(), "income");
                break;
            case R.id.add_finish:
                String moneyString = moneyText.getText().toString();
                if (moneyString.equals("0.00") || GlobalVariables.getmInputMoney().equals(""))
                    Toast.makeText(getApplicationContext(), "唔姆，你还没输入金额", Toast.LENGTH_SHORT).show();
                else {
                    // 将数据存入数据库
                    putDatebase(moneyString);
                    calculatorClear();
                }
                llBottom.setVisibility(View.GONE);
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

    private void putDatebase(String text) {
        LoadingDialog.showProgress(getActivity(), "数据保存中。。。");
        int chargeType = 0;
        boolean checked = rbExpense.isChecked();
        if (checked) {
            chargeType = ChargeType.CHARGE_EXPENSE;
        } else {
            chargeType = ChargeType.CHARGE_INCOME;
        }
        Integer ivTitleIconTag = (Integer) ivTitleIcon.getTag();
        String moneyText = tvTitleText.getText().toString();
        int userId = MyApplication.getInstance().getUserinfo().getId();
        String day = SPTools.spGetString(getContext(), "Day");
        if (day.equals("")) {
            aBoolean = true;
        } else if (!day.equals(CaladarUtils.StringData("DAY"))) {
            aBoolean = true;
        } else {
            aBoolean = false;
        }

        String desText = etDes.getText().toString().trim();
        chargePrecenter.addCharge(userId, chargeType, ivTitleIconTag, moneyText, text, desText, aBoolean);
    }

    @Override
    public void onResume() {
        super.onResume();
        rbExpense.setChecked(true);
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


    private void setRecViewOnclick(final MyRecyclerViewAdapter adapter, int type) {
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                moneyText.setText("0.00");
                calculatorClear();
                llBottom.setVisibility(View.VISIBLE);
                moneyWindow.setVisibility(View.VISIBLE);
                View lastChildView = rcvView.getLayoutManager().findViewByPosition(lastPosition);
                View nowChildView = rcvView.getLayoutManager().findViewByPosition(postion);
                if (nowChildView != null && lastChildView != null) {
                    MyRecyclerViewAdapter.MyViewHolder last = (MyRecyclerViewAdapter.MyViewHolder) rcvView.getChildViewHolder(lastChildView);
                    MyRecyclerViewAdapter.MyViewHolder now = (MyRecyclerViewAdapter.MyViewHolder) rcvView.getChildViewHolder(nowChildView);
                    if (lastPosition != postion) {
                        now.llIconBg.setBackgroundResource(R.drawable.yuan_yello128);
                        last.llIconBg.setBackgroundResource(R.drawable.yuan_bg128);
                    } else {
                        now.llIconBg.setBackgroundResource(R.drawable.yuan_yello128);
                    }
                    Integer integer = adapter.getPicList().get(postion);
                    String text = adapter.getTextList().get(postion);
                    ivTitleIcon.setBackgroundResource(R.drawable.yuan_yello128);
                    ivTitleIcon.setImageResource(integer);
                    ivTitleIcon.setTag(integer);
                    tvTitleText.setText(text);
                    lastPosition = postion;
                    scrollToBottom(scrollView);
                } else {
                    ToastUtils.show(getContext(), "请重新选择！");
                }

            }
        });
    }

    private void scrollToBottom(final MySrollView scrollView) {
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


    // 数字输入按钮
    public void calculatorNumOnclick(View v) {
        Button view = (Button) v;
        String digit = view.getText().toString();
        String money = GlobalVariables.getmInputMoney();
        this.money = money + digit;
        if (this.money.length() > 7) {
            Toast.makeText(getApplicationContext(), "真败家，一下就花了几十万。。", Toast.LENGTH_SHORT).show();
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

    @Override
    public void savaSucc(AddCharge addCharge) {
        ToastUtils.show(getActivity(), "恭喜你，信息录入成功！");

        getActivity().finish();
        dayList.add(addCharge);
        SPTools.spPutString(getActivity(), "Day", CaladarUtils.StringData("DAY"));
        LoadingDialog.dismissprogress();

    }

    @Override
    public void saveFaill(String errMsg) {
        LoadingDialog.dismissprogress();
        ToastUtils.show(getActivity(), "Sorry，信息录入失败！");
        calculatorClear();
    }

    private boolean hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive(etDes)) {   //因为是在fragment下，所以用了getView()获取view，也可以用findViewById（）来获取父控件
            getView().requestFocus();//使其它view获取焦点.这里因为是在fragment下,所以便用了getView(),可以指定任意其它view
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            return true;
        }
        return false;
    }
}
