package com.vero.weatherapp.net;

import com.vero.weatherapp.util.HiExecutor;
import com.vero.weatherapp.util.HiNetUtil;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.net.NetHandle;
import ohos.net.NetManager;
import org.devio.hi.json.HiJson;
import org.devio.hi.json.JSONException;
import org.devio.hi.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HiNet implements IHiNet {
    private NetManager netManager;
    private HiLogLabel logLabel = new HiLogLabel(0, 0, HiNet.class.getSimpleName());

    public HiNet() {
        netManager = NetManager.getInstance(null);
    }

    @Override
    public void get(String url, Map<String, String> params, NetListener listener) {
        String finalUrl = HiNetUtil.buildParams(url, params);
        HiLog.error(logLabel, "finalUrl==" + finalUrl);
        HiExecutor.runOnBG(new Runnable() {
            @Override
            public void run() {
                doGet(finalUrl, listener);
            }
        });
    }

    private void doGet(String finalUrl, NetListener listener) {
        NetHandle netHandle = netManager.getDefaultNet();
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;

        try {
            URL url = new URL(finalUrl);
            URLConnection urlConnection = netHandle.openConnection(url, Proxy.NO_PROXY);
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            }
            connection.setRequestMethod("GET");
            connection.connect();
            HiLog.error(logLabel, "connect...");
            if (connection.getResponseCode() == 200) {
                HiLog.error(logLabel, "connection.getResponseCode() == 200");
                inputStream = connection.getInputStream();
                baos = new ByteArrayOutputStream();
                int readLen;
                byte[] bytes = new byte[1024];
                while ((readLen = inputStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, readLen);
                }
                String result = baos.toString();
                HiExecutor.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        HiLog.error(logLabel, "connected==" + result);
                        try {
                            HiJson res = new HiJson(new JSONObject(result));
                            listener.onSuccess(res);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("connected==数据解析错误" + e.getMessage());
                        }
                    }
                });
            } else {
                HiLog.error(logLabel, "connected==请求失败,code==" + connection.getResponseCode());
                listener.onFail("connected==请求失败,code==" + connection.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            HiLog.error(logLabel, "connected==请求失败,code==" + e.getMessage());
            listener.onFail("connected==请求失败,code==" + e.getMessage());
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
            HiNetUtil.close(inputStream);
            HiNetUtil.close(baos);
        }

    }
}
