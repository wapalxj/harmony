package com.vero.weatherapp.util;

import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;

public class HiExecutor {
    //切换任务到主线程
    public static void runOnUI(Runnable runnable) {
        //切换到主线程
        EventRunner runner=EventRunner.getMainEventRunner();
        EventHandler eventHandler=new EventHandler(runner);
        //执行
        eventHandler.postSyncTask(runnable);
    }

    /**
     * 子线程执行任务
     * @param runnable
     */
    public static void runOnBG(Runnable runnable) {
        //子线程创建
        EventRunner runner=EventRunner.create(true);
        EventHandler eventHandler=new EventHandler(runner);
        //执行
        eventHandler.postTask(runnable,0,EventHandler.Priority.IMMEDIATE);
    }
}
