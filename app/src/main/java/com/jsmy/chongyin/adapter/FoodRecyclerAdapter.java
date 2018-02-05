package com.jsmy.chongyin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.bean.MyFoodBean;
import com.jsmy.chongyin.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.MyViewHolder> {
    private MainActivity context;
    private LayoutInflater inflater;
    private List<MyFoodBean.DataBean.ListBean> list;
    private OnItemClickListener mOnItemClickListener;

    public FoodRecyclerAdapter(MainActivity context, List<MyFoodBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public void setDate(List<MyFoodBean.DataBean.ListBean> listBeen){
        list.clear();
        list.addAll(listBeen);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pop_food_recycler_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position == list.size()) {
            holder.imgTx.setImageResource(R.mipmap.beijingtu_tianjiabeijing_icon);
            holder.tvNc.setText("");

        } else {
            NetWork.setImgGlide(context, list.get(position).getSptx(), holder.imgTx);
            holder.tvNc.setText("+" + list.get(position).getSpcns());
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(position,holder.imgTx);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public interface OnItemClickListener {
        void onClick(int position,ImageView view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgTx;
        private ImageView imgVip;
        private ImageView imgLove;
        private TextView tvNc;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgTx = (CircleImageView) itemView.findViewById(R.id.img_tx);
            imgVip = (ImageView) itemView.findViewById(R.id.img_vip);
            imgLove = (ImageView) itemView.findViewById(R.id.img_love);
            tvNc = (TextView) itemView.findViewById(R.id.tv_nc);
        }
    }

}
