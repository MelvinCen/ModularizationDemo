package com.melvin.tableexample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.melvin.tableexample.R;
import com.melvin.tableexample.timetable1.model.TimeTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 课表显示View
 *
 * @author shallcheek
 * @updated by Melvin
 * @describe bug修复， 但是此自定义控件不适合多数据使用，嵌套太深，内存使用量大，会导致OOM
 */
public class TimeTableView extends LinearLayout {
    /**
     * 配色数组
     */
    public static int colors[] = {R.drawable.table_example_select_label_san,
            R.drawable.table_example_select_label_er, R.drawable.table_example_select_label_si,
            R.drawable.table_example_select_label_wu, R.drawable.table_example_select_label_liu,
            R.drawable.table_example_select_label_qi, R.drawable.table_example_select_label_ba,
            R.drawable.table_example_select_label_jiu, R.drawable.table_example_select_label_sss,
            R.drawable.table_example_select_label_se, R.drawable.table_example_select_label_yiw,
            R.drawable.table_example_select_label_sy, R.drawable.table_example_select_label_yiwu,
            R.drawable.table_example_select_label_yi, R.drawable.table_example_select_label_wuw};
    private final static int START = 0;
    //最大节数
    public final static int MAXNUM = 12;
    //显示到星期几
    public final static int WEEKNUM = 7;
    //单个View高度
    private final static int TimeTableHeight = 50;
    //线的高度
    private final static int TimeTableLineHeight = 2;
    //第一竖排的宽度
    private final static int TimeTableNumWidth = 20;
    //第一横排的高度
    private final static int TimeTableWeekNameHeight = 30;
    private LinearLayout mHorizontalWeekLayout;//第一行的星期显示
    private LinearLayout mVerticalWeekLaout;//课程格子
    private String[]       weekname = {"一", "二", "三", "四", "五", "六", "七"};
    public static String[] colorStr = new String[20];
    int colornum = 0;
    //数据源
    private List<TimeTableModel> mListTimeTable = new ArrayList<TimeTableModel>();

    public TimeTableView(Context context) {
        super(context);
    }

    public TimeTableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 横的分界线
     *
     * @return
     */
    private View getWeekTransverseLine() {
        TextView mWeekline = new TextView(getContext());
        mWeekline.setBackgroundColor(getResources().getColor(R.color.view_line));
        mWeekline.setHeight(TimeTableLineHeight);
        mWeekline.setWidth(LayoutParams.FILL_PARENT);
        return mWeekline;
    }

    /**
     * 竖向分界线
     *
     * @return
     */
    private View getWeekVerticalLine() {
        TextView mWeekline = new TextView(getContext());
        mWeekline.setBackgroundColor(getResources().getColor(R.color.view_line));
        mWeekline.setHeight(dip2px(TimeTableWeekNameHeight));
        mWeekline.setWidth((TimeTableLineHeight));
        return mWeekline;
    }


