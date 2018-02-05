package com.jsmy.chongyin.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.ServiceCode;

import java.io.File;

/**
 * Created by Administrator on 2017/5/2.
 */

public class AnimaUtils {

    String name = "petid" + "_" + "petdj" + "_" + "zyurl" + "_" + "1" + ".png";
    AnimationDrawable anim;
    Context context = MyApplication.getMyApplication().getApplicationContext();
    ImageView img;

    private int run = 3;

    //SD卡路径
    private static Drawable getDrawable(Context context, String name) {
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = ServiceCode.BASE_PATH + name + ".png";
        } else {
            //申请权限
        }
        Drawable drawable;
        File file = new File(path);
        if (file.exists()) {
            drawable = BitmapDrawable.createFromPath(path);
            return drawable;
        } else {
            return null;
        }
    }


    //眨眼 1
    public void runFrameForEye(Context context, String name, ImageView imgPetZ) {
        img = imgPetZ;
        petid = name.substring(0, name.indexOf("_"));
        petdj = name.substring(name.indexOf("_") + 1);
        String allname = name + "_" + "zyjurl" + "_";
        //完全编码实现的动画效果
//        AnimationDrawable anim = new AnimationDrawable();
        if (anim != null && anim.isRunning()) {
            anim.stop();
            anim = null;
        }
        anim = new AnimationDrawable();


        if (null != getDrawable(context, allname + 1))
            anim.addFrame(getDrawable(context, allname + 1), 2000);

        if (null != getDrawable(context, allname + 2))
            anim.addFrame(getDrawable(context, allname + 2), 200);

        if (null != getDrawable(context, allname + 3))
            anim.addFrame(getDrawable(context, allname + 3), 200);

        if (null != getDrawable(context, allname + 4))
            anim.addFrame(getDrawable(context, allname + 4), 200);

//        if (null != getDrawable(context, name + "_" + "zyjurl" + "_" + 1))
//            anim.addFrame(getDrawable(context, name + "_" + "zyjurl" + "_" + 1), 100);

        anim.setOneShot(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imgPetZ.setBackground(anim);
//            imgPetZ.setImageDrawable(anim);
        } else {
            imgPetZ.setBackgroundDrawable(anim);
//            imgPetZ.setImageDrawable(anim);
        }
        if (handler != null) {
            anim.start();  //开始动画
            run = 1;
            MyLog.showLog("anim", "++眨眼++");
            handler.sendEmptyMessageDelayed(1, 2600);
        }

    }

    //吃 2
    public void runFrameForEat(Context context, String name, ImageView imgPetZ) {
        img = imgPetZ;
        petid = name.substring(0, name.indexOf("_"));
        petdj = name.substring(name.indexOf("_") + 1);
        String allname = name + "_" + "curl" + "_";
        //完全编码实现的动画效果
//        AnimationDrawable anim = new AnimationDrawable();
        if (anim != null && anim.isRunning()) {
            anim.stop();
            anim = null;
        }
        anim = new AnimationDrawable();


        if (null != getDrawable(context, allname + 1))
            anim.addFrame(getDrawable(context, allname + 1), 500);

        if (null != getDrawable(context, allname + 2))
            anim.addFrame(getDrawable(context, allname + 2), 500);

        if (null != getDrawable(context, allname + 3))
            anim.addFrame(getDrawable(context, allname + 3), 200);

        if (null != getDrawable(context, allname + 4))
            anim.addFrame(getDrawable(context, allname + 4), 200);

        if (null != getDrawable(context, allname + 5))
            anim.addFrame(getDrawable(context, allname + 5), 200);

        if (null != getDrawable(context, allname + 6))
            anim.addFrame(getDrawable(context, allname + 6), 200);

        if (null != getDrawable(context, allname + 7))
            anim.addFrame(getDrawable(context, allname + 7), 200);

//        if (null != getDrawable(context, name + "_" + "zyjurl" + "_" + 1))
//            anim.addFrame(getDrawable(context, name + "_" + "zyjurl" + "_" + 1), 100);

        anim.setOneShot(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imgPetZ.setBackground(anim);
        } else {
            imgPetZ.setBackgroundDrawable(anim);
        }
        if (handler != null) {
            anim.start();  //开始动画
            run = 2;
            MyLog.showLog("anim", "++吃++");
            handler.sendEmptyMessageDelayed(2, 2000);
        }
    }

    //打招呼 3
    public void runFrameForHey(Context context, String name, ImageView imgPetZ) {
        img = imgPetZ;
        petid = name.substring(0, name.indexOf("_"));
        petdj = name.substring(name.indexOf("_") + 1);
        String allname = name + "_" + "dzhurl" + "_";
        //完全编码实现的动画效果
//        AnimationDrawable anim = new AnimationDrawable();
        if (anim != null && anim.isRunning()) {
            anim.stop();
            anim = null;
        }
        anim = new AnimationDrawable();

        if (null != getDrawable(context, allname + 1))
            anim.addFrame(getDrawable(context, allname + 1), 200);

        if (null != getDrawable(context, allname + 2))
            anim.addFrame(getDrawable(context, allname + 2), 200);

        if (null != getDrawable(context, allname + 3))
            anim.addFrame(getDrawable(context, allname + 3), 200);

        if (null != getDrawable(context, allname + 4))
            anim.addFrame(getDrawable(context, allname + 4), 500);

        if (null != getDrawable(context, allname + 5))
            anim.addFrame(getDrawable(context, allname + 5), 500);

//        if (null != getDrawable(context, name + "_" + "zyjurl" + "_" + 1))
//            anim.addFrame(getDrawable(context, name + "_" + "zyjurl" + "_" + 1), 100);

        anim.setOneShot(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imgPetZ.setBackground(anim);
        } else {
            imgPetZ.setBackgroundDrawable(anim);
        }
        if (handler != null) {
            anim.start();  //开始动画
            run = 3;
            MyLog.showLog("anim", "++打招呼++");
            handler.sendEmptyMessageDelayed(3, 1600);
        }
    }

    //哭 4
    public void runFrameForCry(Context context, String name, ImageView imgPetZ) {
        img = imgPetZ;
        petid = name.substring(0, name.indexOf("_"));
        petdj = name.substring(name.indexOf("_") + 1);
        String allname = name + "_" + "kurl" + "_";
        //完全编码实现的动画效果
//        AnimationDrawable anim = new AnimationDrawable();
        if (anim != null && anim.isRunning()) {
            anim.stop();
            anim = null;
        }
        anim = new AnimationDrawable();


        if (null != getDrawable(context, allname + 1))
            anim.addFrame(getDrawable(context, allname + 1), 2000);

        if (null != getDrawable(context, allname + 2))
            anim.addFrame(getDrawable(context, allname + 2), 500);

        if (null != getDrawable(context, allname + 3))
            anim.addFrame(getDrawable(context, allname + 3), 1000);

        if (null != getDrawable(context, allname + 4))
            anim.addFrame(getDrawable(context, allname + 4), 1000);

        if (null != getDrawable(context, allname + 5))
            anim.addFrame(getDrawable(context, allname + 5), 1000);

//        if (null != getDrawable(context, name + "_" + "zyjurl" + "_" + 1))
//            anim.addFrame(getDrawable(context, name + "_" + "zyjurl" + "_" + 1), 100);

        anim.setOneShot(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imgPetZ.setBackground(anim);
        } else {
            imgPetZ.setBackgroundDrawable(anim);
        }
        if (handler != null) {
            anim.start();  //开始动画
            run = 4;
            MyLog.showLog("anim", "++哭++");
            handler.sendEmptyMessageDelayed(4, 5500);
        }
    }

    //笑 5
    public void runFrameForSmell(Context context, String name, ImageView imgPetZ) {
        img = imgPetZ;
        petid = name.substring(0, name.indexOf("_"));
        petdj = name.substring(name.indexOf("_") + 1);
        String allname = name + "_" + "xurl" + "_";
        //完全编码实现的动画效果
//        AnimationDrawable anim = new AnimationDrawable();
        if (anim != null && anim.isRunning()) {
            anim.stop();
            anim = null;
        }
        anim = new AnimationDrawable();


        if (null != getDrawable(context, allname + 1))
            anim.addFrame(getDrawable(context, allname + 1), 200);

        if (null != getDrawable(context, allname + 2))
            anim.addFrame(getDrawable(context, allname + 2), 500);

        if (null != getDrawable(context, allname + 3))
            anim.addFrame(getDrawable(context, allname + 3), 1000);

        if (null != getDrawable(context, allname + 4))
            anim.addFrame(getDrawable(context, allname + 4), 500);

//        if (null != getDrawable(context, name + "_" + "zyjurl" + "_" + 1))
//            anim.addFrame(getDrawable(context, name + "_" + "zyjurl" + "_" + 1), 100);

        anim.setOneShot(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imgPetZ.setBackground(anim);
        } else {
            imgPetZ.setBackgroundDrawable(anim);
        }
        if (handler != null) {
            anim.start();  //开始动画
            run = 5;
            MyLog.showLog("anim", "++笑++");
            handler.sendEmptyMessageDelayed(5, 2200);
        }
    }

    String petid;
    String petdj;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                if (MyApplication.getMyApplication().userInfo != null && petid != null && petdj != null) {
//                    Double d = 0.0;
//                    if ("".equals(MyApplication.getMyApplication().userInfo.getBscns())) {
//                        d = 0.0;
//                    } else {
//                        d = Double.parseDouble(MyApplication.getMyApplication().userInfo.getBscns());
//                    }
//
//                    int i = d.compareTo(30.00);
//
//                    if (MyApplication.getMyApplication().isFriend.equals("Y")) {
//                        runFrameForEye(context, petid + "_" + petdj, img);
//                    } else {
//                        if (i >= 0) {
//                            //饱食度大于30 眨眼
//                            runFrameForEye(context, petid + "_" + petdj, img);
//                            run = 1;
//                        } else {
//                            //饱食度小于30 哭
//                            runFrameForCry(context, petid + "_" + petdj, img);
//                            run = 4;
//                        }
//                    }
//                }
//            }
            switch (msg.what) {
                case 0:
                    if (run == 1 || run == 4) {
                        handler.removeMessages(0);
                        if (MyApplication.getMyApplication().userInfo != null && petid != null && petdj != null) {
                            Double d = 0.0;
                            if ("".equals(MyApplication.getMyApplication().userInfo.getBscns())) {
                                d = 0.0;
                            } else {
                                d = Double.parseDouble(MyApplication.getMyApplication().userInfo.getBscns());
                            }

                            int i = d.compareTo(30.00);
//                            anim.stop();
                            if (MyApplication.getMyApplication().isFriend.equals("Y")) {
                                runFrameForEye(context, petid + "_" + petdj, img);
                            } else {
                                if (i >= 0) {
                                    //饱食度大于30 眨眼
                                    runFrameForEye(context, petid + "_" + petdj, img);
                                } else {
                                    //饱食度小于30 哭
                                    runFrameForCry(context, petid + "_" + petdj, img);
                                }
                            }
                        }
                    }
                    break;
                case 1:
                    handler.removeMessages(1);
                    handler.sendEmptyMessage(0);
                    break;
                case 2:
                    handler.removeMessages(2);
                    run = 1;
                    handler.sendEmptyMessage(0);
                    break;
                case 3:
                    handler.removeMessages(3);
                    run = 1;
                    handler.sendEmptyMessage(0);
                    break;
                case 4:
                    handler.removeMessages(4);
                    handler.sendEmptyMessage(0);
                    break;
                case 5:
                    handler.removeMessages(5);
                    run = 1;
                    handler.sendEmptyMessage(0);
                    break;
            }
        }
    };

    public void stopAnima() {
        petid = null;
        petdj = null;
    }

}
