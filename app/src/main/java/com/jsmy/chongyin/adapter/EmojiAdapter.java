package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jsmy.chongyin.R;

/**
 * Created by Administrator on 2017/12/20.
 */

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiHolder> implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    private MyItemClickListener mListener;
    private int[] emoji;

    public EmojiAdapter(Context context, int[] emoji) {
        this.context = context;
        this.emoji = emoji;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public EmojiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pop_drawer_item,null);
        view.setOnClickListener(this);
        return new EmojiHolder(view);
    }

    @Override
    public void onBindViewHolder(EmojiHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.imgBq.setImageResource(emoji[position]);
    }

    @Override
    public int getItemCount() {
        return emoji.length;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.onItemClick(v, (int) v.getTag());
        }
    }

    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }

    class EmojiHolder extends RecyclerView.ViewHolder {
        private ImageView imgBq;

        public EmojiHolder(View itemView) {
            super(itemView);
            imgBq = (ImageView) itemView.findViewById(R.id.img_bq);
        }
    }
}
