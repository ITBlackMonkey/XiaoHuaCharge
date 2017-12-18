package ww.com.detailcharge.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ww.com.detailcharge.R;

/**
 * @author: WANGWEI on 2017/12/11 0011.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {

    Context       context;
    List<Integer> picList;
    List<String>  textList;
    Fragment      fragment;
    int tag;
    private OnItemClickListener mOnItemClickListener;
    public  MyViewHolder        viewHolder;

    public MyRecyclerViewAdapter(Context context, List<Integer> picList, List<String> textList, Fragment fragment) {
        this.context = context;
        this.picList = picList;
        this.textList = textList;
        this.fragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_charge, parent, false);
        viewHolder = new MyViewHolder(view);
        viewHolder.llBg.setOnClickListener(this);
        if (viewHolder != null) {

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(textList.get(position));
        holder.ivPic.setImageResource(picList.get(position));
        holder.llBg.setTag(position);
    }


    @Override
    public int getItemCount() {
        return picList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            tag = (int) v.getTag();
            mOnItemClickListener.onItemClick(v, tag);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView     textView;
        ImageView    ivPic;
        LinearLayout llBg;
        public LinearLayout llIconBg;

        public MyViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_add);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            llBg = (LinearLayout) itemView.findViewById(R.id.ll_bg);
            llIconBg = (LinearLayout) itemView.findViewById(R.id.ll_icon_bg);
        }

    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<Integer> getPicList(){
        return picList;
    }

    public List<String> getTextList(){
        return textList;
    }
}
