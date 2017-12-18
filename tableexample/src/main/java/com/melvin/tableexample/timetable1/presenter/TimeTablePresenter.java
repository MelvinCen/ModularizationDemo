package com.melvin.tableexample.timetable1.presenter;

import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.tableexample.timetable1.model.TimeTableDataModel;
import com.melvin.tableexample.timetable1.model.TimeTableModel;
import com.melvin.tableexample.timetable1.ui.TimeTableUIView;

import java.util.List;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/27 15:01
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/27 15:01
 * @Description ${TODO}
 */

public class TimeTablePresenter extends BasePresenter<TimeTableUIView> {

    public TimeTablePresenter(TimeTableUIView timeTableUIView) {
        attachView(timeTableUIView);
    }

    public void getDummyData(){
        List<TimeTableModel> dummyData = TimeTableDataModel.getInstance().createDummyData();
        getView().setTableDatas(dummyData);
    }


}
