package com.melvin.tableexample;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.tableexample.timetable1.ui.TimeTable1Activity;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/27 12:57
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/27 12:57
 * @Description ${TODO}
 */

public class MainTableExampleActivity extends BaseActivity implements View.OnClickListener {

    //跳转TimeTable
    private Button mBtTimeTable;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.table_example_activity_main;
    }

    @Override
    protected void initView() {
        mBtTimeTable = (Button) findViewById(R.id.table_example_main_bt_timetable1);
        mBtTimeTable.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_example_main_bt_timetable1:
                startActivity(new Intent(this, TimeTable1Activity.class));
                break;
        }
    }
}
