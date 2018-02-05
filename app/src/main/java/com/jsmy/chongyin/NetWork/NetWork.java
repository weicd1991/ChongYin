package com.jsmy.chongyin.NetWork;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.TrafficBean;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 进行网络请求类
 * 直接使用 NetWork.getNetVolue()
 * Created by Administrator on 2017/3/28.
 */

public class NetWork {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServiceCode.SERVICE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static Gitapi gitapi;
    private static Call<String> call;
    private static String result;

    /**
     * @param url     请求地址 ServiceCode中选择
     * @param map     get请求专用 封装请求参数
     * @param netCode 选择 get 或者 post 请求
     * @param body    参考 upNetData 方法
     */
    public static void getNetVolue(String url, Map<String, String> map, int netCode, RequestBody body) {
        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
//                Toast.makeText(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态", Toast.LENGTH_LONG).show();
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        gitapi = retrofit.create(Gitapi.class);
        switch (netCode) {
            case ServiceCode.NETWORK_GET:
                call = gitapi.getNetWork(ServiceCode.SERVICE_URL + url, map);
                getResult();
                break;
            case ServiceCode.NETWORK_POST:
                call = gitapi.postNetWork(ServiceCode.SERVICE_URL + url, map);
                getResult();
                break;
            case ServiceCode.NETWORK_UPFILE:
                call = gitapi.uploadFile(ServiceCode.SERVICE_URL + url, body);
                getResult();
                break;
            default:
                break;
        }

    }

    /**
     * 实体类不确定，返回请求结果-字符串，自行Gson解析
     */
    public static void getResult() {
        try {
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    MyLog.showLog("WWW", response.toString());
                    result = response.body();
                    MyLog.showLog("WWW", "- - " + result);
                    EventBus.getDefault().post(new DowloadEvent(result, DecodeData.getCodeRoMsg(result, DecodeData.CODE)));
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    result = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //弹幕新增
    private static void getNetVolue(final String url, final Map<String, String> map, final CallListener callListener) {
        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        call = gitapi.getNetWork(url, map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response == null) {
                    ToastUtil.showShort(MyApplication.getMyApplication(), "404 - 对不起，服务器维护中~~~");
                    return;
                }
                try {
                    MyLog.showLog("NetWork", "*****请求成功****" + response.toString());
                    MyLog.showLog("NetWork", "*****返回数据****" + response.body());
                    String result = new String(response.body());
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.optString("code");
                    String msg = jsonObject.optString("msg");
                    String check = jsonObject.optString("check");
                    if (callListener != null) {
                        callListener.onSuccess(url, check, code, result, msg);
                    } else {
                        MyLog.showLog("NetWork", "*****监听器为空****");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("NetWork", "*****请求失败****" + t.toString());
                if (callListener != null) {
                    callListener.onFailure(url, t.toString());
                } else {
                    MyLog.showLog("NetWork", "*****监听器为空****" + t.toString());
                }
                ToastUtil.showShort(MyApplication.getMyApplication(), "*****请求失败****" + t.toString());
            }
        });

    }

    private static void postNetVolue(final String url, final Map<String, String> map, final CallListener callListener) {
        if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
            try {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        call = gitapi.postNetWork(url, map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response == null) {
                    ToastUtil.showShort(MyApplication.getMyApplication(), "404 - 对不起，服务器维护中~~~");
                    return;
                }
                try {
                    MyLog.showLog("NetWork", "*****请求成功****" + response.toString());
                    MyLog.showLog("NetWork", "*****返回数据****" + response.body());
                    String result = new String(response.body());
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.optString("code");
                    String msg = jsonObject.optString("msg");
                    String check = jsonObject.optString("check");
                    if (callListener != null) {
                        callListener.onSuccess(url, check, code, result, msg);
                    } else {
                        MyLog.showLog("NetWork", "*****监听器为空****");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("NetWork", "*****请求失败****" + t.toString());
                if (callListener != null) {
                    callListener.onFailure(url, t.toString());
                } else {
                    MyLog.showLog("NetWork", "*****监听器为空****" + t.toString());
                }
                ToastUtil.showShort(MyApplication.getMyApplication(), "*****请求失败****" + t.toString());
            }
        });
    }

    public interface CallListener {
        public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException;

        public void onFailure(String type, String arg1);
    }

    //新增用户留言
    public static void addYhly(String yhid, String lyrid, String nr, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("lyrid", lyrid);
        map.put("nr", nr);
        map.put("isAND", "Y");
        getNetVolue(API.ADD_YHLY, map, callListener);
    }

    //查询用户留言
    public static void getYhlylList(String yhid, String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_YHLI_LIST, map, callListener);
    }

