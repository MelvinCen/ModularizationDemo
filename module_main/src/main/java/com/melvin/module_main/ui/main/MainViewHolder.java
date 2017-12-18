package com.melvin.module_main.ui.main;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import test.melvin.module_main.R;


/**
 * @Author Melvin
 * @CreatedDate 2017/10/16 15:01
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/16 15:01
 * @Description ${TODO}
 */

public class MainViewHolder extends RecyclerView.ViewHolder {

    TextView tvItemText;
    CardView mCardView;
    public MainViewHolder(View itemView) {
        super(itemView);
        tvItemText = (TextView) itemView.findViewById(R.id.tv_recyclerview_item);
        mCardView = (CardView) itemView.findViewById(R.id.main_cardview);
    }

}
