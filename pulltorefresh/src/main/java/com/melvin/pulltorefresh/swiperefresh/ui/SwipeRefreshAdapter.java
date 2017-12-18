package com.melvin.pulltorefresh.swiperefresh.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.melvin.pulltorefresh.R;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/23 14:49
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/23 14:49
 * @Description ${TODO}
 */

public class SwipeRefreshAdapter extends SuperAdapter<String> {

    public SwipeRefreshAdapter(Context context, List<String> items, @LayoutRes int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, String item) {
        holder.setText(R.id.tv_swipe_item,item);
    }
}
