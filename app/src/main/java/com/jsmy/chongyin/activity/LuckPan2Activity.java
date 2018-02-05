package com.jsmy.chongyin.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.CJListBean;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.customclass.StrokeTextView;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LuckPan2Activity extends BaseActivity {

    @BindView(R.id.tv_shuangchou)
    TextView tvShuangchou;
    @BindView(R.id.img_zhuanpan)
    ImageView imgZhuanpan;
    @BindView(R.id.btn_begin)
    TextView btnBegin;
    @BindView(R.id.tv_sw)
    TextView tvSw;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.img_map)
    ImageView imgMap;
    @BindView(R.id.activity_luck_pan2)
    RelativeLayout activityLuckPan2;
    @BindView(R.id.img_get)
    ImageView imgGet;
    @BindView(R.id.img_shuiguo)
    ImageView imgShuiguo;
    @BindView(R.id.img_yuanbao)
    ImageView imgYuanbao;
    @BindView(R.id.img_n)
    ImageView imgN;
    @BindView(R.id.tv_n)
    StrokeTextView tvN;
    @BindView(R.id.rela_n)
    RelativeLayout relaN;
    @BindView(R.id.img_go)
    ImageView imgGo;
    @BindView(R.id.img_fet)
    ImageView imgFet;
    @BindView(R.id.tv_fet)
    StrokeTextView tvFet;
    @BindView(R.id.rela_fet)
    RelativeLayout relaFet;
    @BindView(R.id.img_fet1)
    ImageView imgFet1;
    @BindView(R.id.tv_fet1)
    StrokeTextView tvFet1;
    @BindView(R.id.rela_fet1)
    RelativeLayout relaFet1;

    private PathMeasure mPathMeasure;
    //* 贝塞尔曲线中间过程的点的坐标
    private float[] mCurrentPosition = new float[2];

    private List<CJListBean.DataBean.ListBean> listBeen;

    private int time;

    private boolean isRunning = false;

    private boolean isFrist = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_luck_pan2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getCJList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_CJ_LIST_NUM:
                    if (!isRunning) {
                        listBeen = gson.fromJson(result, CJListBean.class).getData().getList();
                        switch (listBeen.get(0).getVtwolx()) {
                            case "1":
                                tvShuangchou.setText("免费抽奖");
                                break;
                            case "2":
                                tvShuangchou.setText("周末双抽");
                                break;
                            case "3":
                                tvShuangchou.setText("节日双抽");
                                break;
                        }
                        setTime(listBeen.get(0).getVdjs());
                    }
                    break;
                case ServiceCode.UP_DATE_CJ_NUM:
                    getCJList();
                    EventBus.getDefault().post(new DowloadEvent("cjtime", "cj"));
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    //访问抽奖列表
    private void getCJList() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_CJ_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //判断倒计时
    private void setTime(String djs) {
        if (isRunning)
            return;
        if (!"".equals(djs) && Integer.parseInt(djs) > 0) {
            time = Integer.parseInt(djs);
            btnBegin.setClickable(false);
            btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_dengdai);
            imgZhuanpan.setImageResource(R.mipmap.dazhuanpan_zhuanpan_dengdai);
//            btnBegin.setText("");
            startTimeTv();
        } else {
            time = 0;
            btnBegin.setClickable(true);
            btnBegin.setText("");
            btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_kaishi);
            imgZhuanpan.setImageResource(R.mipmap.dazhuanpan_zhuanpan_jinxingzhong_kaishi);
        }
