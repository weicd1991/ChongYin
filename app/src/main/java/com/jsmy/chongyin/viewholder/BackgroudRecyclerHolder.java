package com.jsmy.chongyin.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.BJTPBean;
import com.jsmy.chongyin.bean.FJDRBean;
import com.jsmy.chongyin.utils.AtyTag;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/7.
 */

public class BackgroudRecyclerHolder extends BaseViewHolder<BJTPBean.DataBean.ListBean> {
    private MainActivity context;
    private ImageView imgBack;

    public BackgroudRecyclerHolder(ViewGroup parent, MainActivity context) {
        super(parent, R.layout.pop_back_recycler_item);
        this.context = context;
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        imgBack = (ImageView) itemView.findViewById(R.id.img_back);
    }

    @Override
    public void setData(final BJTPBean.DataBean.ListBean object) {
        super.setData(object);
        NetWork.setImgGlide(context, object.getTp(), imgBack);
        imgBack.setScaleType(ImageView.ScaleType.FIT_XY);
        if (MyApplication.getMyApplication().userInfo.getBjtpid().equals(object.getId() + "")) {
            imgBack.setBackgroundResource(R.drawable.bjline);
        } else {
            imgBack.setBackgroundResource(R.drawable.bjline2);
        }

        switch (getLayoutPosition()) {
            case 0:

                break;

            case 1:
                break;

            case 2:
                break;

            default:
                imgBack.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        context.showDeletBakDialog(object.getId() + "");
                        return true;
                    }
                });
                break;
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.setBJTP(object.getId() + "", getLayoutPosition());
            }
        });
    }


    @Override
    public void onItemViewClick(BJTPBean.DataBean.ListBean object) {
        super.onItemViewClick(object);

    }

}