    //查询未读用户留言
    public static void getNoreadlylList(String yhid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("pageindex", "1");
        getNetVolue(API.GET_NO_READ_LY_LIST, map, callListener);
    }

    //更新留言状态
    public static void updateLyzt(String lyId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("lyId", lyId);
        getNetVolue(API.UPDATE_LYZT, map, callListener);
    }

    //新增记事
    public static void addNote(String yhid, String nr, String txsj, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("nr", nr);
        map.put("txsj", txsj);
        postNetVolue(API.ADD_NOTE, map, callListener);
    }

    //查询记事
    public static void getNoteList(String yhid, String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_NOTE_LIST, map, callListener);
    }

    //删除记事
    public static void updateNoteDel(String noteId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("noteId", noteId);
        getNetVolue(API.UPDATE_NOTE_DEL, map, callListener);
    }

    //修改记事
    public static void updateNoteClock(String noteId, String txsj, String nr, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("noteId", noteId);
        map.put("txsj", txsj);
        map.put("nr", nr);
        postNetVolue(API.UPDATE_NOTE_CLOCK, map, callListener);
    }

    //删除记事闹钟
    public static void updateClockDel(String noteId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("noteId", noteId);
        getNetVolue(API.UPDATE_CLOCK_DEL, map, callListener);
    }

    //查询记事详情
    public static void getnoteInfo(String noteId, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("noteId", noteId);
        getNetVolue(API.GET_NOTE_INFO, map, callListener);
    }

    //查询随机消息
    public static void getzdyxxList(CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        getNetVolue(API.GET_ZDYXX_LIST, map, callListener);
    }

    //查询附近的人
    public static void getfjpyList(String yhid, String jd, String wd, String xb, String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("jd", jd);
        map.put("wd", wd);
        map.put("xb", xb);
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_FJPY_LIST, map, callListener);
    }

    //每日推荐用户
    public static void gettjyhList(String yhid, String pageindex, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("pageindex", pageindex);
        getNetVolue(API.GET_TJYH_LIST, map, callListener);
    }

    //查询用户上周爱心排行
    public static void getyhaxph(String yhid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("pageindex", "1");
        getNetVolue(API.GET_YH_AXPH, map, callListener);
    }

    //查询用户上周爱心排行
    public static void updateYhybsl(String yhid, String cns, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        map.put("cns", cns);
        getNetVolue(API.UPDATE_YHYBSL, map, callListener);
    }

    //查询用户上周爱心排行
    public static void updateYhybs(String yhid, CallListener callListener) {
        Map<String, String> map = new HashMap<>();
        map.put("yhid", yhid);
        getNetVolue(API.UPDATE_YHYBS, map, callListener);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param img
     */
    public static void setImgGlide(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .animate(R.anim.item_alpha_in)
                .into(img);
    }

    public static void setImgGlideBackgroud(final Activity context, String url, final View img) {

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        if (!context.isFinishing()) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                MyLog.showLog("UUU", "-*----- 444 -----*-");
                                img.setBackground(resource);
                            } else {
                                MyLog.showLog("UUU", "-*----- 555 -----*-");
                                img.setBackgroundDrawable(resource);
                            }
                        }
                    }
                });
    }

    //上传之前构建 参考
    public void upNetData(String url, Map<String, String> map, File file, String fileType) {
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name", map.get("name"))
                .addFormDataPart("psd", map.get("psd"))
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .build();

    }


}
