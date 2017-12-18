package com.melvin.pulltorefresh.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melvin.melvincommon.utils.StringUtil;
import com.melvin.melvincommon.utils.ToastUtils;
import com.melvin.pulltorefresh.R;
import com.melvin.pulltorefresh.lrecyclerview.LRecyclerviewActivity;
import com.melvin.pulltorefresh.swiperefresh.ui.SwipRefreshActivity;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/16 15:01
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/16 15:01
 * @Description ${TODO}
 */

public class PullToRefreshMainAdapter extends RecyclerView.Adapter<PullToRefreshMainViewHolder> {

    private Context mContext;
    private String[] mDatas;

    public PullToRefreshMainAdapter(Context context, String[] mComingDatas) {
        mContext = context;
        mDatas = mComingDatas;
    }

    @Override
    public PullToRefreshMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PullToRefreshMainViewHolder mainViewHolder = new PullToRefreshMainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.pull_to_refresh_list_item, parent, false));

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(PullToRefreshMainViewHolder holder, final int position) {
        holder.tvItemText.setText(mDatas[position]);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe("position " + position + " 被点击,内容是" +  mDatas[position]);
                String currentItemStr = mDatas[position];
                if (StringUtil.equals(currentItemStr, mContext.getString(R.string.pull_to_refresh_xrecyclerview))) {
//                    mContext.startActivity(new Intent(mContext, XRecyclerviewActivity.class));
                } else if (StringUtil.equals(currentItemStr, mContext.getString(R.string.pull_to_refresh_lrecyclerview))){
                    mContext.startActivity(new Intent(mContext, LRecyclerviewActivity.class));
                } else if (StringUtil.equals(currentItemStr, mContext.getString(R.string.pull_to_refresh_swiperefresh))) {
                    mContext.startActivity(new Intent(mContext, SwipRefreshActivity.class));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.length;
    }


}