    private void initView() {

        mHorizontalWeekLayout = new LinearLayout(getContext());
        mHorizontalWeekLayout.setOrientation(HORIZONTAL);

        mVerticalWeekLaout = new LinearLayout(getContext());
        mVerticalWeekLaout.setOrientation(HORIZONTAL);
        //表格
        for (int i = 0; i <= WEEKNUM; i++) {
            //这里循环一次添加一天的课表
            switch (i) {
                case 0:
                    //课表出的0,0格子 空白的
                    TextView mTime = new TextView(getContext());
                    mTime.setHeight(dip2px(TimeTableWeekNameHeight));
                    mTime.setWidth((dip2px(TimeTableNumWidth)));
                    mHorizontalWeekLayout.addView(mTime);

                    //绘制1~MAXNUM
                    LinearLayout mMonday = new LinearLayout(getContext());
                    //高度是MAXNUM个view格子的高 加上 MAXNUM个线的高度（单条线宽2px）这里是12个
                    ViewGroup.LayoutParams mm = new ViewGroup.LayoutParams(dip2px(TimeTableNumWidth), dip2px(MAXNUM * TimeTableHeight) + MAXNUM * 2);
                    mMonday.setLayoutParams(mm);
                    mMonday.setOrientation(VERTICAL);
                    //循环添加1 ~ MAXNUM个 这里是12个 的textView，加一个textView加一条横线
                    for (int j = 1; j <= MAXNUM; j++) {
                        TextView mNum = new TextView(getContext());
                        mNum.setGravity(Gravity.CENTER);
                        mNum.setTextColor(getResources().getColor(R.color.text_color));
                        mNum.setHeight(dip2px(TimeTableHeight));
                        mNum.setWidth(dip2px(TimeTableNumWidth));
                        mNum.setTextSize(14);
                        mNum.setText(j + "");
                        mMonday.addView(mNum);
                        mMonday.addView(getWeekTransverseLine());
                    }
                    mVerticalWeekLaout.addView(mMonday);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    // 设置显示星期一 到星期天 横排，这里第一个new的LinearLayout其实是一个textView的包裹
                    LinearLayout mHoriView = new LinearLayout(getContext());
                    mHoriView.setOrientation(VERTICAL);
                    TextView mWeekName = new TextView(getContext());
                    mWeekName.setTextColor(getResources().getColor(R.color.text_color));
                    mWeekName.setWidth(((getViewWidth() - dip2px(TimeTableNumWidth))) / WEEKNUM);
                    mWeekName.setHeight(dip2px(TimeTableWeekNameHeight));
                    mWeekName.setGravity(Gravity.CENTER);
                    mWeekName.setTextSize(16);
                    mWeekName.setText(weekname[i - 1]);
                    mHoriView.addView(mWeekName);
                    //加入之后其实还缺一条横线，在for循环的最后添加完课程后，再添加这条横线
                    mHorizontalWeekLayout.addView(mHoriView);

                    //一天的课表的集合
                    List<TimeTableModel> mListMon = new ArrayList<>();
                    //遍历出星期1~7的课表
                    for (TimeTableModel timeTableModel : mListTimeTable) {
                        if (timeTableModel.getWeek() == i) {
                            //添加到一天的课表中
                            mListMon.add(timeTableModel);
                        }
                    }
                    //添加
                    LinearLayout mLayout = getTimeTableView(mListMon, i);
                    mLayout.setOrientation(VERTICAL);
                    ViewGroup.LayoutParams linearParams = new ViewGroup.LayoutParams((getViewWidth() - dip2px(20)) / WEEKNUM, LayoutParams.FILL_PARENT);
                    mLayout.setLayoutParams(linearParams);
                    mLayout.setWeightSum(1);
                    mVerticalWeekLaout.addView(mLayout);
                    break;

                default:
                    break;
            }
            TextView l = new TextView(getContext());
            l.setHeight(dip2px(TimeTableHeight * MAXNUM) + MAXNUM * 2);
            l.setWidth(2);
            l.setBackgroundColor(getResources().getColor(R.color.view_line));
            mVerticalWeekLaout.addView(l);
            mHorizontalWeekLayout.addView(getWeekVerticalLine());
        }
        addView(mHorizontalWeekLayout);
        addView(getWeekTransverseLine());
        addView(mVerticalWeekLaout);
        addView(getWeekTransverseLine());
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    private int getViewWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    private View addStartView(int startnum, final int week, final int start) {//5,1,7
        LinearLayout mStartView = new LinearLayout(getContext());
        mStartView.setOrientation(VERTICAL);
        for (int i = 1; i < startnum; i++) {
            TextView mTime = new TextView(getContext());
            mTime.setGravity(Gravity.CENTER);
            mTime.setHeight(dip2px(TimeTableHeight));
            mTime.setWidth(dip2px(TimeTableHeight));
            mStartView.addView(mTime);
            mStartView.addView(getWeekTransverseLine());
            final int num = i;
            //这里可以处理空白处点击添加课表
            mTime.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "星期" + week + "第" + (start + num) + "节", Toast.LENGTH_LONG).show();
                }
            });

        }
        return mStartView;
    }

    /**
     * 星期一到星期天的课表(一天一天给出，调用一次根据参数返回一天的LinearLayout)
     *
     * @param model  一天的课表
     * @param week  周几
     * @return
     */
    private LinearLayout getTimeTableView(List<TimeTableModel> model, int week) {
        LinearLayout mTimeTableView = new LinearLayout(getContext());
        mTimeTableView.setOrientation(VERTICAL);
        int modesize = model.size();
        if (modesize <= 0) {
            mTimeTableView.addView(addStartView(MAXNUM + 1, week, 0));
        } else {
            for (int i = 0; i < modesize; i++) {
                if (i == 0) {
                    //添加的0到开始节数的空格
                    mTimeTableView.addView(addStartView(model.get(0).getStartnum(), week, 0));
                    mTimeTableView.addView(getMode(model.get(0)));
                } else if (model.get(i).getStartnum() - model.get(i - 1).getStartnum() > 0) {
                    //填充
                    //画空格
                    mTimeTableView.addView(addStartView(model.get(i).getStartnum() - model.get(i - 1).getEndnum(), week, model.get(i - 1).getEndnum()));
                    //画课程格子
                    mTimeTableView.addView(getMode(model.get(i)));
                }
                if (i + 1 == modesize) {
                    //FIXME:此处bug修复，添加了（ + 1） 来矫正计数，使当天有课的时候，能在添加完当天课程之后添加正确的空格数量
                    mTimeTableView.addView(addStartView(MAXNUM - model.get(i).getEndnum()+1, week, model.get(i).getEndnum()));
                }
            }
        }
        return mTimeTableView;
    }

    /**
     * 获取单个课表View 也可以自定义我这个
     *
     * @param model 数据类型
     * @return
     */
    @SuppressWarnings("deprecation")
    private View getMode(final TimeTableModel model) {
        LinearLayout mTimeTableView = new LinearLayout(getContext());
        mTimeTableView.setOrientation(VERTICAL);
        TextView mTimeTableNameView = new TextView(getContext());
        int num = model.getEndnum() - model.getStartnum();
        mTimeTableNameView.setHeight(dip2px((num + 1) * TimeTableHeight) + num * 2);
        mTimeTableNameView.setTextColor(getContext().getResources().getColor(
                android.R.color.white));
        mTimeTableNameView.setWidth(dip2px(50));
        mTimeTableNameView.setTextSize(16);
        mTimeTableNameView.setGravity(Gravity.CENTER);
        mTimeTableNameView.setText(model.getName() + "@" + model.getClassroom());
        mTimeTableView.addView(mTimeTableNameView);
        mTimeTableView.addView(getWeekTransverseLine());
        mTimeTableView.setBackgroundDrawable(getContext().getResources()
                .getDrawable(colors[getColorNum(model.getName())]));
        mTimeTableView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), model.getName() + "@" + model.getClassroom(), Toast.LENGTH_LONG).show();
            }
        });
        return mTimeTableView;
    }

    /**
     * 转换dp
     *
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setTimeTable(List<TimeTableModel> mlist) {
        this.mListTimeTable = mlist;
        for (TimeTableModel timeTableModel : mlist) {
            addTimeName(timeTableModel.getName());
        }
        initView();
        invalidate();
    }

    /**
     * 输入课表名循环判断是否数组存在该课表 如果存在输出true并退出循环 如果不存在则存入colorSt[20]数组
     *
     * @param name
     */
    private void addTimeName(String name) {
        boolean isRepeat = true;
        for (int i = 0; i < 20; i++) {
            if (name.equals(colorStr[i])) {
                isRepeat = true;
                break;
            } else {
                isRepeat = false;
            }
        }
        if (!isRepeat) {
            colorStr[colornum] = name;
            colornum++;
        }
    }

    /**
     * 获取数组中的课程名
     *
     * @param name
     * @return
     */
    public static int getColorNum(String name) {
        int num = 0;
        for (int i = 0; i < 20; i++) {
            if (name.equals(colorStr[i])) {
                num = i;
            }
        }
        return num;
    }
}
