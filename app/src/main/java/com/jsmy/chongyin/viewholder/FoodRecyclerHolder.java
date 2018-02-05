package com.jsmy.chongyin.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.MyFoodBean;
import com.jsmy.chongyin.view.CircleImageView;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/13.
 */

public class FoodRecyclerHolder extends BaseViewHolder<MyFoodBean.DataBean.ListBean> {
    private MainActivity context;
    private CircleImageView imgTx;
    private ImageView imgVip;
    private ImageView imgLove;
    private TextView tvNc;

    public FoodRecyclerHolder(ViewGroup parent, MainActivity context) {
        super(parent, R.layout.pop_food_recycler_item);
        this.context = context;
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        imgTx = (CircleImageView) itemView.findViewById(R.id.img_tx);
        imgVip = (ImageView) itemView.findViewById(R.id.img_vip);
        imgLove = (ImageView) itemView.findViewById(R.id.img_love);
        tvNc = (TextView) itemView.findViewById(R.id.tv_nc);
    }

    @Override
    public void setData(MyFoodBean.DataBean.ListBean object) {
        super.setData(object);

        NetWork.setImgGlide(context, object.getSptx(), imgTx);
        tvNc.setText("+" + object.getSpcns());
    }

    @Override
    public void onItemViewClick(MyFoodBean.DataBean.ListBean object) {
        super.onItemViewClick(object);

        if (!"".equals(MyApplication.getMyApplication().userInfo.getPetid()) && object.getSpcns() > 0) {
            Double d = Double.parseDouble(MyApplication.getMyApplication().userInfo.getBscns());
            int i = d.compareTo(100.0);
            boolean b;
            if (i < 0) {
                b = true;
            } else {
                b = false;
            }
            if (context.eat && b) {
//                context.eatFood(context.foodwindow.activityFrend, imgTx, context.foodwindow.imgMosce, object.getSpid() + "");
//                context.eatFood(context.drawerDialog.relaDrawer, imgTx, context.drawerDialog.imgMosce, object.getSpid() + "");
//                context.eatFood(context.drawerDialog5.relaDrawer, imgTx, context.drawerDialog5.imgMosce, object.getSpid() + "");
                context.eatFood(context.activityMain, imgTx, context.imgMosce, object.getSpid() + "");
                context.eat = false;
            }
        } else {

        }
    }
}
