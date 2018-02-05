package com.jsmy.chongyin.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.LoveRankActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.LoveRankBean;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.view.CircleImageView;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/5/17.
 */

public class LoveRankRecyclerHolder extends BaseViewHolder<LoveRankBean.DataBean.ListBean> {
    private LoveRankActivity context;

    private CircleImageView imgTx;
    private RelativeLayout relaVip;
    private TextView tvVip;
    private ImageView imgNumber;
    private TextView tvMath;
    private TextView tvName;
    private ImageView imgLove;
    private TextView tvLoveNum;
    private TextView tvLoveLeftNum;
    private TextView tvLoveall;
    private ImageView imgVip;

    public LoveRankRecyclerHolder(ViewGroup parent, LoveRankActivity context) {
        super(parent, R.layout.activity_love_rank_item);
        this.context = context;
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        imgTx = (CircleImageView) findViewById(R.id.img_tx);
        relaVip = (RelativeLayout) findViewById(R.id.rela_vip);
        tvVip = (TextView) findViewById(R.id.tv_vip);
        imgNumber = (ImageView) findViewById(R.id.img_number);
        tvMath = (TextView) findViewById(R.id.tv_math);
        tvName = (TextView) findViewById(R.id.tv_name);
        imgLove = (ImageView) findViewById(R.id.img_love);
        tvLoveNum = (TextView) findViewById(R.id.tv_love_num);
        tvLoveLeftNum = (TextView) findViewById(R.id.tv_love_left_num);
        tvLoveall = (TextView) findViewById(R.id.tv_loveall);
        imgVip = (ImageView) findViewById(R.id.img_vip);
    }

    @Override
    public void setData(final LoveRankBean.DataBean.ListBean object) {
        super.setData(object);
        if ("0".equals(object.getVipdj())) {
            relaVip.setVisibility(View.INVISIBLE);
        } else {
            relaVip.setVisibility(View.VISIBLE);
            if ("Y".equals(object.getIsgq())) {
                imgVip.setImageResource(R.mipmap.haoyouliebiao_guojidengji_xiao);
            } else {
                imgVip.setImageResource(R.mipmap.haoyouliebiao_touxianghuangguan_icon);
            }
        }
        tvVip.setText("V" + object.getVipdj());
        switch (getLayoutPosition()) {
            case 1:
                NetWork.setImgGlide(context, object.getTx(), imgTx);
                tvMath.setVisibility(View.INVISIBLE);
                imgNumber.setVisibility(View.VISIBLE);
                imgNumber.setImageResource(R.mipmap.aixinpaihangbang_no1);
                tvName.setText("" + object.getNc());
                tvLoveall.setText("爱心总数：" + object.getZaxcns());
                tvLoveLeftNum.setText("" + object.getAxcns());
                if (!"".equals(object.getYhsax()) && 0 != Integer.parseInt(object.getYhsax())) {
                    tvLoveNum.setVisibility(View.VISIBLE);
                    tvLoveNum.setText("" + object.getYhsax());
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                } else {
                    tvLoveNum.setVisibility(View.INVISIBLE);
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_weixuanzhong);
                }
                break;
            case 2:
                NetWork.setImgGlide(context, object.getTx(), imgTx);
                tvMath.setVisibility(View.INVISIBLE);
                imgNumber.setVisibility(View.VISIBLE);
                imgNumber.setImageResource(R.mipmap.aixinpaihangbang_no2);
                tvName.setText("" + object.getNc());
                tvLoveall.setText("爱心总数：" + object.getZaxcns());
                tvLoveLeftNum.setText("" + object.getAxcns());
                if (!"".equals(object.getYhsax()) && 0 != Integer.parseInt(object.getYhsax())) {
                    tvLoveNum.setVisibility(View.VISIBLE);
                    tvLoveNum.setText("" + object.getYhsax());
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                } else {
                    tvLoveNum.setVisibility(View.INVISIBLE);
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_weixuanzhong);
                }
                break;
            case 3:
                NetWork.setImgGlide(context, object.getTx(), imgTx);
                tvMath.setVisibility(View.INVISIBLE);
                imgNumber.setVisibility(View.VISIBLE);
                imgNumber.setImageResource(R.mipmap.aixinpaihangbang_no3);
                tvName.setText("" + object.getNc());
                tvLoveall.setText("爱心总数：" + object.getZaxcns());
                tvLoveLeftNum.setText("" + object.getAxcns());
                if (!"".equals(object.getYhsax()) && 0 != Integer.parseInt(object.getYhsax())) {
                    tvLoveNum.setVisibility(View.VISIBLE);
                    tvLoveNum.setText("" + object.getYhsax());
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                } else {
                    tvLoveNum.setVisibility(View.INVISIBLE);
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_weixuanzhong);
                }
                break;
            default:
                NetWork.setImgGlide(context, object.getTx(), imgTx);
                tvMath.setVisibility(View.VISIBLE);
                tvMath.setText(object.getXh() + "");
                imgNumber.setVisibility(View.INVISIBLE);
                tvName.setText("" + object.getNc());
                tvLoveall.setText("爱心总数：" + object.getZaxcns());
                tvLoveLeftNum.setText("" + object.getAxcns());
                if (!"".equals(object.getYhsax()) && 0 != Integer.parseInt(object.getYhsax())) {
                    tvLoveNum.setVisibility(View.VISIBLE);
                    tvLoveNum.setText("" + object.getYhsax());
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                } else {
                    tvLoveNum.setVisibility(View.INVISIBLE);
                    imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_weixuanzhong);
                }
                break;
        }

        imgTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.goToFriend(object.getYhid() + "");
            }
        });
        imgLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(context.jrksax) > 0)
                    context.setLove(object.getYhid() + "");
            }
        });
    }

}
