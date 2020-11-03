package com.cvte.wifip2pfiletransfer;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cvte.wifip2pfiletransfer.Base.BaseActivity;
import com.cvte.wifip2pfiletransfer.callback.DirectActionListener;
import com.cvte.wifip2pfiletransfer.view.LoadingDialog;

import java.util.Collection;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by user on 2020/11/2.
 */

public class SendFileActivity extends BaseActivity {
    private static final String TAG = "SendFileActivity";
    private Button mBtn_disconnect;
    private Button mBtn_chooseFile;
    private TextView mTv_deviceInfo;
    private TextView mTv_deviceName;
    private TextView mTv_deviceAddress;
    private TextView mTv_deviceStatus;
    private TextView mTv_status;
    private TextView mTv_deviceList;
    private RecyclerView mRv_deviceList;

    private LoadingDialog mLoadingDialog;

    private WifiP2pManager mWifiP2pManager;
    /**
     * A channel that connects the application to the Wifi p2p framework.
     * Most p2p operations require a Channel as an argument. An instance of Channel is obtained
     */
    private WifiP2pManager.Channel mChannel;
    private WifiP2pInfo mWifiP2pInfo;

    private DirectActionListener mDirectActionListener = new DirectActionListener() {
        @Override
        public void wifiP2pEnabled(boolean enabled) {

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendfile);
        initView();
        initViewListner();
        initP2PEvent();
    }

    private void initP2PEvent() {
        mWifiP2pManager = (WifiP2pManager) getSystemService(WIFI_P2P_SERVICE);
        if (mWifiP2pManager == null) {
            finish();
            return;
        }

        mChannel = mWifiP2pManager.initialize(this,getMainLooper(),mDirectActionListener);


    }

    private void initViewListner() {
        mBtn_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnect();
            }
        });

        mBtn_chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToChose();
            }
        });
    }

    private void navToChose() {
        Log.d(TAG,"choose File");
    }

    private void disconnect() {
    }

    private void initView() {
        setTitle("发送文件");
        mBtn_disconnect = this.findViewById(R.id.btn_disconnect);
        mBtn_chooseFile = this.findViewById(R.id.btn_chooseFile);
        mTv_deviceInfo = this.findViewById(R.id.tv_deviceInfo);
        mTv_deviceName = this.findViewById(R.id.tv_deviceName);
        mTv_deviceAddress = this.findViewById(R.id.tv_deviceAddress);
        mTv_deviceStatus = this.findViewById(R.id.tv_deviceStatus);
        mTv_status = this.findViewById(R.id.tv_status);
        mTv_deviceList = this.findViewById(R.id.tv_deviceList);
        mRv_deviceList = this.findViewById(R.id.rv_deviceList);


        mLoadingDialog = new LoadingDialog(this);


    }

    /*创建好顶部的菜单*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action,menu);
        return true;
    }

    /*顶部菜单添加事件*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDirectEnable:{
                if (mWifiP2pManager != null && mChannel != null) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                } else {
                    showToast("当前设备不支持WFD");
                }
                return true;
            }

            case R.id.menuDirectDiscover:{

            }

        }
        return super.onOptionsItemSelected(item);
    }
}
