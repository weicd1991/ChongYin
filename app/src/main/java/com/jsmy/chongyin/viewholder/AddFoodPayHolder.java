package com.jsmy.chongyin.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.AddFoodPayActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.ShiWuBean;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AddFoodPayHolder extends BaseViewHolder<ShiWuBean.DataBean.ListBean> {
    private AddFoodPayActivity context;
    private ImageView imgFood1;
    private TextView tvFood1;
    private ImageView imgFood2;
    private TextView tvFood2;
    private TextView tvGold1;
    private TextView tvGold2;
    private RelativeLayout relaYuanbao;
    private RelativeLayout relaGroup;

    public AddFoodPayHolder(ViewGroup parent, AddFoodPayActivity context) {
        super(parent, R.layout.activity_add_food_pay_item);
        this.context = context;
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        imgFood1 = (ImageView) itemView.findViewById(R.id.img_food1);
        tvFood1 = (TextView) itemView.findViewById(R.id.tv_food1);
        imgFood2 = (ImageView) itemView.findViewById(R.id.img_food2);
        tvFood2 = (TextView) itemView.findViewById(R.id.tv_food2);
        tvGold1 = (TextView) itemView.findViewById(R.id.tv_gold1);
        tvGold2 = (TextView) itemView.findViewById(R.id.tv_gold2);
        relaYuanbao = (RelativeLayout) itemView.findViewById(R.id.rela_yuanbao);
        relaGroup = (RelativeLayout) itemView.findViewById(R.id.rela_group);
    }

    @Override
    public void setData(final ShiWuBean.DataBean.ListBean object) {
        super.setData(object);
        if (getLayoutPosition() == 1) {
            relaGroup.setBackgroundResource(R.drawable.base_rectangle3);
        } else {
            relaGroup.setBackgroundResource(R.drawable.base_rectangle3_2);
        }
        NetWork.setImgGlide(context, object.getSptx(), imgFood1);
        if (object.getCns() > 1) {
            tvFood1.setText("x" + (object.getCns() - 1));
        } else {
            tvFood1.setText("");
        }
        tvFood2.setText("+" + object.getJbsd() + "%");
        tvGold1.setText("x" + object.getYbcns());
        tvGold2.setText("x" + object.getVipybcns());
        relaYuanbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yuanbao;
                if ("0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) || "Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                    yuanbao = object.getYbcns() + "";
                } else {
                    yuanbao = object.getVipybcns() + "";
                }
                context.showDialogOne(object.getSpid() + "", object.getCns() + "", yuanbao + "", getLayoutPosition() - 1);
            }
        });
    }

    @Override
    public void onItemViewClick(ShiWuBean.DataBean.ListBean object) {
        super.onItemViewClick(object);
    }
}