//        btnBegin.setClickable(true);
//        btnBegin.setText("");
//        btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_kaishi);
//        imgZhuanpan.setImageResource(R.mipmap.dazhuanpan_zhuanpan_jinxingzhong_kaishi);

    }

    //中奖
    private void getZJ(int vxh, int vxh2) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("vxh", vxh + "");
        map.put("vxh2", vxh2 + "");
        NetWork.getNetVolue(ServiceCode.UP_DATE_CJ, map, ServiceCode.NETWORK_GET, null);
    }

    //显示结果
    private void setImgJP(int vxh, int vxh2) {
        switch (vxh) {
            case 0:
                if (1 == vxh2) {
                    imgN.setImageResource(R.mipmap.shouye_yuanbao_iocn);
                } else if (2 == vxh2) {
                    imgN.setImageResource(R.mipmap.xiangjiao);
                } else {
                    imgN.setImageResource(R.mipmap.shiwufenlei_shuiguo);
                }
                tvN.setText("x" + listBeen.get(0).getVcns4());
                relaN.setVisibility(View.VISIBLE);
                break;
            case 1:
                imgShuiguo.setImageResource(R.mipmap.pg1);
                imgShuiguo.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgYuanbao.setImageResource(R.mipmap.yb23);
                imgYuanbao.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgShuiguo.setImageResource(R.mipmap.xj2);
                imgShuiguo.setVisibility(View.VISIBLE);
                break;
            case 4:
                imgYuanbao.setImageResource(R.mipmap.yb3);
                imgYuanbao.setVisibility(View.VISIBLE);
                break;
            case 5:
                imgShuiguo.setImageResource(R.mipmap.pg1);
                imgShuiguo.setVisibility(View.VISIBLE);
                break;
            case 6:
                imgYuanbao.setImageResource(R.mipmap.yb46);
                imgYuanbao.setVisibility(View.VISIBLE);
                break;
            case 7:
                imgShuiguo.setImageResource(R.mipmap.xj2);
                imgShuiguo.setVisibility(View.VISIBLE);
                break;
            case 8:
                imgYuanbao.setImageResource(R.mipmap.yb5);
                imgYuanbao.setVisibility(View.VISIBLE);
                break;
        }
    }

    ObjectAnimator wait;
    ObjectAnimator alpha;

    //go放大动画
    private void scakeView(final View view) {
        isRunning = true;
        wait = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f, 0f);
        wait.setDuration(250);
        wait.setRepeatCount(0);
        wait.setInterpolator(new AccelerateInterpolator());
        wait.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
                choiseJP(listBeen.get(0).getVxh(), listBeen.get(0).getVxh2());
//                choiseJP(0, 1);
            }

        });

        alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        alpha.setDuration(250);
        alpha.setRepeatCount(0);
        alpha.setInterpolator(new AccelerateInterpolator());
        alpha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

//                view.setVisibility(View.INVISIBLE);
//                choiseJP(listBeen.get(0).getVxh(), listBeen.get(0).getVxh2());
//                choiseJP(0, 1);

            }
        });

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 8f);
        scaleX.setDuration(200);
        scaleX.setRepeatCount(0);
        scaleX.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 8f);
        scaleY.setDuration(200);
        scaleY.setRepeatCount(0);
        scaleY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet set1 = new AnimatorSet();
        set1.play(scaleX).with(scaleY).with(wait);
        set1.start();
    }

    /**
     * 第1个 类型 0-8
     * 第2个 类型 0中的 1元宝 2香蕉 3苹果
     */
    private void choiseJP(int vxh, int vxh2) {
        MyLog.showLog("JJJ", "- " + vxh + " - " + vxh2);
        switch (vxh) {
            case 0:
                rotateAnim(360, vxh, vxh2);
                break;
            case 1:
//                rotateAnim((float) 31.25, vxh, vxh2);
                rotateAnim((float) 328.75, vxh, vxh2);
                break;
            case 2:
//                rotateAnim((float) 73.75, vxh, vxh2);
                rotateAnim((float) 286.25, vxh, vxh2);
                break;
            case 3:
//                rotateAnim((float) 116.25, vxh, vxh2);
                rotateAnim((float) 243.75, vxh, vxh2);
                break;
            case 4:
//                rotateAnim((float) 158.75, vxh, vxh2);
                rotateAnim((float) 201.25, vxh, vxh2);
                break;
            case 5:
//                rotateAnim((float) 201.25, vxh, vxh2);
                rotateAnim((float) 158.75, vxh, vxh2);
                break;
            case 6:
//                rotateAnim((float) 243.75, vxh, vxh2);
                rotateAnim((float) 116.25, vxh, vxh2);
                break;
            case 7:
//                rotateAnim((float) 286.25, vxh, vxh2);
                rotateAnim((float) 73.75, vxh, vxh2);
                break;
            case 8:
//                rotateAnim((float) 328.75, vxh, vxh2);
                rotateAnim((float) 31.25, vxh, vxh2);
                break;
        }

    }

    private void rotateAnim(float rotation, final int vxh, final int vxh2) {
        btnBegin.setClickable(false);
        btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_jinxingzhong);
        /** 设置旋转动画 */
        final ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgZhuanpan, "rotation", 0F, 360F).setDuration(2000);//360度旋转
        animator1.setInterpolator(new AccelerateInterpolator());

        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgZhuanpan, "rotation", 0F, 360F).setDuration(200);//360度旋转
        animator2.setInterpolator(new LinearInterpolator());
        animator2.setRepeatCount(10);

        final ObjectAnimator animator3 = ObjectAnimator.ofFloat(imgZhuanpan, "rotation", 0F, rotation + 360 * 16).setDuration(5000);//360度旋转
        animator3.setInterpolator(new AccelerateDecelerateInterpolator());

