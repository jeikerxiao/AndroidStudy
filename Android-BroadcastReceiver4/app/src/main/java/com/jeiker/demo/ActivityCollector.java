package com.jeiker.demo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity 管理器
 * Created by xiao on 17/9/10.
 */

public class ActivityCollector {

    private static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
