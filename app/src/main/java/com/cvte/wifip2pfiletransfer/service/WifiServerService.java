package com.cvte.wifip2pfiletransfer.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by user on 2020/11/11.
 * IntentService 做耗时操作传输文件
 */

public class WifiServerService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public WifiServerService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }


}
