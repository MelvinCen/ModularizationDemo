package com.melvin.pulltorefresh.lrecyclerview;

import android.content.Context;
import android.widget.TextView;

import com.melvin.pulltorefresh.R;
import com.melvin.pulltorefresh.lrecyclerview.base.ListBaseAdapter;
import com.melvin.pulltorefresh.lrecyclerview.base.SuperViewHolder;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/20 9:43
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/20 9:43
 * @Description ${TODO}
 */

public class MyLRecyclerviewAdapter extends ListBaseAdapter<String> {


    public MyLRecyclerviewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pull_to_refresh_lrecyclerview_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {

        TextView itemText = holder.getView(R.id.lrecyclerview_item_text);
        itemText.setText(mDataList.get(position));
    }
}
