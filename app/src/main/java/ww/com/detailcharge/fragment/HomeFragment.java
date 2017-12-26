package ww.com.detailcharge.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ww.com.detailcharge.R;
import ww.com.detailcharge.activity.AddChargeActivity;
import ww.com.detailcharge.db.AddCharge;
import ww.com.detailcharge.global.ChargeType;
import ww.com.detailcharge.precenter.GetChargePrecenter;
import ww.com.detailcharge.precenter.iview.IGetChargeView;
import ww.com.detailcharge.utils.tools.CaladarUtils;
import ww.com.detailcharge.utils.tools.SPTools;
import ww.com.detailcharge.viewutis.LoadingDialog;
import ww.com.detailcharge.viewutis.ToastUtils;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static ww.com.detailcharge.R.id.pullToRefreshRV;
import static ww.com.detailcharge.R.id.tv_charge_money;


public class HomeFragment extends Fragment implements PullToRefreshListener, View.OnClickListener, IGetChargeView {

    List<AddCharge> list = new ArrayList<>();
    private PullToRefreshRecyclerView pullView;
    private ModeAdapter               modeAdapter;
    private RelativeLayout            rlAdd;
    private GetChargePrecenter        getChargePrecenter;
    private TextView                  tvTitleExpense;
    private TextView                  tvTitleIncome;
    private TextView                  tvTieleJieyu;
    private TextView                  tvYear;
    private TextView                  tvMonth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getChargePrecenter = new GetChargePrecenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        pullView = (PullToRefreshRecyclerView) view.findViewById(pullToRefreshRV);
        rlAdd = (RelativeLayout) view.findViewById(R.id.rl_add);
        tvTitleExpense = (TextView) view.findViewById(R.id.tv_title_expense);
        tvTitleIncome = (TextView) view.findViewById(R.id.tv_title_income);
        tvTieleJieyu = (TextView) view.findViewById(R.id.tv_title_jieyu);
        tvYear = (TextView) view.findViewById(R.id.tv_year);
        tvMonth = (TextView) view.findViewById(R.id.tv_month);
        tvYear.setText(CaladarUtils.StringData("YEAR"));
        tvMonth.setText(CaladarUtils.StringData("MONTH"));
        rlAdd.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pullView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        pullView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullView.setRefreshComplete();
                //模拟没有数据的情况
                list.clear();
                modeAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {
      /*  pullView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullView.setLoadMoreComplete();
                //模拟加载数据的情况
                int size = list.size();
                for (int i = size; i < size + 4; i++) {
                    list.add("" + i + i + i + i);
                }
                modeAdapter.notifyDataSetChanged();
            }
        }, 3000);*/
    }

    @Override
    public void onClick(View v) {
      /*  HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.repleaceFragment(FragmentFactory.getFrament(5));
        homeActivity.ivRight.setVisibility(GONE);
        homeActivity.tvCenter.setText("我要记账");*/
        startActivity(new Intent(getActivity(), AddChargeActivity.class));
    }

    @Override
    public void findNoData() {
        ToastUtils.show(getActivity(), "Sorry,木有数据嘞！");
        LoadingDialog.dismissprogress();
        SPTools.spPutString(getContext(), "Day", "");
    }

    @Override
    public void findSucc(List<AddCharge> list) {
        LoadingDialog.dismissprogress();
        this.list = list;
        modeAdapter = new ModeAdapter(getActivity(), R.layout.item_jizhang, list);
        pullView.setAdapter(modeAdapter);
        pullView.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pullView.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        pullView.displayLastRefreshTime(true);
        //设置刷新回调
        pullView.setPullToRefreshListener(this);
    }

    @Override
    public void findFaill(String errMsg) {
        LoadingDialog.dismissprogress();
        ToastUtils.show(getActivity(), "Sorry" + errMsg);
    }


    public class ModeAdapter extends BaseAdapter {

