package com.jsmy.chongyin.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.Gitapi;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DownLoadService extends Service {
    public DownLoadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String petid = "" + intent.getStringExtra("petid");
        String petdj = "" + intent.getStringExtra("petdj");
        String bqing = "" + intent.getStringExtra("bqing");
        String number = "" + intent.getStringExtra("number");
        String url = intent.getStringExtra("url");
        String item = "" + intent.getStringExtra("item");
        DownloadThread thread = new DownloadThread(petid, petdj, bqing, number, url, item);
        thread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class DownloadThread extends Thread {
        private Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceCode.SERVICE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        private Gitapi gitapi;
        private Call<ResponseBody> callFile;
        String petid;
        String petdj;
        String bqing;
        String number;
        private String url;
        private String item;
        private String name;

        public DownloadThread(String petid, String petdj, String bqing, String number, String url, String item) {
            this.petid = petid;
            this.petdj = petdj;
            this.bqing = bqing;
            this.number = number;
            this.url = url;
            this.item = item;
            this.name = petid + "_" + petdj + "_" + bqing + "_" + number + ".png";
        }

        @Override
        public void run() {
            downloadFile(url);
        }

        private void downloadFile(String url) {
            getNetVolue(url);
        }

        public void getNetVolue(String url) {
            if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
//                Toast.makeText(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态", Toast.LENGTH_LONG).show();
                ToastUtil.showShort(MyApplication.getMyApplication(),"网络链接异常，请检查网络状态!");
                return;
            }
            gitapi = retrofit.create(Gitapi.class);
            MyLog.showLog("AAA", url + "");
            callFile = gitapi.downloadFiles(url);
            File file = new File(ServiceCode.BASE_PATH + name);
            if (file.exists()) {
                EventBus.getDefault().post(new DowloadEvent(item, "download"));
            } else if ("".equals(url)) {
                EventBus.getDefault().post(new DowloadEvent(item, "download"));
            } else {
                getResult(url);
            }
        }

        public void getResult(final String url) {
            MyLog.showLog("CCC", "正在下载");
            callFile.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean writtenToDisk = writeResponseBodyToDisk(response.body(), url);
                                if (writtenToDisk)
                                    MyLog.showLog("CCC", "下载成功");
                                setMsg(writtenToDisk);
                            }
                        }).start();

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    MyLog.showLog("CCC", "下载失败");
                    EventBus.getDefault().post(new DowloadEvent(item, "download"));
                }
            });

        }

        public boolean writeResponseBodyToDisk(ResponseBody body, String url) {
            try {

                File futureStudioIconFile = new File(ServiceCode.BASE_PATH + name);
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    byte[] fileReader = new byte[8 * 1024];
                    long fileSize = body.contentLength();
                    long fileSizeDownloaded = 0;
                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(futureStudioIconFile);
                    while (true) {
                        int read = inputStream.read(fileReader);
                        if (read == -1) {
                            break;
                        }
                        outputStream.write(fileReader, 0, read);
                        fileSizeDownloaded += read;
                    }
                    outputStream.flush();
                    return true;
                } catch (IOException e) {
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException e) {
                return false;
            }
        }

        public void setMsg(boolean isComplete) {
            if (isComplete) {
                EventBus.getDefault().post(new DowloadEvent(item, "download"));
            }
        }

    }
}
