package com.cvte.wifip2pfiletransfer;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cvte.wifip2pfiletransfer.Base.BaseActivity;
import com.cvte.wifip2pfiletransfer.broadcast.DirectBroadcastReceiver;
import com.cvte.wifip2pfiletransfer.callback.DirectActionListener;
import com.cvte.wifip2pfiletransfer.service.WifiServerService;

import java.util.Collection;

/**
 * Created by user on 2020/11/2.
 */

public class ReceiveFileActivity extends BaseActivity {

    private ImageView mCover;
    private Button mCreateGroup;
    private Button mRemoveGroup;
    private TextView mTvLog;
    private ProgressDialog mProgressDialog;
    private WifiP2pManager mWifiP2pManager;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //todo:成功绑定文件传输的服务
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private DirectActionListener mDirectActionListener = new DirectActionListener() {
        @Override
        public void setWifiP2pEnabled(boolean enabled) {

        }

        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {

        }

        @Override
        public void onDisconnection() {

        }

        @Override
        public void onSelfDeviceAvailable(WifiP2pDevice wifiP2pDevice) {

        }

        @Override
        public void onPeersAvailable(Collection<WifiP2pDevice> wifiP2pDeviceList) {

        }

        @Override
        public void onChannelDisconnected() {

        }
    };
    private WifiP2pManager.Channel mChannel;
    private DirectBroadcastReceiver mDirectBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivefile);
        initView();

        mWifiP2pManager = (WifiP2pManager) getSystemService(WIFI_P2P_SERVICE);
        if (mWifiP2pManager == null) {
            finish();
            return;
        }

        mChannel = mWifiP2pManager.initialize(this, getMainLooper(), mDirectActionListener);

        mDirectBroadcastReceiver = new DirectBroadcastReceiver(mWifiP2pManager,mChannel,mDirectActionListener);
        registerReceiver(mDirectBroadcastReceiver,DirectBroadcastReceiver.getIntentFilter());
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent(ReceiveFileActivity.this,WifiServerService.class);
        bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
    }


    private void initView() {
        setTitle("接收文件");
        mCover = this.findViewById(R.id.iv_image);
        mCreateGroup = this.findViewById(R.id.btn_createGroup);
        mRemoveGroup = this.findViewById(R.id.btn_removeGroup);
        mTvLog = this.findViewById(R.id.tv_log);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setTitle("正在接收文件");
        mProgressDialog.setMax(100);

    }



}
