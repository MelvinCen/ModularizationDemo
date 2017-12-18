package com.melvin.tableexample.timetable1.ui;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.tableexample.R;
import com.melvin.tableexample.timetable1.model.TimeTableModel;
import com.melvin.tableexample.timetable1.presenter.TimeTablePresenter;
import com.melvin.tableexample.timetable1.utils.ScreenshotUtil;
import com.melvin.tableexample.widget.TimeTableView;

import java.util.List;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/27 13:00
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/27 13:00
 * @Description ${TODO}
 */

public class TimeTable1Activity extends BaseActivity<TimeTablePresenter> implements TimeTableUIView{

    private TimeTableView        mTimeTableView;
    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected TimeTablePresenter createPresenter() {
        return new TimeTablePresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.table_example_activity_timetable1;
    }

    @Override
    protected void initView() {

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.table_example_timetable1_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("课程表表格example");
        mToolbar.setSubtitle("自定义控件");

        mTimeTableView = (TimeTableView) findViewById(R.id.table_example_timetable1);
        //初始化数据获取
        mPresenter.getDummyData();


    }

    @Override
    public void onRequestSuccessData(TimeTableModel data) {

    }

    @Override
    public void setTableDatas(List<TimeTableModel> mDataList) {
        mTimeTableView.setTimeTable(mDataList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.table_example_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.table_example_main_screenshot:
                ScreenshotUtil.getBitmapByView(this, (ScrollView) findViewById(R.id.table_example_timetable1_scrollview));
                break;
        }
        return true;
    }
}
