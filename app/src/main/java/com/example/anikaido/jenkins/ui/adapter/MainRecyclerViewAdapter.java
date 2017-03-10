package com.example.anikaido.jenkins.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anikaido.jenkins.R;
import com.example.anikaido.jenkins.domain.JobStatus;

import java.util.List;

/**
 * Created by anikaido on 2017/02/26.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public List<JobStatus> mData;
    private OnRecyclerListener mListener;

    public interface OnRecyclerListener {
        void onRecyclerClicked(View v, int position);
    }

    public MainRecyclerViewAdapter(Context context, List<JobStatus> data, OnRecyclerListener listener) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mData = data;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mData != null && mData.size() > 0 && mData.get(position) != null) {
            holder.mTextView.setText(mData.get(position).getName());

            if (mData.get(position).getColor().equals("blue")) {
                holder.mView.setBackgroundColor(0xff3264c8);
            } else if (mData.get(position).getColor().equals("red")) {
                holder.mView.setBackgroundColor(0xfff03434);
            } else {
                holder.mView.setBackgroundColor(0xffa0a0a0);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRecyclerClicked(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }

        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_home_text);
            mView = itemView.findViewById(R.id.list_item_main_view);
        }
    }
}
