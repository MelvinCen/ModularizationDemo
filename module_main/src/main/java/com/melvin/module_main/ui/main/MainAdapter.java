package com.melvin.module_main.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mzule.activityrouter.router.Routers;
import com.melvin.melvincommon.utils.StringUtil;
import com.melvin.melvincommon.utils.ToastUtils;

import test.melvin.module_main.R;


/**
 * @Author Melvin
 * @CreatedDate 2017/10/16 15:01
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/16 15:01
 * @Description ${TODO}
 */

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private Context mContext;
    private String[] mDatas;

    public MainAdapter(Context context,String[] mComingDatas) {
        mContext = context;
        mDatas = mComingDatas;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MainViewHolder mainViewHolder = new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.module_main_list_item, parent, false));

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.tvItemText.setText(mDatas[position]);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe("position " + position + " 被点击,内容是" +  mDatas[position]);
                String currentItemStr = mDatas[position];
                if (StringUtil.equals(currentItemStr, mContext.getString(R.string.module_main_pull_to_refresh))) {
                    Routers.open(mContext,"melvin://PullToRefreshMainActivity");
                } else if (StringUtil.equals(currentItemStr, mContext.getString(R.string.module_main_tab_shift))){
                    Routers.open(mContext,"melvin://MainTabShiftActivity");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.length;
    }


}
