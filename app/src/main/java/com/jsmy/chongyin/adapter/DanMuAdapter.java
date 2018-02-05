package com.jsmy.chongyin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.DanMuLiuYanActivity;
import com.jsmy.chongyin.activity.NearbyActivity;
import com.jsmy.chongyin.bean.DanmuBean;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.ExpressionUtil;
import com.jsmy.chongyin.utils.TimeUtils;
import com.jsmy.chongyin.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class DanMuAdapter extends RecyclerView.Adapter<DanMuAdapter.NearbyHolder> {
    private DanMuLiuYanActivity context;
    private List<DanmuBean.DataBean> list;
    private LayoutInflater inflater;

    public DanMuAdapter(DanMuLiuYanActivity context, List<DanmuBean.DataBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NearbyHolder holder = new NearbyHolder(inflater.inflate(R.layout.activity_dan_mu_liu_yan_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(NearbyHolder holder, final int position) {
//        Glide.with(context).load(list.get(position).getTx()).into(holder.imgTx);
        NetWork.setImgGlide(context,list.get(position).getTx(),holder.imgTx);
        holder.tvName.setText(list.get(position).getNc());
        holder.tvOli.setText(TimeUtils.getCHTime(list.get(position).getSj()));
        holder.tvSign.setText("");
        holder.tvSign.getText().insert(holder.tvSign.getSelectionStart(), ExpressionUtil.getExpressionString(context, list.get(position).getNr(), "f[0-9]{3}"));
        holder.relaBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.zt = context.zero;
                context.type = "sreach";
                context.friendID = list.get(position).getLyrId();
                context.gotoSomeActivity(context, AtyTag.ATY_AddNewFrend, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NearbyHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgTx;
        private TextView tvName;
        private TextView tvOli;
        private EditText tvSign;
        private RelativeLayout relaBg;

        public NearbyHolder(View itemView) {
            super(itemView);
            imgTx = (CircleImageView) itemView.findViewById(R.id.img_tx);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvOli = (TextView) itemView.findViewById(R.id.tv_oli);
            tvSign = (EditText) itemView.findViewById(R.id.tv_sign);
            relaBg = (RelativeLayout) itemView.findViewById(R.id.rela_bg);
        }
    }

}
