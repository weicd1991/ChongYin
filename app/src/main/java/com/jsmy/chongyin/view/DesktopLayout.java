package com.jsmy.chongyin.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.activity.NoteActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.service.FloatWindowService;
import com.jsmy.chongyin.utils.AnimaUtils;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.UtilsTools;
import com.jsmy.chongyin.windowmanager.MyWindowManager;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/5.
 */

public class DesktopLayout extends LinearLayout implements View.OnClickListener {
    private TextView tv;
    private ImageView imgZ;
    private RelativeLayout relaPanding;
    private ImageView imgBook;
    private ImageView imgHome;
    private ImageView imgClose;
    private RelativeLayout relaImg;


    private Context context;
    private Timer timer;
    private int number;
    private String name;
    private View view;
    public AnimaUtils anima;

    public DesktopLayout(Context context, String name) {
        super(context);
        this.context = context;
        this.name = name;
        anima = new AnimaUtils();
        setOrientation(LinearLayout.VERTICAL);// 水平排列

        //设置宽高
        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        view = LayoutInflater.from(context).inflate(R.layout.desklayout, null);
        tv = (TextView) view.findViewById(R.id.tv);
        imgZ = (ImageView) view.findViewById(R.id.img_z);
        relaImg = (RelativeLayout) view.findViewById(R.id.rela_img);
        relaPanding = (RelativeLayout) view.findViewById(R.id.rela_panding);
        imgBook = (ImageView) view.findViewById(R.id.img_book);
        imgBook.setOnClickListener(this);
        imgHome = (ImageView) view.findViewById(R.id.img_home);
        imgHome.setOnClickListener(this);
        imgClose = (ImageView) view.findViewById(R.id.img_close);
        imgClose.setOnClickListener(this);
        this.addView(view);
        setImgLayoutParams();
        number = 1;

        // 开启定时器，每隔10秒刷新一次
//        if (timer == null) {
//            timer = new Timer();
//            timer.schedule(new ShowImgTask(), 0, 10000);
//        }

        handler.sendEmptyMessageDelayed(0, 1000);
    }

