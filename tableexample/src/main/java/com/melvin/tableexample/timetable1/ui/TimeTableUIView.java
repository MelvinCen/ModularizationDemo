package com.melvin.tableexample.timetable1.ui;

import com.melvin.melvincommon.base.BaseView;
import com.melvin.tableexample.timetable1.model.TimeTableModel;

import java.util.List;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/27 15:00
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/27 15:00
 * @Description ${TODO}
 */

public interface TimeTableUIView extends BaseView<TimeTableModel> {

    void setTableDatas(List<TimeTableModel> mDataList);
}
