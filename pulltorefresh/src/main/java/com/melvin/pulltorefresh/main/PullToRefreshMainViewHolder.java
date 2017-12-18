package com.melvin.pulltorefresh.main;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.melvin.pulltorefresh.R;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/16 15:01
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/16 15:01
 * @Description ${TODO}
 */

public class PullToRefreshMainViewHolder extends RecyclerView.ViewHolder {

    TextView tvItemText;
    CardView mCardView;
    public PullToRefreshMainViewHolder(View itemView) {
        super(itemView);
        tvItemText = (TextView) itemView.findViewById(R.id.tv_recyclerview_item_pull_to_refresh);
        mCardView = (CardView) itemView.findViewById(R.id.main_cardview_pull_to_refresh);
    }

}
