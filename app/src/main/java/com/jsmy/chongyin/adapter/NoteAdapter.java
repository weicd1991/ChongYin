package com.jsmy.chongyin.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.NoteActivity;
import com.jsmy.chongyin.activity.NoteEditActivity;
import com.jsmy.chongyin.bean.NoteListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private NoteActivity context;
    private List<NoteListBean.DataBean> list;
    private LayoutInflater inflater;

    public NoteAdapter(NoteActivity context, List<NoteListBean.DataBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NoteHolder holder = new NoteHolder(inflater.inflate(R.layout.activity_note_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, final int position) {
        holder.tvData.setText(list.get(position).getNr());
        holder.tvTime.setText(list.get(position).getSj());
        if (null != list.get(position).getTxsj() && !"".equals(list.get(position).getTxsj())) {
            holder.imgClock.setVisibility(View.VISIBLE);
            holder.tvClock.setText(list.get(position).getTxsj().substring(5,11)+list.get(position).getTxsj().substring(14)+ "提醒");
            if (context.isClocked(list.get(position).getTxsj())) {
                holder.imgClock.setImageResource(R.mipmap.clock_y);
                holder.tvClock.setTextColor(Color.parseColor("#DCC22D"));
                context.setClock(list.get(position).getTxsj(),
                        list.get(position).getNoteId(),
                        list.get(position).getNr());
            } else {
                holder.imgClock.setImageResource(R.mipmap.clock_g);
                holder.tvClock.setTextColor(Color.parseColor("#ACACAC"));
            }
        } else {
            holder.imgClock.setVisibility(View.GONE);
            holder.tvClock.setText("");
        }
        holder.relaBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoteEditActivity.class);
                intent.putExtra("noteId", list.get(position).getNoteId());
                context.startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvData;
        private TextView tvTime;
        private TextView tvClock;
        private ImageView imgClock;
        private RelativeLayout relaBg;

        public NoteHolder(View itemView) {
            super(itemView);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvClock = (TextView) itemView.findViewById(R.id.tv_clock);
            imgClock = (ImageView) itemView.findViewById(R.id.img_clock);
            relaBg = (RelativeLayout) itemView.findViewById(R.id.rela_bg);
        }
    }
}
