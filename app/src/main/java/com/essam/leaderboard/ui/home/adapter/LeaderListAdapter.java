package com.essam.leaderboard.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.essam.leaderboard.R;
import com.essam.leaderboard.model.Leader;

import java.util.List;

public class LeaderListAdapter extends RecyclerView.Adapter<LeaderListAdapter.ViewHolder> {

    private List<Leader> mLeaderList;
    private Context mContext;
    private OnItemClickedListener mOnItemClickedListener;

    public LeaderListAdapter(Context context, OnItemClickedListener onItemClickedListener) {
        mContext = context;
        mOnItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener{
        void onItemClicked(Leader leader);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.leader_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mLeaderList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mLeaderList != null){
            return mLeaderList.size();
        }
        else return 0;
    }

    public void setItems(List<Leader> leaders){
        mLeaderList = leaders;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTv, mProgressTextView;
        private ImageView mBadgeIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.tv_name);
            mProgressTextView= itemView.findViewById(R.id.tv_progress);
            mBadgeIv= itemView.findViewById(R.id.iv_badge);

            itemView.setOnClickListener(this);
        }

        void bind(Leader leader){
            mNameTv.setText(leader.getName());
            mProgressTextView.setText(leader.getProgress());
            Glide.with(mContext).load(leader.getBadgeUrl()).into(mBadgeIv);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickedListener.onItemClicked(mLeaderList.get(getAdapterPosition()));
        }
    }
}
