package com.jsmy.chongyin.viewholder;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.FriendListBean;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.view.CircleImageView;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/8.
 */

public class FrendRecyclerHolder extends BaseViewHolder<FriendListBean.DataBean.ListBean> {
    private MainActivity context;
    private CircleImageView imgTx;
    private ImageView imgLove;
    private TextView tvNc;
    private TextView tvVip;
    private RelativeLayout relaVip;
    private ImageView imgVip;

    public FrendRecyclerHolder(ViewGroup parent, MainActivity context) {
        super(parent, R.layout.pop_frend_recycler_item);
        this.context = context;
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        imgTx = (CircleImageView) itemView.findViewById(R.id.img_tx);
        imgLove = (ImageView) itemView.findViewById(R.id.img_love);
        tvNc = (TextView) itemView.findViewById(R.id.tv_nc);
        tvVip = (TextView) itemView.findViewById(R.id.tv_vip);
        relaVip = (RelativeLayout) itemView.findViewById(R.id.rela_vip);
        imgVip = (ImageView) itemView.findViewById(R.id.img_vip);
    }

    @Override
    public void setData(FriendListBean.DataBean.ListBean object) {
        super.setData(object);
        tvNc.setText("" + object.getYhnc());
        NetWork.setImgGlide(context, object.getYhtx(), imgTx);
        if (object.isChosice()) {
            imgTx.setBorderColor(Color.parseColor("#5DC080"));
            tvNc.setTextColor(Color.parseColor("#5DC080"));
            tvNc.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            imgTx.setBorderColor(Color.parseColor("#8AA1EF"));
            tvNc.setTextColor(Color.parseColor("#999999"));
            tvNc.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        tvVip.setText("V" + object.getVipdj());
        if ("0".equals(object.getVipdj())) {
            relaVip.setVisibility(View.GONE);
        } else {
            relaVip.setVisibility(View.VISIBLE);
            if ("Y".equals(object.getIsgq())) {
                imgVip.setImageResource(R.mipmap.haoyouliebiao_guojidengji_xiao);
            } else {
                imgVip.setImageResource(R.mipmap.haoyouliebiao_touxianghuangguan_icon);
            }
        }
        if ("Y".equals(object.getIsax())) {
            imgLove.setVisibility(View.VISIBLE);
        } else {
            imgLove.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemViewClick(FriendListBean.DataBean.ListBean object) {
        super.onItemViewClick(object);
        MyApplication.getMyApplication().haoyouID = object.getYhid() + "";
        context.friendOldPosition = context.friendPosition;
        context.friendPosition = getLayoutPosition();
        context.setThingGone();
        context.isHyTp = true;
        context.getMyFriend(object.getYhid() + "");
        context.showFrend(true);
//        context.frendwindow.updataPosition(getLayoutPosition());
//        context.drawerDialog.updataPosition(getLayoutPosition());
//        context.drawerDialog4.updataPosition(getLayoutPosition());
        context.updataPosition(getLayoutPosition());

    }
}
