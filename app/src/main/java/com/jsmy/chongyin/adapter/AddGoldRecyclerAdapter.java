package com.jsmy.chongyin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.AddGoldActivity;
import com.jsmy.chongyin.bean.GoldBean;
import com.jsmy.chongyin.utils.AtyTag;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class AddGoldRecyclerAdapter extends RecyclerView.Adapter<AddGoldRecyclerAdapter.MyViewHolder> {
    private AddGoldActivity context;
    private LayoutInflater inflater;
    private List<GoldBean> list;
    //两个final int类型表示ViewType的两种类型
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 1;
    private final int HEAD_TYPE = 2;
    //最大item数加1，最后一个为foot
    private int max_count;

    public AddGoldRecyclerAdapter(AddGoldActivity context, List<GoldBean> list) {
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
        if (position == max_count - 1) {
            return FOOT_TYPE;
        }
        return NORMAL_TYPE;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.activity_add_gold_item, null);
        View foot = inflater.inflate(R.layout.recycler_footview, null);
        View head = inflater.inflate(R.layout.recycler_headview, null);
        if (viewType == HEAD_TYPE) {
            return new MyViewHolder(head, HEAD_TYPE);
        } else if (viewType == FOOT_TYPE) {
            return new MyViewHolder(foot, FOOT_TYPE);
        } else {
            return new MyViewHolder(view, NORMAL_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (getItemViewType(position) == FOOT_TYPE || getItemViewType(position) == HEAD_TYPE) {
            return;
        }
        holder.imgGold.setImageResource(list.get(position-1).getRosuceId());
        holder.nomalGold.setText("￥" + list.get(position-1).getNormalGold());
        holder.vipGold.setText("VIP￥" + list.get(position-1).getVipGold());
        holder.relaGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.payType = "yuanbao";
                switch (position-1) {
                    case 0:
                        context.moon = "11";
                        context.price = "3.00";
                        break;
                    case 1:
                        context.moon = "55";
                        context.price = "14.99";
                        break;
                    case 2:
                        context.moon = "120";
                        context.price = "29.99";
                        break;
                    case 3:
                        context.moon = "250";
                        context.price = "59.99";
                        break;
                    case 4:
                        context.moon = "600";
                        context.price = "99.99";
                        break;
                    case 5:
                        context.moon = "120";
                        context.price = "14.99";
                        break;
                }
                context.finish();
                context.gotoSomeActivity(context, AtyTag.ATY_PayVIP, true);

            }
        });
    }

    @Override
    public int getItemCount() {
        return max_count;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgGold;
        private TextView nomalGold;
        private TextView vipGold;
        private RelativeLayout relaGold;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == NORMAL_TYPE) {
                imgGold = (ImageView) itemView.findViewById(R.id.img_gold);
                nomalGold = (TextView) itemView.findViewById(R.id.nomal_gold);
                vipGold = (TextView) itemView.findViewById(R.id.vip_gold);
                relaGold = (RelativeLayout) itemView.findViewById(R.id.rela_gold);
            } else {

            }

        }
    }
}
