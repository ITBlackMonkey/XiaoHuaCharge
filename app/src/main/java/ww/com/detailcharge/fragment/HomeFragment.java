package ww.com.detailcharge.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import ww.com.detailcharge.R;
import ww.com.detailcharge.activity.HomeActivity;

import static ww.com.detailcharge.R.id.pullToRefreshRV;


public class HomeFragment extends Fragment implements PullToRefreshListener, View.OnClickListener {

    List<String> list = new ArrayList<>();
    private PullToRefreshRecyclerView pullView;
    private ModeAdapter               modeAdapter;
    private RelativeLayout rlAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        pullView = (PullToRefreshRecyclerView) view.findViewById(pullToRefreshRV);
        rlAdd = (RelativeLayout) view.findViewById(R.id.rl_add);
        rlAdd.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pullView.setLayoutManager(layoutManager);
        list.add("21312");
        modeAdapter = new ModeAdapter(getActivity(), R.layout.item_jizhang, list);
        pullView.setAdapter(modeAdapter);
        pullView.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pullView.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        pullView.displayLastRefreshTime(true);
        //设置刷新回调
        pullView.setPullToRefreshListener(this);
        return view;
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
        pullView.postDelayed(new Runnable() {
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
        }, 3000);
    }

    @Override
    public void onClick(View v) {
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.repleaceFragment(FragmentFactory.getFrament(5));
        homeActivity.ivRight.setVisibility(View.GONE);
        homeActivity.tvCenter.setText("我要记账");
    }


    public class ModeAdapter extends BaseAdapter {

    public ModeAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {

    }

}
}
