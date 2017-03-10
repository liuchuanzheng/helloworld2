package com.liuchuanzheng.helloworld2;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    /**
     * 打开activity时要添加
     * @param activity
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    /**
     * ondestory时要移除
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    /**
     * 将所有集合中的activity一次finish
     * <br/>换行测试
     */
    public static void finishAll(){
        for (Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
