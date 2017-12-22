package ww.com.detailcharge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ww.com.detailcharge.R;

/**
 * @author: WANGWEI on 2017/12/21 0021.
 */

public class MyBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEAD    = 0;
    private static final int TYPE_CONTENT = 1;
    Context mContext;

    public MyBookAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(mContext);

        switch (viewType) {
            case TYPE_HEAD:
                ViewGroup headView = (ViewGroup) mInflater.inflate(R.layout.item_book_title, parent, false);
                HeadViewHolder headViewHolder = new HeadViewHolder(headView);
                return headViewHolder;

            case TYPE_CONTENT:
                ViewGroup contentView = (ViewGroup) mInflater.inflate(R.layout.item_book_content, parent, false);
                ContentViewHolder contentViewHolder = new ContentViewHolder(contentView);
                return contentViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_HEAD:
                HeadViewHolder headViewHolder = (HeadViewHolder) holder;

                break;

            case TYPE_CONTENT:
                ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
                break;

            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        int viewType;
        return super.getItemViewType(position);

    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        public ContentViewHolder(View itemView) {
            super(itemView);
        }
    }
}
