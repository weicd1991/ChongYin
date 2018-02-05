package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.BaseActivity;
import com.jsmy.chongyin.activity.NewFrendActivity;
import com.jsmy.chongyin.bean.NewFriendBena;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.view.CircleImageView;

import cn.lemon.view.adapter.*;

/**
 * Created by Administrator on 2017/8/1.
 */

public class NewFrenRecyclerAdapter2 extends RecyclerAdapter<NewFriendBena.DataBean.ListBean> {
    private NewFrendActivity context;
    public NewFrenRecyclerAdapter2(NewFrendActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<NewFriendBena.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new NewFrenHolder(parent,context);
    }
    class NewFrenHolder extends BaseViewHolder<NewFriendBena.DataBean.ListBean>{
        private NewFrendActivity context;
        private CircleImageView imgTx;
        private RelativeLayout relaVip;
        private TextView tvVip;
        private TextView tvName;
        private TextView tvId;
        private RelativeLayout relaBtn;
        private TextView btnAgree;
        private TextView btnRefuse;
        private TextView tvIsAgree;
        private ImageView imgVip;
        public NewFrenHolder(ViewGroup parent, NewFrendActivity context) {
            super(parent, R.layout.activity_new_frend_item);
            this.context = context;
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            imgTx = (CircleImageView) findViewById(R.id.img_tx);
            relaVip = (RelativeLayout) findViewById(R.id.rela_vip);
            tvVip = (TextView) findViewById(R.id.tv_vip);
            tvName = (TextView) findViewById(R.id.tv_name);
            tvId = (TextView) findViewById(R.id.tv_id);
            relaBtn = (RelativeLayout) findViewById(R.id.rela_btn);
            btnAgree = (TextView) findViewById(R.id.btn_agree);
            btnRefuse = (TextView) findViewById(R.id.btn_refuse);
            tvIsAgree = (TextView) findViewById(R.id.tv_isAgree);
            imgVip = (ImageView) findViewById(R.id.img_vip);
        }

        @Override
        public void setData(final NewFriendBena.DataBean.ListBean object) {
            super.setData(object);
            tvVip.setText("V" + object.getVipdj());
            if ("".equals(object.getVipdj()) || 0 == Integer.parseInt(object.getVipdj()) ) {
                relaVip.setVisibility(View.GONE);
            } else {
                relaVip.setVisibility(View.VISIBLE);
                if ("Y".equals(object.getIsgq())){
                    imgVip.setImageResource(R.mipmap.haoyouliebiao_guojidengji_xiao);
                }else {
                    imgVip.setImageResource(R.mipmap.haoyouliebiao_touxianghuangguan_icon);
                }
            }
            NetWork.setImgGlide(context, object.getTx(), imgTx);
            tvName.setText(object.getNc());
            tvId.setText("ID:" + object.getYhids());
            switch (object.getZt()) {
                case BaseActivity.zero:
                    relaBtn.setVisibility(View.VISIBLE);
                    tvIsAgree.setVisibility(View.GONE);
                    break;
                case BaseActivity.one:
                    tvIsAgree.setText("已同意");
                    break;
                case BaseActivity.two:
                    tvIsAgree.setText("已拒绝");
                    break;
                case BaseActivity.three:
                    tvIsAgree.setText("等待验证");
                    break;
            }
            imgTx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.type = "sreach";
                    context.zt = object.getZt();
                    context.friendID = object.getYhids() + "";
                    context.gotoSomeActivity(context, AtyTag.ATY_AddNewFrend, true);
                }
            });

            btnAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.agreeFrend(object.getYhids() + "");
                }
            });

            btnRefuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.refuseFrend(object.getYhids() + "");
                }
            });
        }

        @Override
        public void onItemViewClick(NewFriendBena.DataBean.ListBean object) {
            super.onItemViewClick(object);

        }

    }
}
