package ww.com.detailcharge.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ww.com.detailcharge.R;
import ww.com.detailcharge.fragment.AddChargeFragment;
import ww.com.detailcharge.viewutis.ToastUtils;

/**
 * @author: WANGWEI on 2017/12/11 0011.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements View.OnTouchListener {

    Context       context;
    List<Integer> picList;
    List<String> textList;
    Fragment fragment;

    public MyRecyclerViewAdapter(Context context, List<Integer> picList, List<String> textList, Fragment fragment) {
        this.context = context;
        this.picList = picList;
        this.textList = textList;
        this.fragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_charge, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(textList.get(position));
        holder.ivPic.setImageResource(picList.get(position));
        holder.llBg.setOnTouchListener(this);
        holder.llBg.setTag(position);
    }


    @Override
    public int getItemCount() {
        return picList.size();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            //            TODO: 响应点击事件
            ToastUtils.show(context, textList.get((int)v.getTag()));
            AddChargeFragment addChargeFragment = (AddChargeFragment) fragment;
            addChargeFragment.setBottomVisibility(true);
        }
        return false;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView     textView;
        ImageView    ivPic;
        LinearLayout llBg;

        public MyViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_add);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            llBg = (LinearLayout) itemView.findViewById(R.id.ll_bg);
        }
    }


}
