package com.melvin.pulltorefresh.swiperefresh.ui;

import android.support.v4.widget.SwipeRefreshLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/23 15:22
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/23 15:22
 * @Description ${TODO}
 */

public class SwipContainerUtiles {
    //通过反射调用起私有的自动刷新的方法！
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing, boolean notify) {
        Class<? extends SwipeRefreshLayout> refreshLayoutClass = refreshLayout.getClass();
        if (refreshLayoutClass != null) {

            try {
                Method setRefreshing = refreshLayoutClass.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
                setRefreshing.setAccessible(true);
                setRefreshing.invoke(refreshLayout, refreshing, notify);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


}
