package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.utils.EmjoyUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */

public class ChangYongBqAdapter extends RecyclerView.Adapter<ChangYongBqAdapter.ChangYongBqHolder> implements View.OnClickListener {

    private Context context;
    private LayoutInflater inflater;
    private MyItemClickListener mListener;
    public ChangYongBqAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ChangYongBqHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pop_drawer_item,null);
        view.setOnClickListener(this);
        return new ChangYongBqHolder(view);
    }

    @Override
    public void onBindViewHolder(ChangYongBqHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.imgBq.setImageResource(EmjoyUtil.getChangYongEmjoy()[position]);
    }

    @Override
    public int getItemCount() {
        return EmjoyUtil.getChangYongEmjoy().length;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.onItemClick(v, (int) v.getTag());
        }
    }

    class ChangYongBqHolder extends RecyclerView.ViewHolder {
        private ImageView imgBq;

        public ChangYongBqHolder(View itemView) {
            super(itemView);
            imgBq = (ImageView) itemView.findViewById(R.id.img_bq);
        }
    }
    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }
}
