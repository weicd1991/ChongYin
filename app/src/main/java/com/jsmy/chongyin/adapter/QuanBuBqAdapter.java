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
import com.qq.e.comm.pi.POFactory;

/**
 * Created by Administrator on 2017/11/27.
 */

public class QuanBuBqAdapter extends RecyclerView.Adapter<QuanBuBqAdapter.QuanBuBqHolder> implements View.OnClickListener {

    private Context context;
    private LayoutInflater inflater;
    private MyItemClickListener mListener;
    public QuanBuBqAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public QuanBuBqHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pop_drawer_item, null);
        view.setOnClickListener(this);
        return new QuanBuBqHolder(view);
    }

    @Override
    public void onBindViewHolder(QuanBuBqHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.imgBq.setImageResource(EmjoyUtil.getQuanBuEmjoy()[position]);
    }

    @Override
    public int getItemCount() {
        return EmjoyUtil.getQuanBuEmjoy().length;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.onItemClick(v, (int) v.getTag());
        }
    }

    class QuanBuBqHolder extends RecyclerView.ViewHolder {
        private ImageView imgBq;

        public QuanBuBqHolder(View itemView) {
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