//        AnimatorSet set = new AnimatorSet();
//        set.play(animator2).after(animator1).before(animator3);
//        set.start();
//        animator1.start();
//        animator1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                animator2.start();
//                btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_jinxingzhong);
//            }
//        });
//        animator2.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                animator3.start();
//            }
//        });
//
        animator3.start();

//        set.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                switch (vxh) {
//                    case 0:
//                        if (1 == vxh2) {
//                            addCart(imgGet, tvGold, vxh, vxh2);
//                        } else {
//                            addCart(imgGet, tvSw, vxh, vxh2);
//                        }
//                        break;
//                    case 1:
//                        addCart(imgGet, tvSw, vxh, vxh2);
//                        break;
//                    case 2:
//                        addCart(imgGet, tvGold, vxh, vxh2);
//                        break;
//                    case 3:
//                        addCart(imgGet, tvSw, vxh, vxh2);
//                        break;
//                    case 4:
//                        addCart(imgGet, tvGold, vxh, vxh2);
//                        break;
//                    case 5:
//                        addCart(imgGet, tvSw, vxh, vxh2);
//                        break;
//                    case 6:
//                        addCart(imgGet, tvGold, vxh, vxh2);
//                        break;
//                    case 7:
//                        addCart(imgGet, tvSw, vxh, vxh2);
//                        break;
//                    case 8:
//                        addCart(imgGet, tvGold, vxh, vxh2);
//                        break;
//                }
//            }
//        });

        animator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                switch (vxh) {
                    case 0:
                        if (1 == vxh2) {
                            if (isFrist) {
                                addCartMore(tvGold, vxh2);
                            } else {
                                addCartMore1(tvGold, vxh2);
                            }

                        } else {
                            if (isFrist) {
                                addCartMore(tvSw, vxh2);
                            } else {
                                addCartMore1(tvSw, vxh2);
                            }
                        }
                        break;
                    case 1:
                        addCart(imgGet, tvSw, vxh, vxh2);
                        break;
                    case 2:
                        addCart(imgGet, tvGold, vxh, vxh2);
                        break;
                    case 3:
                        addCart(imgGet, tvSw, vxh, vxh2);
                        break;
                    case 4:
                        addCart(imgGet, tvGold, vxh, vxh2);
                        break;
                    case 5:
                        addCart(imgGet, tvSw, vxh, vxh2);
                        break;
                    case 6:
                        addCart(imgGet, tvGold, vxh, vxh2);
                        break;
                    case 7:
                        addCart(imgGet, tvSw, vxh, vxh2);
                        break;
                    case 8:
                        addCart(imgGet, tvGold, vxh, vxh2);
                        break;
                }

            }
        });

    }

    @OnClick({R.id.btn_begin, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_begin:
                if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
//                    Toast.makeText(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态", Toast.LENGTH_LONG).show();
                    ToastUtil.showShort(MyApplication.getMyApplication(),"网络链接异常，请检查网络状态!");
                    return;
                }
                setCJ();
                btnBegin.setClickable(false);
                break;
            case R.id.img_close:
                if (!isRunning)
                    finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isRunning)
            return;
        super.onBackPressed();
    }

    private void setCJ() {

        if (listBeen != null && "Y".equals(listBeen.get(0).getViskcj())) {
            if ("Y".equals(listBeen.get(0).getVisygg())) {
                //有广告，分vip与非vip
                if ("0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) || "Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                    //非VIP看广告
                    gotoAdvertisementActivity("Y");
                } else {
                    //vip看宣传册
                    gotoAdvertisementActivity("N");
//                    btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_jinxingzhong);
//                    imgGo.setVisibility(View.VISIBLE);
//                    scakeView(imgGo);
                }
            } else {
                //无广告，全部看宣传册
                gotoAdvertisementActivity("N");
//                btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_jinxingzhong);
//                imgGo.setVisibility(View.VISIBLE);
//                scakeView(imgGo);
            }
        } else {

        }

//        btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_jinxingzhong);
//        imgGo.setVisibility(View.VISIBLE);
//        scakeView(imgGo);

    }

    //跳转广告页
    private void gotoAdvertisementActivity(String gd) {
        Intent intent = new Intent(this, Advertisement2Activity.class);
        intent.putExtra("gd",gd);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MyLog.showLog("CJCJCJ", "------  ------------");
        btnBegin.setBackgroundResource(R.mipmap.dazhuanpan_zhizhen_jinxingzhong);
        imgGo.setVisibility(View.VISIBLE);
        scakeView(imgGo);
//        choiseJP(listBeen.get(0).getVxh(), listBeen.get(0).getVxh2());
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            btnBegin.setText(String.format("%02d:%02d", time / 60, time % 60));
            time--;
            handler.removeMessages(0);
            handler.postDelayed(runnable, 1000);
        }
    };

    //启动倒数时间
    public void startTimeTv() {
        handler.postDelayed(runnable, 0);
    }

    //显示倒数时间
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if (time > 0) {
                handler.sendEmptyMessage(0);
            } else {
                getCJList();
                handler.removeCallbacks(this);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    /**
     * ★★★★★把商品添加到购物车的动画效果★★★★★
     *
     * @param iv
     */
    private void addCart(ImageView iv, View view, final int lx, int vxh2) {

//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)

        final ImageView goods = new ImageView(LuckPan2Activity.this);
//        goods.setImageDrawable(iv.getDrawable());
        switch (lx) {
            case 0:
//                if (1 == vxh2) {
//                    goods.setImageResource(R.mipmap.yb46);
//                } else if (2 == vxh2) {
//                    goods.setImageResource(R.mipmap.xj2);
//                } else {
//                    goods.setImageResource(R.mipmap.pg1);
//                }

//                if (1 == vxh2) {
//                    imgN.setImageResource(R.mipmap.shouye_yuanbao_iocn);
//                } else if (2 == vxh2) {
//                    imgN.setImageResource(R.mipmap.xiangjiao);
//                } else {
//                    imgN.setImageResource(R.mipmap.shiwufenlei_shuiguo);
//                }
//                tvN.setText("x" + listBeen.get(0).getVcns4());
//                relaFet.setVisibility(View.VISIBLE);
                break;
            case 1:
                goods.setImageResource(R.mipmap.pg1);
                break;
            case 2:
                goods.setImageResource(R.mipmap.yb23);
                break;
            case 3:
                goods.setImageResource(R.mipmap.xj2);
                break;
            case 4:
                goods.setImageResource(R.mipmap.yb3);
                break;
            case 5:
                goods.setImageResource(R.mipmap.pg1);
                break;
            case 6:
                goods.setImageResource(R.mipmap.yb46);
                break;
            case 7:
                goods.setImageResource(R.mipmap.xj2);
                break;
            case 8:
                goods.setImageResource(R.mipmap.yb5);
                break;
        }
//        goods.setImageBitmap(bitmap);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(UtilsTools.dip2px(this, 50), UtilsTools.dip2px(this, 50));

//        if (lx == 0) {
////            activityLuckPan2.addView(relaFet, params);
//            relaFet.setVisibility(View.VISIBLE);
//        } else {
        activityLuckPan2.addView(goods, params);
//        }

//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        activityLuckPan2.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
//        if (lx == 0) {
//            relaFet.getLocationInWindow(startLoc);
//        } else {
        iv.getLocationInWindow(startLoc);
//        }

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        view.getLocationInWindow(endLoc);

//        三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
//        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
//        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;

        float startX = startLoc[0] - parentLocation[0];
        float startY = startLoc[1] - parentLocation[1];

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
//        float toX = endLoc[0] - parentLocation[0] + view.getWidth() / 5;
//        float toY = endLoc[1] - parentLocation[1];
        float toX = endLoc[0] - 30;
        float toY = endLoc[1] - 30;


//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线

        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);

        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环

        mPathMeasure = new PathMeasure(path, false);

        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new AccelerateInterpolator(3f));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）

                float value = (Float) animation.getAnimatedValue();

                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。

                mPathMeasure.getPosTan(value, mCurrentPosition, null);

                //mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
