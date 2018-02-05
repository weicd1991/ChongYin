package com.jsmy.chongyin.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.AddFoodActivity;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.bean.ShiWuFeiLeiBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class AddFoodRecyclerAdapter extends RecyclerView.Adapter<AddFoodRecyclerAdapter.MyViewHolder> {
    private AddFoodActivity context;
    private LayoutInflater inflater;
    private List<ShiWuFeiLeiBean.DataBean.ListBean> list;
    private OnItemClickListener mOnItemClickListener;

    //两个final int类型表示ViewType的两种类型
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 1;
    //最大item数加1，最后一个为foot
    private int max_count;

    public AddFoodRecyclerAdapter(AddFoodActivity context, List<ShiWuFeiLeiBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.max_count = list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOT_TYPE;
        }
        return NORMAL_TYPE;
    }

    public void setDate(List<ShiWuFeiLeiBean.DataBean.ListBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_add_food_item, null);
        View foot = inflater.inflate(R.layout.recycler_footview, null);
        foot.findViewById(R.id.rela_foot).setBackgroundColor(Color.parseColor("#00ffffff"));
        if (viewType == FOOT_TYPE) {
            return new MyViewHolder(foot, FOOT_TYPE);
        } else {
            return new MyViewHolder(view, NORMAL_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (getItemViewType(position) == FOOT_TYPE) {
            return;
        }
        if (position % 2 == 0) {
            holder.tvRight.setVisibility(View.VISIBLE);
            holder.tvBottm.setVisibility(View.VISIBLE);
        } else {
            holder.tvBottm.setVisibility(View.VISIBLE);
        }
        NetWork.setImgGlide(context, list.get(position).getTx(), holder.imgFood);
        holder.tvName.setText(list.get(position).getMc());
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return max_count;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFood;
        private TextView tvName;
        private TextView tvLeft;
        private TextView tvBottm;
        private TextView tvRight;


        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == NORMAL_TYPE) {
                imgFood = (ImageView) itemView.findViewById(R.id.img_food);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
                tvLeft = (TextView) itemView.findViewById(R.id.tv_left);
                tvBottm = (TextView) itemView.findViewById(R.id.tv_bottm);
                tvRight = (TextView) itemView.findViewById(R.id.tv_right);
            } else {

            }
        }
    }
}
