package com.jsmy.chongyin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.Gitapi;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class UpFileService extends Service {
    public UpFileService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String yhid = intent.getStringExtra("yhid");
        String isBackGroud = intent.getStringExtra("isBackGroud");
        String tx = intent.getStringExtra("tx");
        DownloadThread thread = new DownloadThread(yhid, tx, isBackGroud);
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
        private Call<String> call;

        private String name;
        private String psd;
        private String isBackGroud;

        public DownloadThread(String name, String psd, String isBackGroud) {
            this.name = name;
            this.psd = psd;
            this.isBackGroud = isBackGroud;
        }

        @Override
        public void run() {
            upFile();
        }

        private void upFile() {
            if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID){
                ToastUtil.showShort(MyApplication.getMyApplication(),"网络链接异常，请检查网络状态!");
                return;
            }
            MyLog.showLog("GGG", name + "---" + psd);
            gitapi = retrofit.create(Gitapi.class);
            File file = new File(psd);
            //构建body
            if ("Y".equals(isBackGroud)) {
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("yhid", name)
                        .addFormDataPart("tp", "tp")
                        .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                        .build();
                call = gitapi.uploadFile(ServiceCode.SERVICE_URL + ServiceCode.UP_DATE_BJTUSC, requestBody);
            } else {
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("yhid", name)
                        .addFormDataPart("tx", "tx")
                        .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                        .build();
                call = gitapi.uploadFile(ServiceCode.SERVICE_URL + ServiceCode.UP_DATE_TX, requestBody);
            }

            call.enqueue(new retrofit2.Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if ("Y".equals(DecodeData.getCodeRoMsg(response.body(), DecodeData.CODE))) {
                        EventBus.getDefault().post(new DowloadEvent(response.body(), DecodeData.getCodeRoMsg(response.body(), DecodeData.CODE)));
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        }

    }
}