//                if (lx == 0) {
//                    relaFet.setTranslationX(mCurrentPosition[0]);
//                    relaFet.setTranslationY(mCurrentPosition[1]);
//                } else {
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
//                }

            }
        });
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;

//        if (lx == 0) {
//            scaleX = ObjectAnimator.ofFloat(relaFet, "scaleX", 2f, 0f);
//            scaleX.setDuration(200);
//            scaleY = ObjectAnimator.ofFloat(relaFet, "scaleY", 2f, 0f);
//            scaleY.setDuration(200);
//        } else {
        scaleX = ObjectAnimator.ofFloat(goods, "scaleX", 2f, 0f);
        scaleX.setDuration(200);
        scaleY = ObjectAnimator.ofFloat(goods, "scaleY", 2f, 0f);
        scaleY.setDuration(200);
//        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX).with(scaleY).after(valueAnimator);

//      五、 开始执行动画
//        valueAnimator.start();
        animatorSet.start();
//      六、动画结束后的处理
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        scaleX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                if (lx == 0) {
//                    activityLuckPan2.removeView(relaFet);
//                    relaFet.setVisibility(View.GONE);
//                } else {
                activityLuckPan2.removeView(goods);
//                }
//                setImgJP(listBeen.get(0).getVxh(), listBeen.get(0).getVxh2());
                getZJ(listBeen.get(0).getVxh(), listBeen.get(0).getVxh2());
                isRunning = false;
            }
        });
    }

    private void addCartMore(View view, int vxh2) {
        isFrist = false;
        if (1 == vxh2) {
            imgFet.setImageResource(R.mipmap.shouye_yuanbao_iocn);
        } else if (2 == vxh2) {
            imgFet.setImageResource(R.mipmap.xiangjiao);
        } else {
            imgFet.setImageResource(R.mipmap.shiwufenlei_shuiguo);
        }
        tvFet.setText("x" + listBeen.get(0).getVcns4());
        relaFet.setVisibility(View.VISIBLE);
        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        relaFet.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        view.getLocationInWindow(endLoc);

        int endX = endLoc[0] - startLoc[0];
        int endY = endLoc[1] - startLoc[1] - 10;

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(relaFet, "translationX", 0, endX);
        animatorX.setDuration(1500);
        animatorX.setInterpolator(new AccelerateInterpolator(3f));
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(relaFet, "translationY", 0, endY);
        animatorY.setDuration(1500);
        animatorY.setInterpolator(new AccelerateInterpolator(3f));
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.play(animatorX).with(animatorY);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(relaFet, "scaleX", 2f, 0.1f);
        scaleX.setDuration(200);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(relaFet, "scaleY", 2f, 0.1f);
        scaleY.setDuration(200);
        final AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(scaleX).with(scaleY);


        animatorSet1.start();
        animatorX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet2.start();
            }
        });
        scaleX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                relaFet.setVisibility(View.INVISIBLE);
                getZJ(listBeen.get(0).getVxh(), listBeen.get(0).getVxh2());
                isRunning = false;
            }
        });


    }

    private void addCartMore1(View view, int vxh2) {
        if (1 == vxh2) {
            imgFet1.setImageResource(R.mipmap.shouye_yuanbao_iocn);
        } else if (2 == vxh2) {
            imgFet1.setImageResource(R.mipmap.xiangjiao);
        } else {
            imgFet1.setImageResource(R.mipmap.shiwufenlei_shuiguo);
        }
        tvFet1.setText("x" + listBeen.get(0).getVcns4());
        relaFet1.setVisibility(View.VISIBLE);
        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        relaFet1.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        view.getLocationInWindow(endLoc);

        int endX = endLoc[0] - startLoc[0];
        int endY = endLoc[1] - startLoc[1] - 10;

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(relaFet1, "translationX", 0, endX);
        animatorX.setDuration(1500);
        animatorX.setInterpolator(new AccelerateInterpolator(3f));
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(relaFet1, "translationY", 0, endY);
        animatorY.setDuration(1500);
        animatorY.setInterpolator(new AccelerateInterpolator(3f));
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.play(animatorX).with(animatorY);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(relaFet1, "scaleX", 2f, 0.1f);
        scaleX.setDuration(200);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(relaFet1, "scaleY", 2f, 0.1f);
        scaleY.setDuration(200);
        final AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(scaleX).with(scaleY);


        animatorSet1.start();
        animatorX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet2.start();
            }
        });
        scaleX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                relaFet1.setVisibility(View.INVISIBLE);
                getZJ(listBeen.get(0).getVxh(), listBeen.get(0).getVxh2());
                isRunning = false;
            }
        });


    }

}