        public ModeAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, Object o) {
            AddCharge charge = (AddCharge) o;
            holder.setText(R.id.tv_day, charge.getDay() + "日");
            holder.setText(R.id.tv_week, charge.getWeek());
            String chargeType = charge.getType() == ChargeType.CHARGE_EXPENSE ? "支出:" : "收入:";
            holder.setText(R.id.tv_charge_type, chargeType);
            holder.setImageResource(R.id.iv_imgIcon, charge.getImageDrawable());
            holder.setText(R.id.tv_hms, charge.getHms());
            holder.setText(R.id.tv_charge_text, charge.getDescribe().equals("") ? charge.getText() : charge.getDescribe());
            String chargeMoney = charge.getType() == ChargeType.CHARGE_EXPENSE ? "-" + charge.getMoneyText() : charge.getMoneyText();
            String color = charge.getType() == ChargeType.CHARGE_EXPENSE ? "#F32F83" : "#2F55F3";
            holder.setText(tv_charge_money, chargeMoney);
            holder.setTextColor(tv_charge_money, Color.parseColor(color));
            if (charge.isTitleVisibility()) {
                holder.setViewVisiable(R.id.ll_title, VISIBLE);
            } else {
                holder.setViewVisiable(R.id.ll_title, GONE);
            }

            String day = charge.getDay();
            if (getIncomeMoney(list, day) == 0) {
                holder.setViewVisiable(R.id.tv_income, GONE);
                holder.setViewVisiable(R.id.tv_income_num, GONE);
            } else {
                holder.setViewVisiable(R.id.tv_income, VISIBLE);
                holder.setViewVisiable(R.id.tv_income_num, VISIBLE);
            }
            Double incomeMoney = getIncomeMoney(list, day);
            DecimalFormat df = new DecimalFormat("#.00");
            String strIncome = df.format(incomeMoney);
            holder.setText(R.id.tv_income_num, strIncome);
            Double expenseMoney = getExpenseMoney(list, day);
            String strExpense = df.format(expenseMoney);
            holder.setText(R.id.tv_expense_num, strExpense);

            Double allExpense = getAllExpense(list);
            Double allIncome = getAllIncome(list);
            Double jieyu = allIncome - allExpense;

            String strAllExpense = df.format(allExpense);
            String strAllincome = df.format(allIncome);
            String strJieyu = df.format(jieyu);

            tvTitleExpense.setText(strAllExpense.equals(".00") ? "0.00" : strAllExpense);
            tvTitleIncome.setText(strAllincome.equals(".00") ? "0.00" : strAllincome);
            tvTieleJieyu.setText(strJieyu.equals(".00") ? "0.00" : strJieyu);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getChargePrecenter.getDataFromServer();
        LoadingDialog.showProgress(getActivity(), "数据加载中。。。");

    }

    private Double getExpenseMoney(List<AddCharge> list, String day) {

        List<Double> moneyTextList = new ArrayList<>();
        double expenseSum = 0.00;
        if (list.size() > 0) {
            for (AddCharge addCharge : list) {
                if (addCharge.getDay().equals(day) && addCharge.getType() == ChargeType.CHARGE_EXPENSE) {
                    moneyTextList.add(Double.parseDouble(addCharge.getMoneyText()));
                }
            }
        }

        for (Double expensemoney : moneyTextList) {
            expenseSum += expensemoney;
        }
        return expenseSum;
    }

    private Double getIncomeMoney(List<AddCharge> list, String day) {
        List<Double> moneyTextList = new ArrayList<>();
        double IncomeSum = 0.00;
        if (list.size() > 0) {
            for (AddCharge addCharge : list) {
                if (addCharge.getDay().equals(day) && addCharge.getType() == ChargeType.CHARGE_INCOME) {
                    moneyTextList.add(Double.parseDouble(addCharge.getMoneyText()));
                }
            }
        }

        for (Double expensemoney : moneyTextList) {
            IncomeSum += expensemoney;
        }
        return IncomeSum;
    }

    private Double getAllExpense(List<AddCharge> list) {
        List<Double> allExpenseList = new ArrayList<>();
        Double allExpense = 0.00;
        for (AddCharge addCharge : list) {
            if (addCharge.getType() == ChargeType.CHARGE_EXPENSE) {
                allExpenseList.add(Double.parseDouble(addCharge.getMoneyText()));
            }
        }


        for (Double expense : allExpenseList) {
            allExpense += expense;
        }

        return allExpense;
    }

    private Double getAllIncome(List<AddCharge> list) {
        List<Double> allIncomeList = new ArrayList<>();
        Double allIncome = 0.00;
        for (AddCharge addCharge : list) {
            if (addCharge.getType() == ChargeType.CHARGE_INCOME) {
                allIncomeList.add(Double.parseDouble(addCharge.getMoneyText()));
            }
        }


        for (Double expense : allIncomeList) {
            allIncome += expense;
        }

        return allIncome;
    }

}