    public void setImgLayoutParams() {
        ViewGroup.LayoutParams params = imgZ.getLayoutParams();
        ViewGroup.LayoutParams paramsBook = imgBook.getLayoutParams();
        ViewGroup.LayoutParams paramsHome = imgHome.getLayoutParams();
        ViewGroup.LayoutParams paramsClose = imgClose.getLayoutParams();
        ViewGroup.LayoutParams paramsImg = relaImg.getLayoutParams();
        ViewGroup.LayoutParams paramsPanding = relaPanding.getLayoutParams();
        String str = SharePrefUtil.getStringPet(context, AtyTag.DeskPet, MyApplication.getMyApplication().userInfo.getZmpet());
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.chongyin/pet/" + name;
        } else {
            //申请权限
        }
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap == null)
            return;
        switch (str) {
            case "50":
                tv.setTextSize(5);
                params.height = UtilsTools.dip2px(context, 67f);
                params.width = UtilsTools.dip2px(context, 54f);
                imgZ.setLayoutParams(params);
                paramsBook.height = UtilsTools.dip2px(context,20f);
                paramsBook.width = UtilsTools.dip2px(context,20f);
                imgBook.setLayoutParams(paramsBook);
                paramsHome.height = UtilsTools.dip2px(context,20f);
                paramsHome.width = UtilsTools.dip2px(context,20f);
                imgHome.setLayoutParams(paramsHome);
                paramsClose.height = UtilsTools.dip2px(context,20f);
                paramsClose.width = UtilsTools.dip2px(context,20f);
                imgClose.setLayoutParams(paramsClose);
                paramsImg.width = UtilsTools.dip2px(context,100f);
                relaImg.setLayoutParams(paramsImg);
                paramsPanding.width = UtilsTools.dip2px(context,30f);
                relaPanding.setLayoutParams(paramsPanding);
                break;
            case "100":
                tv.setTextSize(10);
                params.height = UtilsTools.dip2px(context, 134f);
                params.width = UtilsTools.dip2px(context, 108f);
                imgZ.setLayoutParams(params);
                paramsBook.height = UtilsTools.dip2px(context,40f);
                paramsBook.width = UtilsTools.dip2px(context,40f);
                imgBook.setLayoutParams(paramsBook);
                paramsHome.height = UtilsTools.dip2px(context,40f);
                paramsHome.width = UtilsTools.dip2px(context,40f);
                imgHome.setLayoutParams(paramsHome);
                paramsClose.height = UtilsTools.dip2px(context,40f);
                paramsClose.width = UtilsTools.dip2px(context,40f);
                imgClose.setLayoutParams(paramsClose);
                paramsImg.width = UtilsTools.dip2px(context,200f);
                relaImg.setLayoutParams(paramsImg);
                paramsPanding.width = UtilsTools.dip2px(context,60f);
                relaPanding.setLayoutParams(paramsPanding);
                break;
            case "150":
                tv.setTextSize(15);
                params.height = UtilsTools.dip2px(context, 201f);
                params.width = UtilsTools.dip2px(context, 162f);
                imgZ.setLayoutParams(params);
                paramsBook.height = UtilsTools.dip2px(context,60f);
                paramsBook.width = UtilsTools.dip2px(context,60f);
                imgBook.setLayoutParams(paramsBook);
                paramsHome.height = UtilsTools.dip2px(context,60f);
                paramsHome.width = UtilsTools.dip2px(context,60f);
                imgHome.setLayoutParams(paramsHome);
                paramsClose.height = UtilsTools.dip2px(context,60f);
                paramsClose.width = UtilsTools.dip2px(context,60f);
                imgClose.setLayoutParams(paramsClose);
                paramsImg.width = UtilsTools.dip2px(context,300f);
                relaImg.setLayoutParams(paramsImg);
                paramsPanding.width = UtilsTools.dip2px(context,90f);
                relaPanding.setLayoutParams(paramsPanding);
                break;
            case "200":
                tv.setTextSize(20);
                params.height = UtilsTools.dip2px(context, 268f);
                params.width = UtilsTools.dip2px(context, 216f);
                imgZ.setLayoutParams(params);
                paramsBook.height = UtilsTools.dip2px(context,80f);
                paramsBook.width = UtilsTools.dip2px(context,80f);
                imgBook.setLayoutParams(paramsBook);
                paramsHome.height = UtilsTools.dip2px(context,80f);
                paramsHome.width = UtilsTools.dip2px(context,80f);
                imgHome.setLayoutParams(paramsHome);
                paramsClose.height = UtilsTools.dip2px(context,80f);
                paramsClose.width = UtilsTools.dip2px(context,80f);
                imgClose.setLayoutParams(paramsClose);
                paramsImg.width = UtilsTools.dip2px(context,400f);
                relaImg.setLayoutParams(paramsImg);
                paramsPanding.width = UtilsTools.dip2px(context,120f);
                relaPanding.setLayoutParams(paramsPanding);
                break;
            default:
                MyWindowManager.removeMyWindow(context);
                break;
        }

    }

    public void setTv(String msg) {
        tv.setText(msg);
        tv.setVisibility(View.VISIBLE);
        handler.sendEmptyMessageDelayed(107, 7000);
    }

    private void showImgB() {
        showAnimation(number);
    }

    public void showAnimation(int i) {
        anima.runFrameForEye(context, MyApplication.getMyApplication().userInfo.getPetid() + "_" + UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())), imgZ);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    removeMessages(0);
                    showAnimation(1);
                    break;
                case 107:
                    tv.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_book:
                Intent intenNote = new Intent(context,NoteActivity.class);
                intenNote.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intenNote);
                break;
            case R.id.img_home:
                Intent intentMain = new Intent(context,MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentMain);
                break;
            case R.id.img_close:
                SharePrefUtil.saveStringPet(getContext(), AtyTag.DeskPet, "0");
                Intent intent1 = new Intent(context, FloatWindowService.class);
                intent1.putExtra("isShow", "N");
                intent1.putExtra("path", "");
                intent1.putExtra("change", "N");
                context.startService(intent1);
                break;
        }
    }

    class ShowImgTask extends TimerTask {

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showImgB();
                }
            });

        }
    }

    public void closeTimer() {

//        timer.cancel();
//        timer = null;

    }

    public void showPanding(){
        if (relaPanding != null && relaPanding.getVisibility() == VISIBLE) {
            relaPanding.setVisibility(GONE);
        } else if (relaPanding != null && relaPanding.getVisibility() == GONE) {
            relaPanding.setVisibility(VISIBLE);
        }
    }

}
