package com.jsmy.chongyin.adapter;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.AddFoodPayActivity;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.ShiWuBean;
import com.jsmy.chongyin.contents.DowloadEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class AddFoodPayRecyclerAdapter extends RecyclerView.Adapter<AddFoodPayRecyclerAdapter.MyViewHolder> {
    private AddFoodPayActivity context;
    private LayoutInflater inflater;
    private List<ShiWuBean.DataBean.ListBean> list;
    private OnItemClickListener mOnItemClickListener;
    //两个final int类型表示ViewType的两种类型
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 1;
    private final int HEAD_TYPE = 1;
    //最大item数加1，最后一个为foot
    private int max_count;

    public AddFoodPayRecyclerAdapter(AddFoodPayActivity context, List<ShiWuBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.max_count = list.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD_TYPE;
        }
        if (position > list.size()) {
            return FOOT_TYPE;
        }
        return NORMAL_TYPE;
    }

    public void setDate(List<ShiWuBean.DataBean.ListBean> listbean, int position) {
        list.clear();
        list.addAll(listbean);
        notifyItemRangeChanged(position + 1, max_count);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_add_food_pay_item, null);
        View foot = inflater.inflate(R.layout.recycler_footview_pay, null);
        View head = inflater.inflate(R.layout.recycler_headview, null);
        foot.setBackgroundResource(R.drawable.base_rectangle3_2);
        if (viewType == HEAD_TYPE) {
            return new MyViewHolder(head, HEAD_TYPE);
        } else if (viewType == FOOT_TYPE) {
            return new MyViewHolder(foot, FOOT_TYPE);
        } else {
            return new MyViewHolder(view, NORMAL_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (getItemViewType(position) == FOOT_TYPE || getItemViewType(position) == HEAD_TYPE) {
            return;
        }
        if (position == 1) {
            holder.relaGroup.setBackgroundResource(R.drawable.base_rectangle3);
        } else {
            holder.relaGroup.setBackgroundResource(R.drawable.base_rectangle3_2);
        }
        NetWork.setImgGlide(context, list.get(position - 1).getSptx(), holder.imgFood1);
        if (list.get(position - 1).getCns() > 1) {
            holder.tvFood1.setText("x" + (list.get(position - 1).getCns() - 1));
        } else {
            holder.tvFood1.setText("");
        }
        holder.tvFood2.setText("+" + list.get(position - 1).getJbsd() + "%");
        holder.tvGold1.setText("x" + list.get(position - 1).getYbcns());
        holder.tvGold2.setText("x" + list.get(position - 1).getVipybcns());
        holder.relaYuanbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yuanbao;
                if ("0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) || "Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                    yuanbao = list.get(position - 1).getYbcns() + "";
                } else {
                    yuanbao = list.get(position - 1).getVipybcns() + "";
                }
                context.showDialogOne(list.get(position - 1).getSpid() + "", list.get(position - 1).getCns() + "", yuanbao + "", position - 1);
            }
        });
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
        private ImageView imgFood1;
        private TextView tvFood1;
        private ImageView imgFood2;
        private TextView tvFood2;
        private TextView tvGold1;
        private TextView tvGold2;
        private RelativeLayout relaYuanbao;
        private RelativeLayout relaGroup;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == NORMAL_TYPE) {
                imgFood1 = (ImageView) itemView.findViewById(R.id.img_food1);
                tvFood1 = (TextView) itemView.findViewById(R.id.tv_food1);
                imgFood2 = (ImageView) itemView.findViewById(R.id.img_food2);
                tvFood2 = (TextView) itemView.findViewById(R.id.tv_food2);
                tvGold1 = (TextView) itemView.findViewById(R.id.tv_gold1);
                tvGold2 = (TextView) itemView.findViewById(R.id.tv_gold2);
                relaYuanbao = (RelativeLayout) itemView.findViewById(R.id.rela_yuanbao);
                relaGroup = (RelativeLayout) itemView.findViewById(R.id.rela_group);
            } else {

            }
        }
    }


}
