package com.jsmy.chongyin.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.BackgroudRecyclerAdapter2;
import com.jsmy.chongyin.adapter.EmojiAdapter;
import com.jsmy.chongyin.adapter.FoodRecyclerAdapter2;
import com.jsmy.chongyin.adapter.FrendRecyclerAdapter2;
import com.jsmy.chongyin.adapter.QuickPageAdapter;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.ApplistBean;
import com.jsmy.chongyin.bean.BJTPBean;
import com.jsmy.chongyin.bean.CJListBean;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.FJDRBean;
import com.jsmy.chongyin.bean.FriendBean;
import com.jsmy.chongyin.bean.FriendListBean;
import com.jsmy.chongyin.bean.FriendPetThingBean;
import com.jsmy.chongyin.bean.HyPetList;
import com.jsmy.chongyin.bean.JCSJBean;
import com.jsmy.chongyin.bean.LogSxxBean;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.bean.MyFoodBean;
import com.jsmy.chongyin.bean.MyPetThing2Bean;
import com.jsmy.chongyin.bean.MyPetThingBean;
import com.jsmy.chongyin.bean.PetTPSiseBean;
import com.jsmy.chongyin.bean.PetTpListBean;
import com.jsmy.chongyin.bean.TJYHBean;
import com.jsmy.chongyin.bean.VisionBean;
import com.jsmy.chongyin.bean.WeiDuDanmuBean;
import com.jsmy.chongyin.bean.ZSYXXBean;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.customclass.BackgroudWindow;
import com.jsmy.chongyin.customclass.DrawerDialog3;
import com.jsmy.chongyin.customclass.DrawerDialog4;
import com.jsmy.chongyin.customclass.DrawerDialog5;
import com.jsmy.chongyin.customclass.DrawerWindow2;
import com.jsmy.chongyin.customclass.FoodWindow;
import com.jsmy.chongyin.customclass.FrendWindow;
import com.jsmy.chongyin.listener.IPermission;
import com.jsmy.chongyin.service.DownLoadService;
import com.jsmy.chongyin.service.FloatWindowService;
import com.jsmy.chongyin.service.LocationBaiDuService;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.ChartUtils;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.EmjoyUtil;
import com.jsmy.chongyin.utils.ExpressionUtil;
import com.jsmy.chongyin.utils.KeybordS;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UpdateManage;
import com.jsmy.chongyin.utils.UtilsTools;
import com.jsmy.chongyin.view.CircleImageView;
import com.jsmy.chongyin.view.RefreshRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends BaseActivity implements NetWork.CallListener, View.OnLayoutChangeListener {

    @BindView(R.id.tv_lv)
    TextView tvLv;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.tv_sw)
    TextView tvSw;
    @BindView(R.id.img_gold)
    ImageView img1;
    @BindView(R.id.img_love1)
    ImageView img3;
    @BindView(R.id.img_love2)
    ImageView img2;
    @BindView(R.id.img_cm)
    ImageView img4;
    @BindView(R.id.activity_main)
    public RelativeLayout activityMain;
    @BindView(R.id.img_tx)
    CircleImageView imgTx;
    @BindView(R.id.img_pet_z)
    ImageView imgPetZ;
    @BindView(R.id.img_grally)
    ImageView imgGrally;
    @BindView(R.id.img_map)
    ImageView imgMap;

    @BindView(R.id.img_love3)
    ImageView img5;
    @BindView(R.id.img_cm2)
    ImageView img6;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.img_frend)
    CircleImageView imgFrend;
    @BindView(R.id.img_food)
    CircleImageView imgFood;
    @BindView(R.id.rela_main)
    RelativeLayout relaMain;

    @BindView(R.id.rela_frend)
    RelativeLayout relaFrend;
    @BindView(R.id.rela_dengji)
    RelativeLayout relaDengji;
    @BindView(R.id.tv_dengji)
    TextView tvDengji;
    @BindView(R.id.tv_jingyan)
    TextView tvJingyan;
    @BindView(R.id.rela_yuanbao)
    RelativeLayout relaYuanbao;
    @BindView(R.id.tv_yuanbao)
    TextView tvYuanbao;
    @BindView(R.id.img_cwtx)
    CircleImageView imgCwtx;
    @BindView(R.id.tv_cwnc)
    TextView tvCwnc;
    @BindView(R.id.img_hd)
    ImageView imgHd;
    @BindView(R.id.img_hand)
    ImageView imgHand;
    @BindView(R.id.tv_nc)
    TextView tvNc;

    @BindView(R.id.frend_img_tx1)
    CircleImageView frendImgTx1;
    @BindView(R.id.tv_huangguan1)
    TextView tvHuangguan1;
    @BindView(R.id.rela_huangguan1)
    RelativeLayout relaHuangguan1;
    @BindView(R.id.frend_img_cwtx1)
    CircleImageView frendImgCwtx1;
    @BindView(R.id.frend_tv_nc1)
    TextView frendTvNc1;
    @BindView(R.id.frend_tv_petnc1)
    TextView frendTvPetnc1;
    @BindView(R.id.img_paihang1)
    ImageView imgPaihang1;
    @BindView(R.id.frend_img_tx2)

    CircleImageView frendImgTx2;
    @BindView(R.id.tv_huangguan2)
    TextView tvHuangguan2;
    @BindView(R.id.rela_huangguan2)
    RelativeLayout relaHuangguan2;
    @BindView(R.id.frend_img_cwtx2)
    CircleImageView frendImgCwtx2;
    @BindView(R.id.frend_tv_petnc2)
    TextView frendTvPetnc2;
    @BindView(R.id.img_paihang2)
    ImageView imgPaihang2;
    @BindView(R.id.frend_tv_nc2)
    TextView frendTvNc2;
    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.img_mosce)
    public ImageView imgMosce;
    @BindView(R.id.probar_sw)
    ProgressBar probarSw;
    @BindView(R.id.probar_lv)
    ProgressBar probarLv;

    public boolean eat = true;
    @BindView(R.id.textView)
    public TextView textView;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.rela_vip)
    RelativeLayout relaVip;
    @BindView(R.id.img_fj)
    CircleImageView imgFj;
    @BindView(R.id.img_dj)
    ImageView imgDj;
    @BindView(R.id.img_huangguan1)
    ImageView imgHuangguan1;
    @BindView(R.id.img_huangguan2)
    ImageView imgHuangguan2;
    @BindView(R.id.img_hand2)
    ImageView imgHand2;
    @BindView(R.id.img_dian)
    CircleImageView imgDian;

    @BindView(R.id.danmu_1)
    RelativeLayout danmu1;
    @BindView(R.id.danmu_2)
    RelativeLayout danmu2;
    @BindView(R.id.danmu_3)
    RelativeLayout danmu3;
    @BindView(R.id.danmu_4)
    RelativeLayout danmu4;
    @BindView(R.id.danmu_5)
    RelativeLayout danmu5;
    @BindView(R.id.rela_danmu)
    LinearLayout relaDanmu;

    @BindView(R.id.danmu_1_f)
    RelativeLayout danmu1F;
    @BindView(R.id.danmu_2_f)
    RelativeLayout danmu2F;
    @BindView(R.id.danmu_3_f)
    RelativeLayout danmu3F;
    @BindView(R.id.danmu_4_f)
    RelativeLayout danmu4F;
    @BindView(R.id.danmu_5_f)
    RelativeLayout danmu5F;
    @BindView(R.id.rela_danmu_f)
    LinearLayout relaDanmuF;


    private boolean isFirst = true;

    private Timer timer;
    private Timer timerCJ;

    private boolean isDowload = true;

    public boolean isHyTp = false;

    public boolean isUpLevel = false;

    public int friendOldPosition;

    public int friendPosition;

    public int foodPosition;

    public boolean isHD = true;

    public boolean isJiazai = false;

    public boolean isMainShow = true;

    public boolean isAnimoRuning = false;

    public JCSJBean.DataBean bean;
    public List<FriendListBean.DataBean.ListBean> friendList;
    public List<MyFoodBean.DataBean.ListBean> foodList;
    public List<BJTPBean.DataBean.ListBean> bjtpList;
    public FriendBean.DataBean friendBean;
    private FriendPetThingBean.DataBean friendPetBean;
    private List<MyPetThingBean.DataBean.ListBean> myPetThingList;
    public List<MyPetThing2Bean> list = new ArrayList<>();
    public List<CJListBean.DataBean.ListBean> listBeen;
    private List<PetTpListBean.DataBean.ListBean> petTpList;
    private List<HyPetList.DataBean.ListBean> hyPetList;
    private FJDRBean.DataBean fjdr;
    private PetTPSiseBean.DataBean dataBean;
    private List<ApplistBean.DataBean.ListBean> applistBeen;
    private int sjFJDR;
    public int position;
    public boolean isBackFirst = true;

    private LogSxxBean.DataBean logsxx;

    private String bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        creatFolder();
        openTimer();
        getBuildVERSION();
//        setLocation(this);
        relaBottom.addOnLayoutChangeListener(this);
    }

    @Override
    protected void initData() {
        setPerAndPet();
        getBJTP();
        getMyFriendList();
        getMyFood();
        getSJWPList();
        getJCSJ();
        getCJList();
        getAppVision();
        openDeskPet("N");
//        getFJDR();
        getApplyList();
        if ("".equals(SharePrefUtil.getString(this, "mission", ""))) {
            if ("".equals(MyApplication.getMyApplication().userInfo.getMm()) || "".equals(MyApplication.getMyApplication().userInfo.getDh())) {
                showDialogMissionComple();
            }
            SharePrefUtil.saveString(this, "mission", "mission");
        } else {

        }

        NetWork.getNoreadlylList(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), this);
        NetWork.gettjyhList(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", this);
    }

    @Override
    protected void paresData(String result, String code) {
        switch (code) {
            case "haoyou":
                getMyFriendList();
                getApplyList();
                break;
            case "close":
                handler.sendEmptyMessageDelayed(33, 2000);
                getLoginDate();
                getJCSJ();
                break;
            case "cj":
                getJCSJ();
                if (MyApplication.getMyApplication().time > 0) {
                    tvTime.setVisibility(View.VISIBLE);
                    imgHand.setVisibility(View.GONE);
                    handler.postDelayed(runnable, 0);
                } else {
                    tvTime.setVisibility(View.GONE);
                    imgHand.setVisibility(View.VISIBLE);
                    scakeView(imgHand);
                }
                getMyFood();
                break;
            case "Y":
                seletData(result);
                break;
            case "change":
                getLoginDate();
                break;
            case "pet":
                showSetPet(result);
                break;
            case Constants.LOCATION:

                break;
            case "refresh":
                if (frendwindow != null) {
                    showFrend(false);
                    frendwindow.dismiss();
                    Message message = new Message();
                    message.what = 100;
                    Bundle bundle = new Bundle();
                    bundle.putString("petid", MyApplication.getMyApplication().userInfo.getPetid() + "");
                    bundle.putString("petdj", MyApplication.getMyApplication().userInfo.getPetdj() + "");
                    message.setData(bundle);
                    handler.sendMessageDelayed(message, 100);
                }
                getMyFriendList();
                break;
            case "petnc":
                String nc = result;
                if (null != nc && !"".equals(nc)) {
                    showDialogChangePetNc(nc);
                }
                break;
            case "yuanbao":
                getJCSJ();
                break;
            case "download":
                MyLog.showLog("AAA", "result = " + result);
                if ("main".equals(result)) {
                    handler.sendEmptyMessage(101);
                    if (num < 4 && isHyTp) {
                        num++;
                        tpDowloadHy(num);
                    } else if (num < 25 && !isHyTp) {
                        num++;
                        tpDowload(num);
                    } else {
                        num = 1;
                        isHyTp = false;
                        closeDowload();
                        if (dialog != null)
                            dialog.dismiss();
                        isFirst = true;
                        if (isFirst) {
                            MyLog.showLog("OOO", "- " + MyApplication.getMyApplication().isFriend + " -");
                            Message message = new Message();
                            message.what = 100;
                            Bundle bundle = new Bundle();
                            if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                                bundle.putString("petid", friendBean.getYhidspetid() + "");
                                bundle.putString("petdj", friendBean.getYhpetdj() + "");
                            } else {
                                getLoginDate();
                                getJCSJ();
                                bundle.putString("petid", MyApplication.getMyApplication().userInfo.getPetid() + "");
                                bundle.putString("petdj", MyApplication.getMyApplication().userInfo.getPetdj() + "");
                            }
                            message.setData(bundle);
                            handler.sendMessageDelayed(message, 100);
                            isFirst = false;
                            isDowload = false;
                        }
                    }
                }
                break;
            case "reflashpet":
                Message message = new Message();
                message.what = 100;
                Bundle b = new Bundle();
                b.putString("petid", MyApplication.getMyApplication().userInfo.getPetid() + "");
                b.putString("petdj", MyApplication.getMyApplication().userInfo.getPetdj() + "");
                message.setData(b);
                handler.sendMessageDelayed(message, 1000);
                break;
            case "JPush":
                getJCSJ();
                if ("huodong".equals(result)) {

                } else {
                    setMessage(result, true);
                }

                break;
            case "upbackgruop":
                MyLog.showLog("UUU", "-*----- upbackgruop -----*-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    activityMain.setBackground(Drawable.createFromPath(ServiceCode.BASE_PATH + ServiceCode.BASE_IMG_CROP));
//                    NetWork.setImgGlideBackgroud(this,ServiceCode.BASE_PATH + ServiceCode.BASE_IMG_CROP,activityMain);
                    MyLog.showLog("UUU", "-*----- 111 -----*-");
                } else {
                    activityMain.setBackgroundDrawable(Drawable.createFromPath(ServiceCode.BASE_PATH + ServiceCode.BASE_IMG_CROP));
//                    NetWork.setImgGlideBackgroud(this,ServiceCode.BASE_PATH + ServiceCode.BASE_IMG_CROP,activityMain);
                    MyLog.showLog("UUU", "-*----- 222 -----*-");
                }
                break;
            case "tx":

                break;
            case "network":
                MyLog.showLog("EEE", " - " + result);
                choseDialog(Integer.parseInt(result));
                break;
            case "danmu":
                NetWork.getNoreadlylList(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), this);
                break;
            case "food":
                if (listFood != null) {
                    listFood.clear();
                    listFood.addAll(gson.fromJson(result, MyFoodBean.class).getData().getList());
//            getData(true);
//            initRecycler();
                    adapterFood.clear();
                    adapterFood.addAll(listFood);
                }
                break;
            case "bjtp":
                if (bjtpList != null && listBack != null) {
                    listBack.clear();
                    listBack.addAll(bjtpList);
                    getDataBack(true);
                }
                break;
            default:
                break;
        }
    }

    //访问登录接口，更新数据
    private void getLoginDate() {
        String yhid = SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + "");
        MyLog.showLog("SSS", "yhid = " + yhid);
        String openid = SharePrefUtil.getString(this, "openid", "");
        MyLog.showLog("SSS", "openid = " + openid);
        String dh = SharePrefUtil.getString(this, "dh", MyApplication.getMyApplication().userInfo.getDh() + "");
        MyLog.showLog("SSS", "dh = " + dh);
        String mm = SharePrefUtil.getString(this, "mm", MyApplication.getMyApplication().userInfo.getMm() + "");
        MyLog.showLog("SSS", "mm = " + mm);
        map.clear();
        if (!"".equals(yhid) && !"".equals(openid)) {
            map.put("yhid", yhid);
            map.put("openid", openid);
            NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
        } else if (!"".equals(dh) && !"".equals(mm)) {
            map.put("dh", dh);
            map.put("mm", mm);
            NetWork.getNetVolue(ServiceCode.GET_DH_LOGIN, map, ServiceCode.NETWORK_GET, null);
        }
    }

    //访问基础数据
    private void getJCSJ() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SY_JCSJ, map, ServiceCode.NETWORK_GET, null);
    }

    //访问背景图
    private void getBJTP() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_BJTP_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //访问好友列表
    public void getMyFriendList() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_FRIEND_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //访问我的食物
    private void getMyFood() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_FOOD_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //喂养宠物
    public void setPetFood(String yhspid) {
        map.clear();
        map.put("yhpetid", MyApplication.getMyApplication().userInfo.getPetid() + "");
        map.put("yhid", MyApplication.getMyApplication().userInfo.getYhid() + "");
        map.put("yhspid", yhspid);
        NetWork.getNetVolue(ServiceCode.UP_DATE_PET_EAT, map, ServiceCode.NETWORK_GET, null);
    }

    //访问好友首页
    public void getMyFriend(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        NetWork.getNetVolue(ServiceCode.GET_FRIEND_SY, map, ServiceCode.NETWORK_GET, null);
    }

    //访问好友首页随机爱心
    public void getMyFriendLoveheart(String yhid) {
        map.clear();
        map.put("yhids", yhid);
        NetWork.getNetVolue(ServiceCode.GET_FRIEND_SYAX, map, ServiceCode.NETWORK_GET, null);
    }

    //帮好友收取爱心
    public void getBHYSAX(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        NetWork.getNetVolue(ServiceCode.UP_DATE_HYAX, map, ServiceCode.NETWORK_GET, null);
    }

    //偷取好友爱心
    public void getTQHYAX(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        NetWork.getNetVolue(ServiceCode.UP_DATE_HYSJAX, map, ServiceCode.NETWORK_GET, null);
    }

    //访问随机收获物品列表
    public void getSJWPList() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SJWP_list, map, ServiceCode.NETWORK_GET, null);
    }

    //访问收集随机物品
    public void getSJWP(String lx, String spid, String cns) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("lx", lx);
        map.put("spid", spid);
        map.put("cns", cns);
        NetWork.getNetVolue(ServiceCode.UP_DATE_SJWP, map, ServiceCode.NETWORK_GET, null);
    }

    //收取我的爱心
    public void getMyPetAx() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.UP_DATE_YHPET_AX, map, ServiceCode.NETWORK_GET, null);
    }

    //设置背景
    private void setBackLocation() {
        for (int i = 0; i < bjtpList.size(); i++) {
            if (MyApplication.getMyApplication().userInfo.getBjtpid().equals(bjtpList.get(i).getId() + "")) {
                MyLog.showLog("UUU", "-*----- 333 -----*-");
                NetWork.setImgGlideBackgroud(this, bjtpList.get(i).getTp(), activityMain);
            }
        }
    }

    //更换背景图
    public void setBJTP(String bjt, int position) {
        this.position = position;
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("bjt", bjt);
        NetWork.getNetVolue(ServiceCode.UP_DATE_BJTP, map, ServiceCode.NETWORK_GET, null);
    }

    //访问抽奖列表
    private void getCJList() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_CJ_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //访问登录接口
    private void loginData() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("openid", SharePrefUtil.getString(this, "openid", ""));
        NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
    }

    //版本对比
    public void getAppVision() {
        map.clear();
        NetWork.getNetVolue(ServiceCode.GET_APP, map, ServiceCode.NETWORK_GET, null);
    }

    //登录消息
    public void getLogsxx() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", ""));
        NetWork.getNetVolue(ServiceCode.GET_LOG_SXX, map, ServiceCode.NETWORK_GET, null);
    }

    //附近的人
    private void getFJDR() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("jd", MyApplication.getMyApplication().longitude);
        map.put("wd", MyApplication.getMyApplication().latitude);
        NetWork.getNetVolue(ServiceCode.GET_FJDR, map, ServiceCode.NETWORK_GET, null);
    }

    //删除背景图
    public void deleteBack(String bjtid) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("bjtid", bjtid);
        NetWork.getNetVolue(ServiceCode.UP_DATE_BJTP_DEL, map, ServiceCode.NETWORK_GET, null);
    }

    //分发接口数据
    private void seletData(String date) {
        String check = DecodeData.getCodeRoMsg(date, DecodeData.CHECK);
        switch (check) {
            case ServiceCode.GET_PET_TP_NUM:
                if (!isMainShow)
                    return;
                dataBean = gson.fromJson(date, PetTPSiseBean.class).getData();
                if (isUpLevel) {
                    isUpLevel = false;
                    showPetUpLevel(bean.getPetid(), UtilsTools.getPetDj(Integer.parseInt(bean.getPetdj())) + "");
                } else {
                    showWIFIdialog(petid, petdj, pro);
                }
                break;
            case ServiceCode.UP_DATE_BJTUSC_NUM:
//                Toast.makeText(this, "背景修改成功！", Toast.LENGTH_SHORT).show();
                ToastUtil.showShort(this, "背景修改成功！");
                isBackFirst = false;
//                loginData();
                getLoginDate();
                break;
            case ServiceCode.UP_DATE_TX_NUM:
                ToastUtil.showShort(this, "头像修改成功！");
                getLoginDate();
                break;
            case ServiceCode.UP_DATE_BJTP_DEL_NUM:
                isBackFirst = true;
                getLoginDate();
                break;
            case ServiceCode.GET_FJDR_NUM:
                fjdr = gson.fromJson(date, FJDRBean.class).getData();
//                setFJDR();
                break;
            case ServiceCode.GET_LOG_SXX_NUM:
                logsxx = gson.fromJson(date, LogSxxBean.class).getData();
                if (!"".equals(logsxx.getVlx1())) {
                    setMessage("今天每10分钟可以抽奖2次哟n(*≧▽≦*)n", true);
                    logsxx.setVlx1("今天每10分钟可以抽奖2次哟n(*≧▽≦*)n");
                } else if (!"".equals(logsxx.getVmc())) {
                    setMessage("点击左上角我的名字可以给我改名字哟", true);
                    logsxx.setVmc("");
                }

                break;
            case ServiceCode.GET_CJ_LIST_NUM:
                listBeen = gson.fromJson(date, CJListBean.class).getData().getList();
                setCJTime();
                break;
            case ServiceCode.GET_LOGIN_NUM:
                MyApplication.getMyApplication().userInfo = gson.fromJson(date, LoginBean.class).getData();
                isFirst = true;
                setPetChange();
                NetWork.setImgGlide(this, MyApplication.getMyApplication().userInfo.getYhtx(), imgTx);
                setPerAndPet();
                getBJTP();
                break;
            case ServiceCode.GET_DH_LOGIN_NUM:
                MyApplication.getMyApplication().userInfo = gson.fromJson(date, LoginBean.class).getData();
                isFirst = true;
                setPetChange();
                NetWork.setImgGlide(this, MyApplication.getMyApplication().userInfo.getYhtx(), imgTx);
                setPerAndPet();
                getBJTP();
                break;
            case ServiceCode.GET_SY_JCSJ_NUM:
                setPetLevel(date);
                break;
            case ServiceCode.GET_BJTP_LIST_NUM:
                bjtpList = gson.fromJson(date, BJTPBean.class).getData().getList();
                if (isBackFirst) {
                    setBackLocation();
                }
                EventBus.getDefault().post(new DowloadEvent(date, "bjtp"));
                break;
            case ServiceCode.GET_FRIEND_LIST_NUM:
                friendList = gson.fromJson(date, FriendListBean.class).getData().getList();
                if (isDelF) {
                    updataAll(friendList);
                } else if (friendBean != null) {
                    for (int i = 0; i < friendList.size(); i++) {
                        if (friendList.get(i).getYhid() == friendBean.getYhids()) {
                            friendList.get(i).setChosice(true);
//                            frendwindow.setChoise(friendList, friendPosition);
//                            drawerDialog.setChoise(friendList, friendPosition);
//                            drawerDialog4.setChoise(friendList, friendPosition);
                            setChoise(friendList, friendPosition);

                            break;
                        }
                    }
                }

                break;
            case ServiceCode.GET_FOOD_LIST_NUM:
                foodList = gson.fromJson(date, MyFoodBean.class).getData().getList();
                EventBus.getDefault().post(new DowloadEvent(date, "food"));
                break;
            case ServiceCode.GET_FRIEND_SY_NUM:
                friendBean = gson.fromJson(date, FriendBean.class).getData();
                getMyFriendLoveheart(friendBean.getYhids() + "");
                setFriendSY();
                break;
            case ServiceCode.GET_FRIEND_SYAX_NUM:
                friendPetBean = gson.fromJson(date, FriendPetThingBean.class).getData();
//                getMyFriendList();
                setFriendPetThing();
                imgPetZ.setClickable(true);
                break;
            case ServiceCode.GET_SJWP_list_NUM:
                myPetThingList = null;
                myPetThingList = gson.fromJson(date, MyPetThingBean.class).getData().getList();
                if (!"Y".equals(MyApplication.getMyApplication().isFriend))
                    setMyPetThing();
                imgPetZ.setClickable(true);
                break;
            case ServiceCode.UP_DATE_SJWP_NUM:
                getMyFood();
                getJCSJ();
                getSJWPList();
                break;
            case ServiceCode.UP_DATE_SP_SHOP_NUM:
                getJCSJ();
                getMyFood();
                break;
            case ServiceCode.UP_DATE_PET_NC_NUM:
                showDialogSucess("宠物昵称修改成功！");
                getLoginDate();
                getJCSJ();
                break;
            case ServiceCode.UP_DATE_FRIEND_DEL_NUM:
                isDelF = true;
                getMyFriendList();
                break;
            case ServiceCode.UP_DATE_FRIEND_SQ_NUM:
                getMyFriendList();
                break;
            case ServiceCode.UP_DATE_BJTP_NUM:
                isBackFirst = true;
//                loginData();
                getLoginDate();
                break;
            case ServiceCode.UP_DATE_WZ_NUM:

                break;
            case ServiceCode.UP_DATE_HYSJAX_NUM:
                getJCSJ();
                getMyFriendList();
                getMyFriendLoveheart(MyApplication.getMyApplication().haoyouID);
                break;
            case ServiceCode.UP_DATE_HYAX_NUM:
                getMyFriendLoveheart(MyApplication.getMyApplication().haoyouID);
                break;
            case ServiceCode.UP_DATE_PET_EAT_NUM:
                getJCSJ();
                getMyFood();
                break;
            case ServiceCode.UP_DATE_YHPET_AX_NUM:
                showAnimation(5, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());
                setMessage("谢谢你来看我(づ｡◕‿‿◕｡)づ", true);
                getSJWPList();
                break;
            case ServiceCode.GET_XZ_LIST_NUM:
                petTpList = gson.fromJson(date, PetTpListBean.class).getData().getList();
                if (isDowload)
                    tpDowload(num);
                break;
            case ServiceCode.GET_APP_NUM:
                VisionBean.DataBean vison = gson.fromJson(date, VisionBean.class).getData();
                new UpdateManage(this, vison.getApp(), vison.getMs(), vison.getIsqz());
                break;
            case ServiceCode.GET_HYPET_TP_LIST_NUM:
                hyPetList = gson.fromJson(date, HyPetList.class).getData().getList();
                if (isDowload)
                    tpDowloadHy(num);
                break;
            case ServiceCode.APPLY_LIST_NUM:
                applistBeen = gson.fromJson(date, ApplistBean.class).getData().getList();
                if (applistBeen != null && applistBeen.size() > 0) {
                    imgDian.setVisibility(View.VISIBLE);
                } else {
                    imgDian.setVisibility(View.GONE);
                }
                break;
            case ServiceCode.UPDATE_MM_NUM:
                if (bdd.equals("word")) {
                    if ("S".equals(DecodeData.getCodeRoMsg(date, DecodeData.CODE))) {
                        //修改密码获得元宝奖励
                        showDialogMissionComplePhone("设置登录密码");
                        getLoginDate();
                        getJCSJ();
                    }
                }
                break;
            case ServiceCode.UP_DATE_ZH_BD_NUM:
                if (bdd.equals("phone")) {
                    showDialogMissionComplePhone("");
                    getJCSJ();
                    getLoginDate();
                }
                break;
            default:
                break;
        }
    }

    //设置抽奖倒计时
    private void setCJTime() {
        if (!"".equals(listBeen.get(0).getVdjs()) && Integer.parseInt(listBeen.get(0).getVdjs()) > 0) {
            MyApplication.getMyApplication().time = Integer.parseInt(listBeen.get(0).getVdjs());
            if (MyApplication.getMyApplication().time > 0) {
                tvTime.setVisibility(View.VISIBLE);
                imgHand.setVisibility(View.GONE);
                handler.postDelayed(runnable, 0);
            } else {
                tvTime.setVisibility(View.GONE);
                imgHand.setVisibility(View.VISIBLE);
                scakeView(imgHand);
            }
        } else {
            MyApplication.getMyApplication().time = 0;
            tvTime.setVisibility(View.GONE);
            imgHand.setVisibility(View.VISIBLE);
            scakeView(imgHand);
        }

    }

    //设置我的头像昵称，宠物昵称
    private void setPerAndPet() {
        NetWork.setImgGlide(this, MyApplication.getMyApplication().userInfo.getYhtx(), imgTx);
        tvNc.setText(MyApplication.getMyApplication().userInfo.getYhnc());
        tvCwnc.setText(MyApplication.getMyApplication().userInfo.getPetnc());
        tvVip.setText("V" + MyApplication.getMyApplication().userInfo.getVipdj());
        if ("0".equals(MyApplication.getMyApplication().userInfo.getVipdj())) {
            relaVip.setVisibility(View.INVISIBLE);
        } else {
            relaVip.setVisibility(View.VISIBLE);
            if ("Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_guoqihuiyuan_da);
            } else {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_dengji_icon);
            }
        }

        if (!"".equals(MyApplication.getMyApplication().userInfo.getPetid())) {
//            NetWork.setImgGlideBackgroud(this, MyApplication.getMyApplication().userInfo.getTpurl(), imgPetZ);
            MyApplication.getMyApplication().drawable = imgPetZ.getDrawable();
        } else {
            imgPetZ.setBackgroundResource(R.mipmap.shouye_xinyonghu_chongwu);
        }
    }

    //设置宠物头像，等级经验，元宝，饱食度
    private void setPetLevel(String result) {
        bean = gson.fromJson(result, JCSJBean.class).getData();
        NetWork.setImgGlide(this, bean.getPettx(), imgCwtx);
        if ("".equals(bean.getPetdj())) {
            tvLv.setText("");
        } else {
            tvLv.setText("LV" + bean.getPetdj());
            if (UtilsTools.getPetDj(Integer.parseInt(bean.getPetdj())) > Integer.parseInt(SharePrefUtil.getString(this, bean.getPetid(), bean.getPetdj()))) {
                isUpLevel = true;
                getPetSise(bean.getPetid(), UtilsTools.getPetDj(Integer.parseInt(bean.getPetdj())) + "");
            }
            SharePrefUtil.saveString(this, bean.getPetid(), UtilsTools.getPetDj(Integer.parseInt(bean.getPetdj())) + "");
        }
        if ("".equals(bean.getDjcnsmax())) {
            probarLv.setMax(100);
        } else {
            probarLv.setMax((int) Double.parseDouble(bean.getDjcnsmax()));
        }
        if ("".equals(bean.getDjcns())) {
            probarLv.setProgress(0);
        } else {
            probarLv.setProgress((int) Double.parseDouble(bean.getDjcns()));
        }

        if (bean.getYbcns() > 999) {
            tvGold.setText("999");
        } else {
            tvGold.setText("" + bean.getYbcns());
        }
        MyApplication.getMyApplication().userInfo.setYbcns(bean.getYbcns());
        MyApplication.getMyApplication().userInfo.setBscns(bean.getBscns());
        if ("".equals(bean.getBscns())) {
            tvSw.setText("");
            probarSw.setProgress(0);
        } else {
            tvSw.setText((int) Double.parseDouble(bean.getBscns()) + "%");
            probarSw.setProgress((int) Double.parseDouble(bean.getBscns()));
            Double d = Double.parseDouble(bean.getBscns());
            int i = d.compareTo(30.0);
            int j = d.compareTo(90.0);
            if (i < 0) {
                setMessage("我饿了，快给我吃的/(ㄒoㄒ)/~~", true);
            } else if (j >= 0) {
                setMessage("我吃饱了，谢谢你O(∩_∩)O~", true);
            } else {
                getLogsxx();
            }
        }

        if ("".equals(bean.getTb())) {
            imgHd.setVisibility(View.GONE);
        } else {
            NetWork.setImgGlide(this, bean.getTb(), imgHd);
            if (isHD) {
                hdScale(imgHd);
                isHD = false;
            }
        }
//        hdScale(imgHd);

        //设置排名
        MyApplication.getMyApplication().pm = bean.getPm();
        tvDengji.setText("等级：" + bean.getPetdj());
        tvJingyan.setText("经验：" + bean.getDjcns() + "/" + bean.getDjcnsmax());
        tvYuanbao.setText("元宝：" + bean.getYbcns());
        eat = true;
    }

    //设置宠物
    public void setImgPetZ(String tx) {
        if (null != tx && !"".equals(tx)) {
//            NetWork.setImgGlideBackgroud(this, tx, imgPetZ);
        } else {
            imgPetZ.setBackgroundResource(R.mipmap.shouye_xinyonghu_chongwu);
        }
    }

    //设置我的宠物
    private void setPetZB(String lx, String tx, ImageView img) {
        //1 元宝 本地图片
        //2 爱心 本地图片
        //3 食物 网络图片
        switch (lx) {
            case "1":
                img.setImageResource(R.mipmap.shouye_yuanbao_iocn);
                break;
            case "2":
//                img.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                NetWork.setImgGlide(this, myPetThingList.get(0).getAxtx().trim().toString(), img);
                break;
            case "3":
                NetWork.setImgGlide(this, tx, img);
                break;
            default:

                break;
        }
    }

    //设置我的宠物周边物品
    public void setMyPetThing() {
        if (isAnimoRuning)
            return;
        setThingGone();
        if (myPetThingList != null && myPetThingList.size() > 0 && !"".equals(myPetThingList.get(0).getAxcns())) {
            if (Integer.parseInt(myPetThingList.get(0).getAxcns()) > 0) {
                imgHand2.setVisibility(View.VISIBLE);
                scakeView(imgHand2);
            } else {
                imgHand2.setVisibility(View.INVISIBLE);
            }
        }
//        imgFj.setVisibility(View.GONE);
        list.clear();
        for (int i = 0; i < myPetThingList.size(); i++) {
            for (int j = 0; j < myPetThingList.get(i).getCns(); j++) {
                if ("".equals(myPetThingList.get(i).getSpid())) {

                } else {
                    list.add(new MyPetThing2Bean(myPetThingList.get(i).getLx(), Integer.parseInt(myPetThingList.get(i).getSpid()), myPetThingList.get(i).getTx()));
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    setPetZB(list.get(i).getLx(), list.get(i).getTx(), img1);
                    img1.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    setPetZB(list.get(i).getLx(), list.get(i).getTx(), img2);
                    img2.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    setPetZB(list.get(i).getLx(), list.get(i).getTx(), img3);
                    img3.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    setPetZB(list.get(i).getLx(), list.get(i).getTx(), img4);
                    img4.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    setPetZB(list.get(i).getLx(), list.get(i).getTx(), img5);
                    img5.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    setPetZB(list.get(i).getLx(), list.get(i).getTx(), img6);
                    img6.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            if (i >= 6) {
                break;
            }
        }
//        setFJDR();
    }

    //设置好友宠物周边物品
    public void setFriendPetThing() {
        setThingGone();
//        imgFj.setVisibility(View.GONE);
        MyLog.showLog("EEE", "friendPetBean.getZbaxcns()" + friendPetBean.getZbaxcns());
        for (int i = 1; i <= friendPetBean.getZbaxcns(); i++) {
            switch (i) {
                case 1:
                    img1.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                    img1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    img2.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                    img2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    img3.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                    img3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    img4.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                    img4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    img5.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                    img5.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    img6.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
                    img6.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            if (i >= 6) {
                return;
            }
        }
    }

    //切换显示宠物时，先隐藏周边
    public void setThingGone() {
        img1.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);
        img4.setVisibility(View.GONE);
        img5.setVisibility(View.GONE);
        img6.setVisibility(View.GONE);
    }

    //显示附近的人
    public void setFJDR() {

//        if (fjdr == null)
//            return;
//
//        if (fjdr != null && "0".equals(fjdr.getVtx())) {
//            imgFj.setVisibility(View.GONE);
//        } else if (!"0".equals(fjdr.getVtx())) {
//            img2.setVisibility(View.INVISIBLE);
//            NetWork.setImgGlide(this, fjdr.getVtx(), imgFj);
//            imgFj.setVisibility(View.VISIBLE);
//        }

        if (tjyhList != null && tjyhList.size() > 0) {
            img2.setVisibility(View.INVISIBLE);
            NetWork.setImgGlide(this, tjyhList.get(0).getTx(), imgFj);
            imgFj.setVisibility(View.VISIBLE);
        } else {
//            imgFj.setVisibility(View.GONE);
        }

    }

    //设置好友首页
    public void setFriendSY() {
        if (friendBean != null) {
            NetWork.setImgGlide(this, friendBean.getTx(), frendImgTx1);
            MyLog.showLog("SSS", friendBean.getBjimg());
//        NetWork.setImgGlide(this, friendBean.getBjimg(), imgBg);
            MyLog.showLog("UUU", "-*--setFriendSY--- LLLLL -----*-");
            NetWork.setImgGlideBackgroud(this, friendBean.getBjimg(), activityMain);
            tvHuangguan1.setText("V" + friendBean.getYhsvipdj() + "");
            if (0 == Integer.parseInt(friendBean.getYhsvipdj())) {
                relaHuangguan1.setVisibility(View.INVISIBLE);
            } else {
                relaHuangguan1.setVisibility(View.VISIBLE);
                if ("Y".equals(friendBean.getIsgq())) {
                    imgHuangguan1.setImageResource(R.mipmap.yonghuzhongxin_guoqihuiyuan_da);
                } else {
                    imgHuangguan1.setImageResource(R.mipmap.yonghuzhongxin_dengji_icon);
                }
            }
            frendTvNc1.setText(friendBean.getNc());
            NetWork.setImgGlide(this, friendBean.getYhidspettx(), frendImgCwtx1);
            frendTvPetnc1.setText(friendBean.getYhidspetnc());
            if (!"".equals(friendBean.getYhidspetid() + "")) {
                if (checkFriendPetImg(friendBean.getYhidspetid() + "", UtilsTools.getPetDj(Integer.parseInt(friendBean.getYhpetdj())) + "") < 4) {
                    checkAnima(friendBean.getYhidspetid() + "", UtilsTools.getPetDj(Integer.parseInt(friendBean.getYhpetdj())) + "", 4);
                } else {
                    isHyTp = false;
                    showAnimation(1, friendBean.getYhidspetid() + "", friendBean.getYhpetdj() + "");
                }
            } else {
                imgPetZ.clearAnimation();
                imgPetZ.setBackgroundResource(R.mipmap.shouye_xinyonghu_chongwu);
                anima.stopAnima();

            }

        }
        NetWork.setImgGlide(this, MyApplication.getMyApplication().userInfo.getYhtx(), frendImgTx2);
        tvHuangguan2.setText("V" + MyApplication.getMyApplication().userInfo.getVipdj() + "");
        if (0 == Integer.parseInt(MyApplication.getMyApplication().userInfo.getVipdj())) {
            relaHuangguan2.setVisibility(View.INVISIBLE);
        } else {
            relaHuangguan2.setVisibility(View.VISIBLE);
            if ("Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                imgHuangguan2.setImageResource(R.mipmap.yonghuzhongxin_guoqihuiyuan_da);
            } else {
                imgHuangguan2.setImageResource(R.mipmap.yonghuzhongxin_dengji_icon);
            }
        }
        frendTvNc2.setText(MyApplication.getMyApplication().userInfo.getYhnc());
        NetWork.setImgGlide(this, bean.getPettx(), frendImgCwtx2);
        frendTvPetnc2.setText(MyApplication.getMyApplication().userInfo.getPetnc());

    }

    //查看好友资料页
    private void seeFriend(String id) {
        zt = one;
        type = DELETFREND;
        friendID = id;
        gotoSomeActivity(this, AtyTag.ATY_AddNewFrend, true);
    }

    //访问宠物下载列表
    public void getPetTpList(String petid, String petdj) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("petid", petid + "");
        map.put("petdj", petdj + "");
        NetWork.getNetVolue(ServiceCode.GET_XZ_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    public void getPetSise(String petid, String petdj) {
        map.clear();
        map.put("petid", petid);
        map.put("petdj", petdj);
        NetWork.getNetVolue(ServiceCode.GET_PET_TP, map, ServiceCode.NETWORK_GET, null);
    }

    //访问好友宠物下载列表
    public void getFriendPetList(String petid, String petdj) {
        map.clear();
        map.put("petid", petid);
        map.put("petdj", petdj);
        NetWork.getNetVolue(ServiceCode.GET_HYPET_TP_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //检查图片
    public int checkPetImg(String petid, String petdj) {
        int img = 0;
        String name = petid;

        File file = null;
        for (int i = 1; i < 26; i++) {
            switch (i) {
                case 1:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "1" + ".png");
                    break;
                case 2:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "2" + ".png");
                    break;
                case 3:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "3" + ".png");
                    break;
                case 4:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "4" + ".png");
                    break;
                case 5:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "curl" + "_" + "1" + ".png");
                    break;
                case 6:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "curl" + "_" + "2" + ".png");
                    break;
                case 7:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "curl" + "_" + "3" + ".png");
                    break;
                case 8:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "curl" + "_" + "4" + ".png");
                    break;
                case 9:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "curl" + "_" + "5" + ".png");
                    break;
                case 10:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "curl" + "_" + "6" + ".png");
                    break;
                case 11:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "curl" + "_" + "7" + ".png");
                    break;
                case 12:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "dzhurl" + "_" + "1" + ".png");
                    break;
                case 13:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "dzhurl" + "_" + "2" + ".png");
                    break;
                case 14:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "dzhurl" + "_" + "3" + ".png");
                    break;
                case 15:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "dzhurl" + "_" + "4" + ".png");
                    break;
                case 16:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "dzhurl" + "_" + "5" + ".png");
                    break;
                case 17:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "kurl" + "_" + "1" + ".png");
                    break;
                case 18:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "kurl" + "_" + "2" + ".png");
                    break;
                case 19:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "kurl" + "_" + "3" + ".png");
                    break;
                case 20:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "kurl" + "_" + "4" + ".png");
                    break;
                case 21:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "kurl" + "_" + "5" + ".png");
                    break;
                case 22:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "xurl" + "_" + "1" + ".png");
                    break;
                case 23:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "xurl" + "_" + "2" + ".png");
                    break;
                case 24:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "xurl" + "_" + "3" + ".png");
                    break;
                case 25:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "xurl" + "_" + "4" + ".png");
                    break;
                default:
                    break;
            }

            if (file != null && file.exists()) {
                img++;
            } else {

            }
        }
        return img;
    }

    public int checkFriendPetImg(String petid, String petdj) {
        int img = 0;
        String name = petid;
        File file = null;
        for (int i = 1; i < 26; i++) {
            switch (i) {
                case 1:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "1" + ".png");
                    break;
                case 2:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "2" + ".png");
                    break;
                case 3:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "3" + ".png");
                    break;
                case 4:
                    file = new File(ServiceCode.BASE_PATH + name + "_" + petdj + "_" + "zyjurl" + "_" + "4" + ".png");
                    break;
                default:
                    break;
            }
            if (file != null && file.exists()) {
                img++;
            } else {

            }
        }
        return img;
    }

    private int num = 1;

    //下载
    public void tpDowload(int i) {
        //眨眼 1-12
        //吃 13-33
        //打招呼 34-48
        //哭泣 49-63
        //笑 64-75
        Intent intent = new Intent(this, DownLoadService.class);
        intent.putExtra("petid", petTpList.get(0).getPetid() + "");
        switch (i) {
            case 1:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "1");
                intent.putExtra("url", petTpList.get(0).getZyjurl1());
                break;
            case 2:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "2");
                intent.putExtra("url", petTpList.get(0).getZyjurl2());
                break;
            case 3:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "3");
                intent.putExtra("url", petTpList.get(0).getZyjurl3());
                break;
            case 4:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "4");
                intent.putExtra("url", petTpList.get(0).getZyjurl4());
                break;
            case 5:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "curl");
                intent.putExtra("number", "1");
                intent.putExtra("url", petTpList.get(0).getCurl1());
                break;
            case 6:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "curl");
                intent.putExtra("number", "2");
                intent.putExtra("url", petTpList.get(0).getCurl2());
                break;
            case 7:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "curl");
                intent.putExtra("number", "3");
                intent.putExtra("url", petTpList.get(0).getCurl3());
                break;
            case 8:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "curl");
                intent.putExtra("number", "4");
                intent.putExtra("url", petTpList.get(0).getCurl4());
                break;
            case 9:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "curl");
                intent.putExtra("number", "5");
                intent.putExtra("url", petTpList.get(0).getCurl5());
                break;
            case 10:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "curl");
                intent.putExtra("number", "6");
                intent.putExtra("url", petTpList.get(0).getCurl6());
                break;
            case 11:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "curl");
                intent.putExtra("number", "7");
                intent.putExtra("url", petTpList.get(0).getCurl7());
                break;
            case 12:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "dzhurl");
                intent.putExtra("number", "1");
                intent.putExtra("url", petTpList.get(0).getDzhurl1());
                break;
            case 13:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "dzhurl");
                intent.putExtra("number", "2");
                intent.putExtra("url", petTpList.get(0).getDzhurl2());
                break;
            case 14:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "dzhurl");
                intent.putExtra("number", "3");
                intent.putExtra("url", petTpList.get(0).getDzhurl3());
                break;
            case 15:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "dzhurl");
                intent.putExtra("number", "4");
                intent.putExtra("url", petTpList.get(0).getDzhurl4());
                break;
            case 16:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "dzhurl");
                intent.putExtra("number", "5");
                intent.putExtra("url", petTpList.get(0).getDzhurl5());
                break;
            case 17:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "kurl");
                intent.putExtra("number", "1");
                intent.putExtra("url", petTpList.get(0).getKurl1());
                break;
            case 18:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "kurl");
                intent.putExtra("number", "2");
                intent.putExtra("url", petTpList.get(0).getKurl2());
                break;
            case 19:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "kurl");
                intent.putExtra("number", "3");
                intent.putExtra("url", petTpList.get(0).getKurl3());
                break;
            case 20:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "kurl");
                intent.putExtra("number", "4");
                intent.putExtra("url", petTpList.get(0).getKurl4());
                break;
            case 21:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "kurl");
                intent.putExtra("number", "5");
                intent.putExtra("url", petTpList.get(0).getKurl5());
                break;
            case 22:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "xurl");
                intent.putExtra("number", "1");
                intent.putExtra("url", petTpList.get(0).getXurl1());
                break;
            case 23:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "xurl");
                intent.putExtra("number", "2");
                intent.putExtra("url", petTpList.get(0).getXurl2());
                break;
            case 24:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "xurl");
                intent.putExtra("number", "3");
                intent.putExtra("url", petTpList.get(0).getXurl3());
                break;
            case 25:
                intent.putExtra("petdj", petTpList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "xurl");
                intent.putExtra("number", "4");
                intent.putExtra("url", petTpList.get(0).getXurl4());
                break;
        }
        intent.putExtra("item", "main");
        startService(intent);
    }

    public void tpDowloadHy(int i) {
        Intent intent = new Intent(this, DownLoadService.class);
        intent.putExtra("petid", hyPetList.get(0).getPetid() + "");
        switch (i) {
            case 1:
                intent.putExtra("petdj", hyPetList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "1");
                intent.putExtra("url", hyPetList.get(0).getZyjurl1());
                break;
            case 2:
                intent.putExtra("petdj", hyPetList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "2");
                intent.putExtra("url", hyPetList.get(0).getZyjurl2());
                break;
            case 3:
                intent.putExtra("petdj", hyPetList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "3");
                intent.putExtra("url", hyPetList.get(0).getZyjurl3());
                break;
            case 4:
                intent.putExtra("petdj", hyPetList.get(0).getPetdj() + "");
                intent.putExtra("bqing", "zyjurl");
                intent.putExtra("number", "4");
                intent.putExtra("url", hyPetList.get(0).getZyjurl4());
                break;
            default:
                break;
        }
        intent.putExtra("item", "main");
        startService(intent);
    }

    //下载完成，关闭service
    private void closeDowload() {
        Intent intent = new Intent(this, DownLoadService.class);
        stopService(intent);
    }

    //1 饱食度小于30%时
    //2 饱食度大于90%时
    //3 关心，点击宠物身体，获得爱心时
    //4 当日第一次登录后，是节日抽奖/周末抽奖
    //5 没有修改过宠物昵称的用户，每天首次登录
    public void setMessage(String str, boolean needName) {

        if ("Y".equals(MyApplication.getMyApplication().isFriend) || textView.getVisibility() == View.VISIBLE)
            return;

        processCustomMessage(this, str, needName);
        textView.setVisibility(View.VISIBLE);
        if (needName) {
            textView.setText(MyApplication.getMyApplication().userInfo.getYhnc() + "，" + str);
        } else {
            textView.setText(str);
        }
        handler.sendEmptyMessageDelayed(107, 7000);
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, String str, boolean needname) {
        Intent intent = new Intent(context, FloatWindowService.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        if (needname) {
            intent.putExtra("msg", MyApplication.getMyApplication().userInfo.getYhnc() + "，" + str);
        } else {
            intent.putExtra("msg", str);
        }
        startService(intent);
    }

    //设置眨眼或哭
    private void showImgB() {
        if (null != bean) {
            Double d = 0.0;
            if ("".equals(bean.getBscns())) {
                d = 0.0;
            } else {
                d = Double.parseDouble(bean.getBscns());
            }

            int i = d.compareTo(30.00);
            if (i >= 0) {
                //饱食度大于30 眨眼
                showAnimation(1, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());
            } else {
                //饱食度小于30 哭
                showAnimation(4, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());
            }
        }

    }

    //动画选择
    //1 眨眼
    //2 吃
    //3 打招呼
    //4 哭
    //5 笑
    public void showAnimation(int i, String petid, String petdj) {
        if ("".equals(petid)) {
            anima.stopAnima();
            imgPetZ.setBackgroundResource(R.mipmap.shouye_xinyonghu_chongwu);
            return;
        }
        MyLog.showLog("LLL", "--- " + UtilsTools.getPetDj(Integer.parseInt(petdj)) + " ---");
        switch (i) {
            case 1:
                anima.runFrameForEye(this, petid + "_" + UtilsTools.getPetDj(Integer.parseInt(petdj)), imgPetZ);
                break;
            case 2:
                anima.runFrameForEat(this, petid + "_" + UtilsTools.getPetDj(Integer.parseInt(petdj)), imgPetZ);
                break;
            case 3:
                anima.runFrameForHey(this, petid + "_" + UtilsTools.getPetDj(Integer.parseInt(petdj)), imgPetZ);
                break;
            case 4:
                anima.runFrameForCry(this, petid + "_" + UtilsTools.getPetDj(Integer.parseInt(petdj)), imgPetZ);
                break;
            case 5:
                anima.runFrameForSmell(this, petid + "_" + UtilsTools.getPetDj(Integer.parseInt(petdj)), imgPetZ);
                break;
            default:
                break;
        }

    }


    //设置眨眼定时
    class ShowImgTask extends TimerTask {
        @Override
        public void run() {
            getJCSJ();
            getSJWPList();
            getCJList();
        }
    }

    //开启定时器
    public void openTimer() {
        try {
            if (MyApplication.getMyApplication().userInfo != null && !"".equals(MyApplication.getMyApplication().userInfo.getPetid())) {
                // 开启定时器，每隔10秒刷新一次
                if (timer == null) {
                    timer = new Timer();
                    timer.schedule(new ShowImgTask(), 0, 25000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //关闭定时
    public void closeTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSJWPList();
//        showFrend(false);
        isMainShow = true;
        playMusic();
        setPetChange();
    }

    public void setPetChange() {
        if (isFirst) {
            if (!"".equals(MyApplication.getMyApplication().userInfo.getPetid())) {
                if (UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) > Integer.parseInt(SharePrefUtil.getString(this, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj()))) {
                    isUpLevel = true;
                    getPetSise(MyApplication.getMyApplication().userInfo.getPetid(), UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) + "");
                } else {
                    if (checkPetImg(MyApplication.getMyApplication().userInfo.getPetid() + "", UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) + "") < 25) {
                        checkAnima(MyApplication.getMyApplication().userInfo.getPetid() + "", UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) + "", 25);
                        isDowload = true;
                    } else {
                        Message message = new Message();
                        message.what = 100;
                        Bundle b = new Bundle();
                        b.putString("petid", MyApplication.getMyApplication().userInfo.getPetid() + "");
                        b.putString("petdj", MyApplication.getMyApplication().userInfo.getPetdj() + "");
                        message.setData(b);
                        handler.sendMessageDelayed(message, 1000);
                        isDowload = false;
                    }
                }
            }
            isFirst = false;
        }
    }

    private String petid;
    private String petdj;
    private int pro;

    //检查网络加载动画
    public void checkAnima(String petid, String petdj, int pro) {
        this.petid = petid;
        this.petdj = petdj;
        this.pro = pro;
        if (CheckNetWork.getNetWorkType(this) != CheckNetWork.NETWORKTYPE_WIFI) {
            getPetSise(petid, petdj);
        } else {
            if (pro == 25) {
                getPetTpList(petid, petdj);
            } else {
                getFriendPetList(petid, petdj);
            }
            showDowloadPressbar(pro);
        }
    }

    //提示WIFI缺失WiFi
    private void showWIFIdialog(final String petid, final String petdj, final int pro) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        if (petid.equals(MyApplication.getMyApplication().userInfo.getPetid())) {

        }
        boolean b = "Y".equals(MyApplication.getMyApplication().isFriend);
        String size = "";
        switch (Integer.parseInt(petdj)) {
            case 1:
                if (!b) {
                    size = dataBean.getVzsize();
                } else {
                    size = dataBean.getVzyjsize();
                }
                break;
            case 2:
                if (!b) {
                    size = dataBean.getVzsize();
                } else {
                    size = dataBean.getVzyjsize();
                }
                break;
            case 3:
                if (!b) {
                    size = dataBean.getVzsize();
                } else {
                    size = dataBean.getVzyjsize();
                }
                break;
            default:
                if (!b) {
                    size = "5M";
                } else {
                    size = "1M";
                }
                break;
        }
        tvTitle.setText("当前为非WiFi网络，加载宠物需要" + size + "M。\n是否加载？");
        tvData.setVisibility(View.GONE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJiazai = true;
                dialog.dismiss();
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJiazai = false;
                if (pro == 25) {
                    getPetTpList(petid, petdj);
                } else {
                    getFriendPetList(petid, petdj);
                }
                showDowloadPressbar(pro);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //提示宠物升级
    private void showPetUpLevel(final String petid, final String petdj) {
        num = 1;
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        String size = "";
        switch (UtilsTools.getPetDj(Integer.parseInt(petdj))) {
            case 1:
                size = dataBean.getVzsize();
                break;
            case 2:
                size = dataBean.getVzsize();
                break;
            case 3:
                size = dataBean.getVzsize();
                break;
            default:
                size = "0.5M";
                break;
        }
        if (CheckNetWork.getNetWorkType(this) == CheckNetWork.NETWORKTYPE_WIFI) {
            tvTitle.setText("您的宠物已经进化，请下载进化资源包。\n" + size + "M");
        } else {
            tvTitle.setText("您的宠物已经进化，请下载进化资源包。\n当前处于非WiFi状态，请注意流量。\n" + size + "M。");
        }
        tvData.setVisibility(View.GONE);
        tvCancel.setVisibility(View.GONE);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPetTpList(petid, petdj);
                showDowloadPressbar(25);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private TextView tvData;
    private ProgressBar progress;
    private Dialog dialog;

    //下载进度条
    private void showDowloadPressbar(int pro) {
        isDowload = true;
        dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_dowload);
        dialog.setCancelable(false);
        tvData = (TextView) dialog.findViewById(R.id.tv_data);
        progress = (ProgressBar) dialog.findViewById(R.id.progress);
        progress.setMax(pro);
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isMainShow = false;
        isDowload = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        closeTimer();
        stopMusic();
        stopLocation();
        anima.stopAnima();
        handler.removeCallbacks(runnable);
        handler = null;
        MyLog.showLog("WEI", "------onDestroy-------");
        super.onDestroy();
    }

    //设定飞到哪里
    private void setAnimTo(ImageView view, String lx, String spid, String cns, String yhids) {
        switch (lx) {
            case "1":
                addCart(view, tvGold, lx, spid, cns, yhids, false);
                break;
            case "2":
                addCart(view, imgMap, lx, spid, cns, yhids, false);
                break;
            case "3":
//                addCart(view, tvSw, lx, spid, cns, yhids, false);
                addCart(view, imgFood, lx, spid, cns, yhids, false);
                break;
            case "4":
                addCart(view, imgPaihang2, "", "", "", yhids, false);
                break;
            default:

                break;
        }
    }

    @OnClick({R.id.img_gold, R.id.img_love1, R.id.img_love2, R.id.img_cm, R.id.img_luckpan,
            R.id.img_grally, R.id.img_map, R.id.img_love3, R.id.img_cm2, R.id.img_frend,
            R.id.img_food, R.id.img_tx, R.id.img_gold2, R.id.img_lv, R.id.rela_dengji, R.id.rela_yuanbao,
            R.id.img_sw_jia, R.id.img_yuanbao_jia, R.id.img_cwtx, R.id.tv_cwnc, R.id.img_hd,
            R.id.frend_img_tx1, R.id.img_pet_z, R.id.rela_lv, R.id.rela_gold, R.id.img_fj, R.id.img_shop,
            R.id.img_grally2, R.id.img_shop2, R.id.tv_gively,
            R.id.img_close, R.id.tv_send, R.id.tab_friend, R.id.tab_emoji, R.id.tab_food, R.id.tab_groud,
            R.id.tab_shop, R.id.tab_rank, R.id.tab_pan, R.id.rela_cy, R.id.rela_emoji, R.id.rela_dw, R.id.rela_vip_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                dismiss();
                break;
            case R.id.tv_send:
                sendLY();
                break;
            case R.id.tab_friend:
                changeTab(1);
                changeLayout(1);
                KeybordS.closeKeybord(editSend, this);
                freshLayout(positionKey);
                this.setDanMuVistble(false);
                break;
            case R.id.tab_food:
                changeTab(3);
                changeLayout(3);
                setMainPet();
                KeybordS.closeKeybord(editSend, this);
                freshLayout(positionKey);
                this.setDanMuVistble(true);
                break;
            case R.id.tab_emoji:
                changeTab(2);
                changeLayout(2);
                KeybordS.closeKeybord(editSend, this);
                freshLayout(positionKey);
                this.setDanMuVistble(false);
                break;
            case R.id.tab_groud:
                changeTab(4);
                changeLayout(4);
                setMainPet();
                KeybordS.closeKeybord(editSend, this);
                freshLayout(positionKey);
                this.setDanMuVistble(true);
                break;
            case R.id.tab_shop:
                changeTab(5);
                goToActivity(5);
                break;
            case R.id.tab_rank:
                changeTab(6);
                goToActivity(6);
                break;
            case R.id.tab_pan:
                changeTab(7);
                goToActivity(7);
                break;
            case R.id.rela_cy:
                myViewpager.setCurrentItem(0);
                break;
            case R.id.rela_emoji:
                myViewpager.setCurrentItem(1);
                break;
            case R.id.rela_dw:
                myViewpager.setCurrentItem(2);
                break;
            case R.id.rela_vip_d:
                myViewpager.setCurrentItem(3);
                break;
            case R.id.tv_gively:
//                showDrawerDialog();
//                showDrawerWindow();
                break;
            case R.id.img_grally2:
                gotoSomeActivity(this, AtyTag.ATY_Note, false);
//                gotoAdvertisementActivity("Y");
                break;
            case R.id.img_shop2:
                gotoSomeActivity(this, AtyTag.ATY_Nearby, false);
//                gotoAdvertisementActivity();
                break;
            case R.id.img_fj:
//                imgFj.setVisibility(View.GONE);
//                zt = zero;
//                type = "sreach";
//                if (fjdr != null && !"0".equals(fjdr.getVtx())) {
//                    friendID = fjdr.getVyhids() + "";
//                    gotoSomeActivity(this, AtyTag.ATY_AddNewFrend, true);
//                }
//                fjdr.setVtx("0");
                showPopupPage();
                break;
            case R.id.img_pet_z:
                try {
                    if ("".equals(MyApplication.getMyApplication().userInfo.getPetid()) && !"Y".equals(MyApplication.getMyApplication().isFriend)) {
                        gotoSomeActivity(this, AtyTag.ATY_PetShop, true);
                    } else {
                        if (isJiazai) {
                            if (UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) > Integer.parseInt(SharePrefUtil.getString(this, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj()))) {
                                isUpLevel = true;
                                getPetSise(MyApplication.getMyApplication().userInfo.getPetid(), UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) + "");
                            } else {
                                if (checkPetImg(MyApplication.getMyApplication().userInfo.getPetid() + "", UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) + "") < 25) {
                                    checkAnima(MyApplication.getMyApplication().userInfo.getPetid() + "", UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) + "", 25);
                                    isDowload = true;
                                } else {
                                    Message message = new Message();
                                    message.what = 100;
                                    Bundle b = new Bundle();
                                    b.putString("petid", MyApplication.getMyApplication().userInfo.getPetid() + "");
                                    b.putString("petdj", MyApplication.getMyApplication().userInfo.getPetdj() + "");
                                    message.setData(b);
                                    handler.sendMessageDelayed(message, 1000);
                                    isDowload = false;
                                }
                            }
                        } else {
                            if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                                if (friendPetBean != null && friendPetBean.getLbaxcns() > 0)
                                    addCart(imgMosce, imgPaihang1, "5", "", "", friendPetBean.getPyhids() + "", false);
                            } else {
                                NetWork.getzdyxxList(MainActivity.this);
                                if (myPetThingList != null && myPetThingList.size() > 0 && !"".equals(myPetThingList.get(0).getAxcns())) {
                                    if (Integer.parseInt(myPetThingList.get(0).getAxcns()) > 0) {
                                        addCart(imgMosce, imgMap, "6", "", "", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), false);
                                    }
                                } else {

                                }
                            }
                            imgPetZ.setClickable(false);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.img_gold:
                // 第一个 左1
                if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                    if (friendPetBean != null && friendPetBean.getZbaxcns() > 1) {
                        setAnimTo(img1, "4", "", "", friendPetBean.getPyhids() + "");
                    } else {
//                        Toast.makeText(this, "给TA留一个吧!", Toast.LENGTH_SHORT).show();
                        ToastUtil.showShort(this, "给TA留一个吧!");
                    }
                } else {
                    if (list != null && list.size() > 0)
                        setAnimTo(img1, list.get(0).getLx(), list.get(0).getSpid() + "", 1 + "", "");
                }
                break;
            case R.id.img_love1:
                //第三个 左二
                if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                    if (friendPetBean != null && friendPetBean.getZbaxcns() > 1)
                        setAnimTo(img3, "4", "", "", friendPetBean.getPyhids() + "");
                } else {
                    if (list != null && list.size() > 2)
                        setAnimTo(img3, list.get(2).getLx(), list.get(2).getSpid() + "", 1 + "", "");
                }
                break;
            case R.id.img_love2:
                //第二个 右一
                if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                    if (friendPetBean != null && friendPetBean.getZbaxcns() > 1)
                        setAnimTo(img2, "4", "", "", friendPetBean.getPyhids() + "");
                } else {
                    if (list != null && list.size() > 1)
                        setAnimTo(img2, list.get(1).getLx(), list.get(1).getSpid() + "", 1 + "", "");
                }
                break;
            case R.id.img_love3:
                //第五个 左三
                if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                    if (friendPetBean != null && friendPetBean.getZbaxcns() > 1)
                        setAnimTo(img5, "4", "", "", friendPetBean.getPyhids() + "");
                } else {
                    if (list != null && list.size() > 4)
                        setAnimTo(img5, list.get(4).getLx(), list.get(4).getSpid() + "", 1 + "", "");
                }
                break;
            case R.id.img_cm:
                //第四个 右二
                if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                    if (friendPetBean != null && friendPetBean.getZbaxcns() > 1)
                        setAnimTo(img4, "4", "", "", friendPetBean.getPyhids() + "");
                } else {
                    if (list != null && list.size() > 3)
                        setAnimTo(img4, list.get(3).getLx(), list.get(3).getSpid() + "", 1 + "", "");
                }
                break;
            case R.id.img_cm2:
                //第六个 右三
                if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                    if (friendPetBean != null && friendPetBean.getZbaxcns() > 1)
                        setAnimTo(img6, "4", "", "", friendPetBean.getPyhids() + "");
                } else {
                    if (list != null && list.size() > 5)
                        setAnimTo(img6, list.get(5).getLx(), list.get(5).getSpid() + "", 1 + "", "");
                }
                break;
            case R.id.img_luckpan:
                if (list != null && listBeen != null && listBeen.size() > 0) {
                    shaungchou = listBeen.get(0).getVtwolx();
                    djs = listBeen.get(0).getVdjs();
                    gotoSomeActivity(this, AtyTag.ATY_LuckPan2, true);
                }
//                gotoSomeActivity(this,AtyTag.ATY_Advertisement2,false);
                break;
            case R.id.img_frend:
//                showPopFrendWindow();
//                showDrawerDialog(1);
//                showDrawer4Window();
                showRelaBottom(1);
                if (friendList.size() > 0) {
                    chNo1(this.friendList.get(0).getYhid() + "", 0);
                }
                break;
            case R.id.img_food:
//                showPopFoodWindow();
//                showDrawer5Dialog(3);
                showRelaBottom(3);
                break;
            case R.id.img_hd:
                hdNet = bean.getUrl();
                hdBt = bean.getBt();
                gotoSomeActivity(this, AtyTag.ATY_WebView, false);
                break;
            case R.id.img_cwtx:
                personDate = bean.getPetnc();
                gotoSomeActivity(this, AtyTag.ATY_PetChangeName, true);
                break;
            case R.id.tv_cwnc:
                personDate = bean.getPetnc();
                gotoSomeActivity(this, AtyTag.ATY_PetChangeName, true);
                break;
            case R.id.img_grally:
//                showBackgroudWindow();
//                showDrawer5Dialog(4);
                showRelaBottom(4);
                break;
            case R.id.img_map:
                gotoSomeActivity(this, AtyTag.ATY_LoveRank, true);
                break;
            case R.id.img_tx:
                gotoSomeActivity(this, AtyTag.ATY_PersonCenter, true);
                break;
            case R.id.img_gold2:
                if (relaYuanbao.getVisibility() == View.INVISIBLE) {
                    relaYuanbao.setVisibility(View.VISIBLE);
                    relaDengji.setVisibility(View.INVISIBLE);
                } else {
                    relaYuanbao.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rela_gold:
                if (relaYuanbao.getVisibility() == View.INVISIBLE) {
                    relaYuanbao.setVisibility(View.VISIBLE);
                    relaDengji.setVisibility(View.INVISIBLE);
                } else {
                    relaYuanbao.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.img_lv:
                if (relaDengji.getVisibility() == View.INVISIBLE) {
                    relaDengji.setVisibility(View.VISIBLE);
                    relaYuanbao.setVisibility(View.INVISIBLE);
                } else {
                    relaDengji.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rela_lv:
                if (relaDengji.getVisibility() == View.INVISIBLE) {
                    relaDengji.setVisibility(View.VISIBLE);
                    relaYuanbao.setVisibility(View.INVISIBLE);
                } else {
                    relaDengji.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rela_dengji:
                relaDengji.setVisibility(View.INVISIBLE);
                break;
            case R.id.rela_yuanbao:
                relaYuanbao.setVisibility(View.INVISIBLE);
                break;
            case R.id.img_sw_jia:
                gotoSomeActivity(this, AtyTag.ATY_AddFood, true);
                break;
            case R.id.img_yuanbao_jia:
                gotoSomeActivity(this, AtyTag.ATY_AddGold, true);
                break;
            case R.id.frend_img_tx1:
                seeFriend(friendBean.getYhids() + "");
                break;
            case R.id.img_shop:
                gotoSomeActivity(this, AtyTag.ATY_PetShop, true);
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (handler != null) {
                        tvTime.setText(String.format("%02d:%02d", MyApplication.getMyApplication().time / 60, MyApplication.getMyApplication().time % 60));
                        MyApplication.getMyApplication().time--;
                        handler.removeMessages(0);
                        handler.postDelayed(runnable, 1000);
                    }
                    break;
                case 100:
                    MyLog.showLog("OOO", " -- petid " + msg.getData().getString("petid") + " - UtilsTools.getPetDj(Integer.parseInt(petdj)) " + UtilsTools.getPetDj(Integer.parseInt(msg.getData().getString("petdj"))));
                    if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                        setFriendSY();
                    } else {
                        String petid = msg.getData().getString("petid");
                        String petdj = msg.getData().getString("petdj");
                        showAnimation(3, petid, petdj);
                    }
                    break;
                case 101:
                    if (dialog != null) {
                        progress.setProgress(num);
                        if (isHyTp) {
                            tvData.setText("进度 " + (num * 100 / 4) + "%");
                        } else {
                            tvData.setText("进度 " + (num * 100 / 25) + "%");
                        }

                    }
                    break;
                case 107:
                    textView.setVisibility(View.INVISIBLE);
                    if (logsxx == null) {
                        getLogsxx();
                    } else {
                        if (!"".equals(logsxx.getVmc())) {
                            setMessage(logsxx.getVmc(), true);
                            logsxx.setVmc("");
                        }
                    }
                    break;
                case 109:
                    if (handler != null)
                        handler.removeMessages(109);
                    NetWork.setImgGlideBackgroud(MainActivity.this, MyApplication.getMyApplication().userInfo.getTp(), activityMain);
                    break;
                case 22:
                    if (wddm < wddmList.size()) {
                        startDanmu(wddmList.get(wddm).getTx(), wddmList.get(wddm).getNr(), wddmList.get(wddm).getLyid());
                        wddm++;
                        handler.sendEmptyMessageDelayed(22, 2000);
                    } else {
                        wddm = 0;
                        if (wddmList.size() == 10) {
                            NetWork.getNoreadlylList(SharePrefUtil.getString(MainActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), MainActivity.this);
                        }
                        handler.removeMessages(22);
                    }
                    break;
                case 33:
                    getLoginDate();
                    getJCSJ();
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == 101) {
                NetWork.updateYhybs(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), this);
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (MyApplication.getMyApplication().time >= 0) {
                handler.sendEmptyMessage(0);
            } else {
                getCJList();
                handler.removeCallbacks(this);
            }
        }
    };

    /**
     * ★★★★★把商品添加到购物车的动画效果★★★★★
     *
     * @param iv
     */
    public void addCart(ImageView iv, View view, final String lx, final String spid, final String cns, final String yhids, final boolean isFood) {

        isAnimoRuning = true;

        final PathMeasure mPathMeasure;
        /**
         * 贝塞尔曲线中间过程的点的坐标
         */
        final float[] mCurrentPosition = new float[2];
        if (isFood) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.INVISIBLE);
        }

        setCanNotClick(false);

//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        final ImageView goods = new ImageView(MainActivity.this);
        goods.setImageDrawable(iv.getDrawable());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(UtilsTools.dip2px(this, 70), UtilsTools.dip2px(this, 70));
        params.width = UtilsTools.dip2px(this, 70);
        params.height = UtilsTools.dip2px(this, 70);
        goods.setLayoutParams(params);
        goods.setPadding(UtilsTools.dip2px(this, 15), UtilsTools.dip2px(this, 15), UtilsTools.dip2px(this, 15), UtilsTools.dip2px(this, 15));

//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(UtilsTools.dip2px(this, 65), UtilsTools.dip2px(this, 65));

        activityMain.addView(goods);


//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        activityMain.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        iv.getLocationInWindow(startLoc);

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
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(goods, "scaleX", 2f, 0f);
        scaleX.setDuration(200);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(goods, "scaleY", 2f, 0f);
        scaleY.setDuration(200);
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
                                   isAnimoRuning = false;
                                   setCanNotClick(true);
                                   activityMain.removeView(goods);
                                   if (lx.equals("6")) {
                                       getMyPetAx();
                                   } else if (lx.equals("5")) {
                                       getBHYSAX(yhids);
                                   } else if (isFood) {
                                       setPetFood(yhids);
                                   } else {
                                       if ("Y".equals(MyApplication.getMyApplication().isFriend)) {
                                           if (friendPetBean.getZbaxcns() > 1) {
                                               getTQHYAX(yhids);
                                           } else {
//                                               Toast.makeText(MainActivity.this, "给TA留一个吧!", Toast.LENGTH_SHORT).show();
                                               ToastUtil.showShort(MainActivity.this, "给TA留一个吧!");
                                           }
                                       } else {
//                                           showAnimation(5, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());
                                           getSJWP(lx, spid, cns);
                                       }
                                   }

                               }
                           }

        );
    }

    public void eatFood(final RelativeLayout relati, ImageView iv, View view, final String yhids) {
        final PathMeasure mPathMeasure;
        /**
         * 贝塞尔曲线中间过程的点的坐标
         */
        final float[] mCurrentPosition = new float[2];

        setCanNotClick(false);

//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        final ImageView goods = new ImageView(MainActivity.this);
        goods.setImageDrawable(iv.getDrawable());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(UtilsTools.dip2px(this, 50), UtilsTools.dip2px(this, 50));
        relati.addView(goods, params);

//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        relati.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        iv.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        view.getLocationInWindow(endLoc);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

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
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(goods, "scaleX", 2f, 0f);
        scaleX.setDuration(200);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(goods, "scaleY", 2f, 0f);
        scaleY.setDuration(200);
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
                setCanNotClick(true);
                relati.removeView(goods);
                setPetFood(yhids);
                showAnimation(2, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());

            }
        });
    }

    //一个动画启动后，所有的物品禁止点击，直到动画结束
    private void setCanNotClick(boolean can) {
        if (can) {
            img1.setClickable(true);
            img4.setClickable(true);
            img3.setClickable(true);
            img2.setClickable(true);
            img5.setClickable(true);
            img6.setClickable(true);
        } else {
            img1.setClickable(false);
            img4.setClickable(false);
            img3.setClickable(false);
            img2.setClickable(false);
            img5.setClickable(false);
            img6.setClickable(false);
        }
    }

    private int sece = 0;

    //手指放大缩小动画
    private void scakeView(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f, 1f);
        scaleX.setDuration(2000);
        scaleX.setRepeatCount(Integer.MAX_VALUE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f, 1f);
        scaleY.setDuration(2000);
        scaleY.setRepeatCount(Integer.MAX_VALUE);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
    }

    private void hdScale(final View view) {
        ScaleAnimation animation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1f);
        animation.setDuration(500);//设置动画持续时间
        /** 常用方法 */
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态

        final ScaleAnimation animation2 = new ScaleAnimation(1.5f, 1f, 1.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1f);
        animation2.setDuration(500);//设置动画持续时间
        /** 常用方法 */
        animation2.setFillAfter(true);//动画执行完后是否停留在执行完的状态

        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                sece++;
                if (sece < 5)
                    hdScale(imgHd);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    //好友窗口
    public FrendWindow frendwindow;

    private void showPopFrendWindow() {
//        MyApplication.getMyApplication().isFriend = "Y";
        if (frendwindow == null && friendList != null) {
            textView.setVisibility(View.INVISIBLE);
            frendwindow = new FrendWindow(this, friendList);
            frendwindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else if (frendwindow != null && !frendwindow.isShowing()) {
            textView.setVisibility(View.INVISIBLE);
            frendwindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    public void clearChoisce() {
        frendwindow = null;
        if (friendList != null) {
            for (int i = 0; i < friendList.size(); i++) {
                friendList.get(i).setChosice(false);
            }
        }
    }

    //切换main xml顶部布局 显示好友
    public void showFrend(boolean isShow) {
        if (isShow) {
            MyApplication.getMyApplication().isFriend = "Y";
            relaMain.setVisibility(View.GONE);
            relaFrend.setVisibility(View.VISIBLE);
            imgHand2.setVisibility(View.INVISIBLE);
        } else {
            MyApplication.getMyApplication().isFriend = "N";
            relaMain.setVisibility(View.VISIBLE);
            relaFrend.setVisibility(View.GONE);
            NetWork.setImgGlideBackgroud(this, MyApplication.getMyApplication().userInfo.getTp(), activityMain);
            handler.sendEmptyMessageDelayed(109, 1000);
        }

    }

    //食物窗口
    public FoodWindow foodwindow;

    private void showPopFoodWindow() {
        if (foodwindow == null) {
            foodwindow = new FoodWindow(this, foodList);
            foodwindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else if (foodwindow != null && !foodwindow.isShowing()) {
            foodwindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    //背景图窗口
    public BackgroudWindow backgroudWindow;

    private void showBackgroudWindow() {
        if (backgroudWindow == null) {
            backgroudWindow = new BackgroudWindow(this, bjtpList);
            backgroudWindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else if (backgroudWindow != null && !backgroudWindow.isShowing()) {
            backgroudWindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }


    //删除背景
    public void showDeletBakDialog(final String bjtid) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("确认删除这张背景图吗？");
        tvData.setVisibility(View.GONE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBack(bjtid);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public DrawerWindow2 drawerWindow;

    public void showDrawerWindow() {
        if (drawerWindow == null) {
            drawerWindow = new DrawerWindow2(this);
            drawerWindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else if (drawerWindow != null && !drawerWindow.isShowing()) {
            drawerWindow.showAtLocation(activityMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    public DrawerDialog3 drawerDialog;

    public void showDrawerDialog(int num) {
        drawerDialog = new DrawerDialog3(this, R.style.MyDialog2, num);
        drawerDialog.show();
    }

    public DrawerDialog4 drawerDialog4;

    public void showDrawer4Window() {
        if (drawerDialog4 != null && drawerDialog4.isShowing()) {

        } else {
            drawerDialog4 = new DrawerDialog4(this, R.style.MyDialog2);
            drawerDialog4.show();
        }
    }

    public void setDanMuVistble(boolean isVisible) {
        if (isVisible) {
            relaDanmu.setVisibility(View.VISIBLE);
            relaDanmuF.setVisibility(View.GONE);
        } else {
            relaDanmu.setVisibility(View.GONE);
            relaDanmuF.setVisibility(View.VISIBLE);
        }
    }

    public DrawerDialog5 drawerDialog5;

    public void showDrawer5Dialog(int num) {
        if (drawerDialog5 != null && drawerDialog5.isShowing()) {

        } else {
            drawerDialog5 = new DrawerDialog5(this, R.style.MyDialog2, num);
            drawerDialog5.show();

        }
    }

    public void chNo1(String yhids, int position) {
        MyApplication.getMyApplication().haoyouID = yhids;
        this.friendOldPosition = this.friendPosition;
        this.friendPosition = position;
        this.setThingGone();
        this.isHyTp = true;
        this.getMyFriend(yhids);
        this.showFrend(true);
//        context.frendwindow.updataPosition(getLayoutPosition());
//        this.drawerDialog.updataPosition(0);
//        this.drawerDialog4.updataPosition(0);
        this.updataPosition(position);
    }

    @Override
    public void onBackPressed() {
        if (null != drawerWindow && drawerWindow.isShowing()) {
            drawerWindow.dismiss();
            return;
        }
        if (null != frendwindow && frendwindow.isShowing()) {
            showFrend(false);
            showAnimation(1, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());
            frendwindow.dismiss();
            return;
        }
        if (null != foodwindow && foodwindow.isShowing()) {
            foodwindow.dismiss();
            return;
        }
        if (null != backgroudWindow && backgroudWindow.isShowing()) {
            backgroudWindow.dismiss();
            return;
        }
        if (null != drawerWindow && drawerWindow.isShowing()) {
            drawerWindow.dismiss();
            return;
        }

        if (null != relaBottom && relaBottom.getVisibility() == View.VISIBLE) {
            hideRelaBottom();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_aplha_in, R.anim.activity_aplha_out);
    }

    //检查权限

    public void getBuildVERSION() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                showPermissionDialog();
            } else {
                getLocation();
            }
        } else {
            getLocation();
        }
    }

    //申请权限
    private void getPermission(final Activity activity) {
        requestRunTimePermission(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA}, new IPermission() {

            //用户同意时的回调
            @Override
            public void onGranted() {
                getLocation();
            }

            //用户拒绝时的回调，并打印出具体拒绝了什么权限
            @Override
            public void onDenied(List<String> deniedPermissions) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    for (String deniedPermission : deniedPermissions) {
                        boolean b = shouldShowRequestPermissionRationale(deniedPermission);
                        if (!b) {
                            showPermissionDialog();
                        }
                    }
                }
            }
        });
    }

    //提示用户开启权限
    private void showPermissionDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setText("开启");
        tvTitle.setText("宠物需要访问手机状态，地理位置，相机和读写SD卡的权限！");
        tvData.setVisibility(View.GONE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermission(MainActivity.this);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //开启service获取位置
    private void getLocation() {
//        Intent intent = new Intent(this, LocationService.class);
//        intent.setClass(this, LocationService.class);
//        startService(intent);
        Intent intent = new Intent(this, LocationBaiDuService.class);
        intent.setClass(this, LocationBaiDuService.class);
        startService(intent);
    }

    private void stopLocation() {
        Intent intent = new Intent(this, LocationBaiDuService.class);
        intent.setClass(this, LocationBaiDuService.class);
        stopService(intent);
    }

    private void showDialogMissionComple() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_mission);
        dialog.setCancelable(false);
        TextView phone = (TextView) dialog.findViewById(R.id.tv_phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdd = "phone";
                Intent intent = new Intent(MainActivity.this, SetPhoneActivity.class);
                startActivity(intent);
            }
        });
        TextView password = (TextView) dialog.findViewById(R.id.tv_password);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(MyApplication.getMyApplication().userInfo.getDh())) {
                    showDialogNoPhone();
                } else {
                    bdd = "word";
                    Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                    intent.putExtra("title", "设置密码");
                    MainActivity.this.startActivity(intent);
                }
            }
        });
        LinearLayout linerTwo = (LinearLayout) dialog.findViewById(R.id.liner_two);
        if (!"".equals(MyApplication.getMyApplication().userInfo.getDh())) {
            linerTwo.setVisibility(View.GONE);
        } else {
            linerTwo.setVisibility(View.VISIBLE);
        }
        LinearLayout linerThr = (LinearLayout) dialog.findViewById(R.id.liner_thr);
        if (!"".equals(MyApplication.getMyApplication().userInfo.getMm())) {
            linerThr.setVisibility(View.GONE);
        } else {
            linerThr.setVisibility(View.VISIBLE);
        }
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showDialogMissionComplePhone(String msg) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_missioncomple);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        tvTitle.setText(msg + "任务已完成");
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showDialogNoPhone() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        ;
        tvTitle.setText("请先绑定手机号");
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        ;
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        ;
        tvCancel.setVisibility(View.GONE);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private QuickPageAdapter quickPageAdapter;
    private List<View> listPage;

    private void getPageList() {
        if (null == listPage)
            listPage = new ArrayList<>();
        for (int i = 0; i < tjyhList.size(); i++) {
            View page = LayoutInflater.from(this).inflate(R.layout.viewpage_item, null);
            ImageView imgTx = (ImageView) page.findViewById(R.id.img_tx);
            TextView tvName = (TextView) page.findViewById(R.id.tv_name);
            LinearLayout linearSex = (LinearLayout) page.findViewById(R.id.linear_sex);
            ImageView imgSex = (ImageView) page.findViewById(R.id.img_sex);
            TextView tvSex = (TextView) page.findViewById(R.id.tv_sex);
            TextView tvLevel = (TextView) page.findViewById(R.id.tv_level);
            TextView tvSign = (TextView) page.findViewById(R.id.tv_sign);
            LinearLayout linearBottom = (LinearLayout) page.findViewById(R.id.linear_bottom);
            final String yhid = tjyhList.get(i).getYhid();
            linearBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zt = zero;
                    type = "sreach";
                    friendID = yhid;
                    gotoSomeActivity(MainActivity.this, AtyTag.ATY_AddNewFrend, true);
                }
            });
//            Glide.with(this).load(tjyhList.get(i).getTx()).into(imgTx);
            NetWork.setImgGlide(this, tjyhList.get(i).getTx(), imgTx);
            tvName.setText(tjyhList.get(i).getNc());
            if ("女".equals(tjyhList.get(i).getXb())) {
                linearSex.setBackgroundResource(R.drawable.base_rectangle_girl);
                imgSex.setImageResource(R.mipmap.women21);
                tvSex.setText(this.getAge(tjyhList.get(i).getBdate()) + "");
            } else {
                linearSex.setBackgroundResource(R.drawable.base_rectangle_boy);
                imgSex.setImageResource(R.mipmap.man21);
                tvSex.setText(this.getAge(tjyhList.get(i).getBdate()) + "");
            }
            tvLevel.setText("LV" + tjyhList.get(i).getDj());
            tvSign.setText(tjyhList.get(i).getQm());
            listPage.add(page);
        }
        if (null != quickPageAdapter) {
            quickPageAdapter.notifyDataSetChanged();
        }
    }

    private void showPopupPage() {
        if (listPage == null || listPage.size() == 0)
            return;
        final PopupWindow mPopWin = new PopupWindow(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        View view = LayoutInflater.from(this).inflate(R.layout.popupwind_viewpage, null);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        ImageView imgClose = (ImageView) view.findViewById(R.id.img_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWin.dismiss();
            }
        });

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_page);
        viewPager.setPageTransformer(true, getPageTransformer());
        quickPageAdapter = new QuickPageAdapter<>(listPage);
        viewPager.setAdapter(quickPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == listPage.size() - 1) {
                    pageindex++;
                    NetWork.gettjyhList(SharePrefUtil.getString(MainActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", MainActivity.this);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        ImageView imgHand = (ImageView) view.findViewById(R.id.img_hand);
        handPage(imgHand);
        mPopWin.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mPopWin.setContentView(view);

        mPopWin.setFocusable(true); //获取焦点
        mPopWin.setTouchable(true); //可以接收点击事件

        mPopWin.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //设置背景透明
        mPopWin.setOutsideTouchable(false);//允许外部点击隐藏popupwindow

        mPopWin.showAsDropDown(findViewById(R.id.img_grally2));
    }


    //viewpage动画
    private void handPage(final View view) {
        view.setVisibility(View.VISIBLE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f, 1f).setDuration(2000);
        scaleX.setRepeatCount(1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f, 1f).setDuration(2000);
        scaleY.setRepeatCount(1);
        ObjectAnimator translationX1 = ObjectAnimator.ofFloat(view, "translationX", 0.0f, -500f).setDuration(2000);
        translationX1.setRepeatCount(1);
        AnimatorSet set1 = new AnimatorSet();
        set1.play(scaleX).with(scaleY).before(translationX1);
        set1.start();
        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });


    }

    public int danmu = 1;

    //弹幕
    public void startDanmu(String img, String ly, final String id) {
        MyLog.showLog("LLL", ly);
        final View view = LayoutInflater.from(this).inflate(R.layout.danmu_layout, null);
        ImageView tx = (ImageView) view.findViewById(R.id.img_tx);
//        Glide.with(this).load(img).into(tx);
        NetWork.setImgGlide(this, img, tx);
        EditText tv = (EditText) view.findViewById(R.id.tv_danmu);
        tv.setTextColor(ChartUtils.pickColor());
        MyLog.showLog("LLL", ExpressionUtil.getExpressionString(this, ly, "f[0-9]{3}").toString());
        tv.getText().insert(tv.getSelectionStart(), ExpressionUtil.getExpressionString(this, ly, "f[0-9]{3}"));
        switch (danmu) {
            case 1:
                danmu++;
                danmu1.addView(view);
                break;
            case 2:
                danmu++;
                danmu2.addView(view);
                break;
            case 3:
                danmu++;
                danmu3.addView(view);
                break;
            case 4:
                danmu++;
                danmu4.addView(view);
                break;
            case 5:
                danmu = 1;
                danmu5.addView(view);
                break;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", getScreenWidth(this), -getScreenWidth(this)).setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                switch (danmu) {
                    case 1:
                        danmu1.removeView(view);
                        break;
                    case 2:
                        danmu2.removeView(view);
                        break;
                    case 3:
                        danmu3.removeView(view);
                        break;
                    case 4:
                        danmu4.removeView(view);
                        break;
                    case 5:
                        danmu5.removeView(view);
                        break;
                }
                if (!"".equals(id))
                    NetWork.updateLyzt(id, MainActivity.this);
            }
        });
        animator.start();
    }

    public int danmuF = 1;

    //好友弹幕
    public void startDanmuF(String img, String ly, final String id) {
        MyLog.showLog("LLL", ly);
        final View view = LayoutInflater.from(this).inflate(R.layout.danmu_layout, null);
        ImageView tx = (ImageView) view.findViewById(R.id.img_tx);
//        Glide.with(this).load(img).into(tx);
        NetWork.setImgGlide(this, img, tx);
        EditText tv = (EditText) view.findViewById(R.id.tv_danmu);
        tv.setTextColor(ChartUtils.pickColor());
        MyLog.showLog("LLL", ExpressionUtil.getExpressionString(this, ly, "f[0-9]{3}").toString());
        tv.getText().insert(tv.getSelectionStart(), ExpressionUtil.getExpressionString(this, ly, "f[0-9]{3}"));
        switch (danmuF) {
            case 1:
                danmuF++;
                danmu1F.addView(view);
                break;
            case 2:
                danmuF++;
                danmu2F.addView(view);
                break;
            case 3:
                danmuF++;
                danmu3F.addView(view);
                break;
            case 4:
                danmuF++;
                danmu4F.addView(view);
                break;
            case 5:
                danmuF = 1;
                danmu5F.addView(view);
                break;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", getScreenWidth(this), -getScreenWidth(this)).setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                switch (danmuF) {
                    case 1:
                        danmu1F.removeView(view);
                        break;
                    case 2:
                        danmu2F.removeView(view);
                        break;
                    case 3:
                        danmu3F.removeView(view);
                        break;
                    case 4:
                        danmu4F.removeView(view);
                        break;
                    case 5:
                        danmu5F.removeView(view);
                        break;
                }
                if (!"".equals(id))
                    NetWork.updateLyzt(id, MainActivity.this);
            }
        });
        animator.start();
    }

    public static float getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static float getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    List<WeiDuDanmuBean.DataBean> wddmList;
    private int wddm = 0;
    private int pageindex = 1;
    private List<TJYHBean.DataBean> tjyhList;

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {
            switch (type) {
                case API.ADD_YHLY:

                    break;
                case API.GET_NO_READ_LY_LIST:
                    wddmList = gson.fromJson(result, WeiDuDanmuBean.class).getData();
                    if (null != wddmList && wddmList.size() > 0) {
                        handler.sendEmptyMessageDelayed(22, 3000);
                    }
                    break;
                case API.UPDATE_LYZT:

                    break;
                case API.GET_ZDYXX_LIST:
                    setMessage(gson.fromJson(result, ZSYXXBean.class).getData().getNr(), false);
                    showAnimation(5, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());
                    imgPetZ.setClickable(true);
                    break;
                case API.GET_TJYH_LIST:
                    tjyhList = gson.fromJson(result, TJYHBean.class).getData();
                    setFJDR();
                    getPageList();
                    break;
                case API.UPDATE_YHYBS:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String data = jsonObject.optString("data");
                        showAdDialog(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
//            ToastUtil.showShort(this, msg);
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    public int getAge(String bdate) {
        if (null != bdate && !"".equals(bdate)) {
            int year = Integer.parseInt(bdate.substring(0, 4));
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            int now = Integer.parseInt(format.format(new Date()));
            return now - year;
        }
        return 0;
    }

    private ViewPager.PageTransformer getPageTransformer() {
        ViewPager.PageTransformer pageTransformer;
        pageTransformer = new ViewPager.PageTransformer() {
            private float margin = UtilsTools.dip2px(MainActivity.this, 10);

            @Override
            public void transformPage(View page, float position) {
                page.setTranslationY(-position * margin);
                if (position >= -1.0f && position <= 0.0f) {
                    // 控制左侧滑入或者划出View相对X坐标为0
                    page.setTranslationX(-page.getWidth() * (position));
                    // 旋转
                    page.setPivotX(0);
                    page.setPivotY(0);
                    page.setRotation(-90f * position);
                } else if (position > 0.0f) {
                    // 控制右侧滑入或者划出控制View相对X坐标为0
                    page.setTranslationX(-page.getWidth() * (position));
                    page.setPivotX(0);
                    page.setPivotY(0);
                    page.setRotation(0f);
                } else {
                    // 控制左侧其它缓存View旋转状态固定
                    page.setPivotX(0);
                    page.setPivotY(0);
                    page.setRotation(90f);
                }
            }
        };
        return pageTransformer;
    }

    @BindView(R.id.rela_bottom)
    RelativeLayout relaBottom;

    private void showRelaBottom(int num) {
//        ObjectAnimator object = ObjectAnimator.ofFloat(relaBottom, "tranlsationY", getScreenHeight(this), getScreenHeight(this) - relaBottom.getHeight()).setDuration(2000);
//        object.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//        });
//        object.start();
        relaBottom.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_enter_anim_2));
        relaBottom.setVisibility(View.VISIBLE);

        if (adapterFrend == null) {
            initDanmu();
            initEmoji();
            initFood();
            initBackGroud();
        }
        changeTab(num);
        changeLayout(num);
        if (num == 1) {
            setDanMuVistble(false);
        } else {
            setDanMuVistble(true);
        }
    }

    private void hideRelaBottom() {
        KeybordS.closeKeybord(editSend, this);
        relaBottom.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_exit_anim_2));
        relaBottom.setVisibility(View.GONE);
//        ObjectAnimator object = ObjectAnimator.ofFloat(relaBottom, "tranlsationY", getScreenHeight(this) - relaBottom.getHeight(), getScreenHeight(this)).setDuration(2000);
//        object.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                relaBottom.setVisibility(View.GONE);
//            }
//        });
//        object.start();

    }

    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.edit_send)
    EditText editSend;
    @BindView(R.id.rela_key)
    RelativeLayout relaKey;

    @BindView(R.id.tab_friend)
    RelativeLayout tabFriend;
    @BindView(R.id.tab_emoji)
    RelativeLayout tabEmoji;
    @BindView(R.id.tab_food)
    RelativeLayout tabFood;
    @BindView(R.id.tab_groud)
    RelativeLayout tabGroud;
    @BindView(R.id.tab_shop)
    RelativeLayout tabShop;
    @BindView(R.id.tab_rank)
    RelativeLayout tabRank;
    @BindView(R.id.tab_pan)
    RelativeLayout tabPan;

    @BindView(R.id.rela_danmu_d)
    RelativeLayout relaDanmuD;
    @BindView(R.id.rela_food)
    RelativeLayout relaFood;
    @BindView(R.id.my_scrollView)
    RelativeLayout myScrollView;
    @BindView(R.id.rela_groud)
    RelativeLayout relaGroud;

    @BindView(R.id.recycler_frend)
    RefreshRecyclerView recyclerFrend;
    @BindView(R.id.recycler_food)
    RefreshRecyclerView recyclerFood;
    @BindView(R.id.my_viewpager)
    ViewPager myViewpager;
    @BindView(R.id.recycler_groud)
    RefreshRecyclerView recyclerGroud;

    @BindView(R.id.rela_cy)
    RelativeLayout relaCy;
    @BindView(R.id.rela_emoji)
    RelativeLayout relaEmoji;
    @BindView(R.id.rela_dw)
    RelativeLayout relaDw;
    @BindView(R.id.rela_vip_d)
    RelativeLayout relaVipD;

    private int positionKey = 0;

    private void freshLayout(int num) {
        switch (num) {
            case 1:
                if (friendListD.size() > 0) {
                    for (int i = 0; i < friendListD.size(); i++) {
                        friendListD.get(i).setChosice(false);
                    }
                    friendListD.get(friendPosition).setChosice(true);
                    getData(true, 0);
                    MyApplication.getMyApplication().haoyouID = this.friendList.get(friendPosition).getYhid() + "";
                    this.setThingGone();
                    this.isHyTp = true;
                    this.getMyFriend(this.friendList.get(friendPosition).getYhid() + "");
                    this.showFrend(true);
                    setDanMuVistble(false);
                }
                break;
            case 2:
                if ("".equals(MyApplication.getMyApplication().haoyouID)) {
                    if (friendList.size() > 0) {
                        chNo1(this.friendList.get(0).getYhid() + "", 0);
                    }
                } else {
                    if (friendList.size() > 0) {
                        chNo1(this.friendList.get(friendPosition).getYhid() + "", friendPosition);
                    }
                }
                break;
            case 3:
                getData(true);
                break;
            case 4:
                getDataBack(true);
                break;
        }
    }

    private void changeTab(int num) {
        tabFriend.setBackgroundColor(Color.parseColor("#EDEEF2"));
        tabFood.setBackgroundColor(Color.parseColor("#EDEEF2"));
        tabEmoji.setBackgroundColor(Color.parseColor("#EDEEF2"));
        tabGroud.setBackgroundColor(Color.parseColor("#EDEEF2"));
        tabShop.setBackgroundColor(Color.parseColor("#EDEEF2"));
        tabRank.setBackgroundColor(Color.parseColor("#EDEEF2"));
        tabPan.setBackgroundColor(Color.parseColor("#EDEEF2"));
        switch (num) {
            case 1:
                tabFriend.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                tabEmoji.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 3:
                tabFood.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 4:
                tabGroud.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 5:
                tabShop.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 6:
                tabRank.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 7:
                tabPan.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
        }
    }

    private void changeLayout(int num) {
        relaDanmuD.setVisibility(View.GONE);
        relaFood.setVisibility(View.GONE);
        myScrollView.setVisibility(View.GONE);
        relaGroud.setVisibility(View.GONE);
        switch (num) {
            case 1:
                relaDanmuD.setVisibility(View.VISIBLE);
                positionKey = 1;
                break;
            case 2:
                myScrollView.setVisibility(View.VISIBLE);
                positionKey = 2;
                break;
            case 3:
                relaFood.setVisibility(View.VISIBLE);
                positionKey = 3;
                break;
            case 4:
                relaGroud.setVisibility(View.VISIBLE);
                positionKey = 4;
                break;
        }
    }

    private void goToActivity(int num) {
        switch (num) {
            case 5:
                this.gotoSomeActivity(this, AtyTag.ATY_PetShop, true);
                break;
            case 6:
                this.gotoSomeActivity(this, AtyTag.ATY_LoveRank, true);
                break;
            case 7:
                if (this.list != null && this.listBeen != null) {
                    this.shaungchou = this.listBeen.get(0).getVtwolx();
                    this.djs = this.listBeen.get(0).getVdjs();
                    this.gotoSomeActivity(this, AtyTag.ATY_LuckPan2, true);
                }
                break;
        }
    }

    public void dismiss() {
        this.clearChoisce();
        MyApplication.getMyApplication().haoyouID = "";
        setMainPet();
        hideRelaBottom();
        setDanMuVistble(true);
    }


    private void setMainPet() {
        this.showAnimation(1, MyApplication.getMyApplication().userInfo.getPetid(), MyApplication.getMyApplication().userInfo.getPetdj());
        this.setThingGone();
        this.showFrend(false);
        this.getSJWPList();
        this.setImgPetZ(this.bean.getTpurl());
    }

    // ---------------- 好友区域
    private View footFrend;
    public List<FriendListBean.DataBean.ListBean> friendListD;
    private FrendRecyclerAdapter2 adapterFrend;

    private boolean isDelF = false;

    private void getfriendList() {
        friendListD = this.friendList;
        if (friendListD != null) {
            for (int i = 0; i < friendListD.size(); i++) {
                friendListD.get(i).setChosice(false);
            }
        } else {
            return;
        }
    }

    private void initDanmu() {
        getfriendList();
        footFrend = LayoutInflater.from(this).inflate(R.layout.pop_food_recycler_item, null);
        footFrend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                MainActivity.this.gotoSomeActivity(MainActivity.this, AtyTag.ATY_NewFrend, true);
            }
        });
        adapterFrend = new FrendRecyclerAdapter2(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerFrend.setLayoutManager(layoutManager);
        recyclerFrend.setAdapter(adapterFrend);
        recyclerFrend.setItemAnimor(null);
        getData(false, 0);
    }

    public void setChoise(List<FriendListBean.DataBean.ListBean> listBeen, int position) {
        friendListD.clear();
        friendListD.addAll(listBeen);
//        initRecycler();
//        getData(true, position);
        adapterFrend.notifyItemChange(friendListD, position);
    }

    public void updataAll(List<FriendListBean.DataBean.ListBean> listBeen) {
        friendListD.clear();
        friendListD.addAll(listBeen);
        getData(true, 0);
        chNo1(friendListD.get(0).getYhid() + "", 0);
        isDelF = false;
    }

    public void updataPosition(int position) {
        if (friendListD != null && friendListD.size() > 0) {
            for (int i = 0; i < friendListD.size(); i++) {
                friendListD.get(i).setChosice(false);
            }
            friendListD.get(position).setChosice(true);
            adapterFrend.notifyItemChange(friendListD, this.friendOldPosition);
            adapterFrend.notifyItemChange(friendListD, this.friendPosition);
        }
    }

    public void getData(final boolean b, final int position) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterFrend != null) {
                    adapterFrend.removeFooter();
                    adapterFrend.clear();
                    adapterFrend.addAll(friendListD);
                    adapterFrend.setFooter(footFrend);
                }
            }
        }, 100);
    }

    // ---------------- 好友区域

    // ---------------- 食物区域

    private View footFood;
    private FoodRecyclerAdapter2 adapterFood;

    private List<MyFoodBean.DataBean.ListBean> listFood;

    private void initFood() {
        this.listFood = this.foodList;
        footFood = LayoutInflater.from(this).inflate(R.layout.pop_food_recycler_item, null);
        footFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                MainActivity.this.addType = "two";
                MainActivity.this.gotoSomeActivity(MainActivity.this, AtyTag.ATY_AddFood, true);
            }
        });
        initRecycler();
    }

    private void initRecycler() {
        adapterFood = new FoodRecyclerAdapter2(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerFood.setLayoutManager(layoutManager);
        recyclerFood.setItemAnimor(null);
        recyclerFood.setAdapter(adapterFood);
        getData(true);
    }

    public void getData(final boolean b) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterFood != null) {
                    adapterFood.removeFooter();
                    adapterFood.clear();
                    adapterFood.addAll(listFood);
                    adapterFood.setFooter(footFood);
                }
            }
        }, 100);
    }

    // ---------------- 食物区域

    //  --------------- 表情区域 ------------------

    private void initEmoji() {
//        myScrollView = (RelativeLayout) view.findViewById(R.id.my_scrollView);
//        ViewGroup.LayoutParams params = myScrollView.getLayoutParams();
//        params.height = getScreenHeight(context) / 3;
//        myScrollView.setLayoutParams(params);
        setPageAdapter();
        myViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeBottom(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPageAdapter() {
        List<View> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            View page = LayoutInflater.from(this).inflate(R.layout.pop_drawer2_page, null);
            RecyclerView myRecycler = (RecyclerView) page.findViewById(R.id.my_recyclerview);
            myRecycler.setLayoutManager(new GridLayoutManager(this, 7));
            myRecycler.setItemAnimator(null);
            switch (i) {
                case 0:
                    EmojiAdapter cy = new EmojiAdapter(this, EmjoyUtil.getChangYongEmjoy());
                    cy.setOnItemClickListener(new EmojiAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
//                            Message msg = new Message();
//                            msg.what = 1;
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("position", postion);
//                            msg.setData(bundle);
//                            handler.sendMessage(msg);
                            setEmjoy(1, postion);
                        }
                    });
                    myRecycler.setAdapter(cy);
                    break;
                case 1:
                    EmojiAdapter em = new EmojiAdapter(this, EmjoyUtil.getImgEmoji());
                    em.setOnItemClickListener(new EmojiAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
//                            Message msg = new Message();
//                            msg.what = 0;
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("position", postion);
//                            msg.setData(bundle);
//                            handler.sendMessage(msg);
                            setEmjoy(0, postion);
                        }
                    });
                    myRecycler.setAdapter(em);
                    break;
                case 2:
                    EmojiAdapter dw = new EmojiAdapter(this, EmjoyUtil.getImgDongWu());
                    dw.setOnItemClickListener(new EmojiAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
//                            Message msg = new Message();
//                            msg.what = 2;
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("position", postion);
//                            msg.setData(bundle);
//                            handler.sendMessage(msg);
                            setEmjoy(2, postion);
                        }
                    });
                    myRecycler.setAdapter(dw);
                    break;
                case 3:
                    EmojiAdapter vip = new EmojiAdapter(this, EmjoyUtil.getImgVIP());
                    vip.setOnItemClickListener(new EmojiAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
                            if (!"0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) && !"Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
//                                Message msg = new Message();
//                                msg.what = 3;
//                                Bundle bundle = new Bundle();
//                                bundle.putInt("position", postion);
//                                msg.setData(bundle);
//                                handler.sendMessage(msg);
                                setEmjoy(3, postion);
                            } else {
                                showDialogVIP("VIP专属表情");
                            }
                        }
                    });
                    myRecycler.setAdapter(vip);
                    break;
            }
            list.add(page);
        }
        myViewpager.setAdapter(new QuickPageAdapter<>(list));
    }

    private void changeBottom(int position) {
        relaCy.setBackgroundColor(Color.parseColor("#EDEEF2"));
        relaEmoji.setBackgroundColor(Color.parseColor("#EDEEF2"));
        relaDw.setBackgroundColor(Color.parseColor("#EDEEF2"));
        relaVipD.setBackgroundColor(Color.parseColor("#EDEEF2"));
        switch (position) {
            case 0:
                relaCy.setBackgroundColor(Color.parseColor("#E6E6E6"));
                break;
            case 1:
                relaEmoji.setBackgroundColor(Color.parseColor("#E6E6E6"));
                break;
            case 2:
                relaDw.setBackgroundColor(Color.parseColor("#E6E6E6"));
                break;
            case 3:
                relaVipD.setBackgroundColor(Color.parseColor("#E6E6E6"));
                break;
        }
    }

    public void setEmjoy(int num, int position) {
        String tag = "";
        int resources = 0;
        switch (num) {
            case 0:
                tag = EmjoyUtil.getImgEmojiTAG()[position] + "";
                resources = EmjoyUtil.getImgEmoji()[position];
                break;
            case 1:
                tag = EmjoyUtil.getChangYongTAG()[position] + "";
                resources = EmjoyUtil.getChangYongEmjoy()[position];
                break;
            case 2:
                tag = EmjoyUtil.getImgDongWuATG()[position] + "";
                resources = EmjoyUtil.getImgDongWu()[position];
                break;
            case 3:
                tag = EmjoyUtil.getImgVIPTAG()[position] + "";
                resources = EmjoyUtil.getImgVIP()[position];
                break;
        }
        MyLog.showLog("DDD", "tag = " + tag + " - " + "resources = " + resources);
        SpannableString spannableString = new SpannableString(tag);
        Drawable drawable = this.getResources().getDrawable(resources);
        drawable.setBounds(0, 0, UtilsTools.dip2px(this, 23), UtilsTools.dip2px(this, 23));
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(imageSpan, 0, tag.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        editSend.getText().insert(editSend.getSelectionStart(), spannableString);
    }

    private void sendLY() {
        if (!"".equals(MyApplication.getMyApplication().haoyouID)) {
            String ly = editSend.getText().toString().trim();
            if (null != ly && !"".equals(ly)) {
                this.startDanmuF(MyApplication.getMyApplication().userInfo.getYhtx(), ly, "");
                NetWork.addYhly(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                        MyApplication.getMyApplication().haoyouID,
                        ly,
                        this);
            } else {

            }
            editSend.setText("");
            editSend.setHint("弹幕留言");
        } else {
            ToastUtil.showShort(this, "请选择需要留言的好友");
        }
    }

    //购买VIP
    public void showDialogVIP(String title) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setText("立即开通");
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dismiss();
                MainActivity.this.gotoSomeActivity(MainActivity.this, AtyTag.ATY_OpenVIP, true);
            }
        });
        dialog.show();
    }

    //  --------------- 表情区域 ------------------

    //  --------------- 背景区域 ------------------
    private View footBack;
    private BackgroudRecyclerAdapter2 adapterBack;
    private List<BJTPBean.DataBean.ListBean> listBack = new ArrayList<>();

    private void initBackGroud() {
        if (null != listBack) {
            listBack.clear();
            listBack.addAll(bjtpList);
        }
        footBack = LayoutInflater.from(this).inflate(R.layout.pop_back_recycler_item, null);
        footBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.isBackGroud = "Y";
                MainActivity.this.gotoSomeActivity(MainActivity.this, AtyTag.ATY_ChoiceImage, true);
            }
        });
        ;
        adapterBack = new BackgroudRecyclerAdapter2(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerGroud.setLayoutManager(layoutManager);
        recyclerGroud.setAdapter(adapterBack);
        recyclerGroud.setItemAnimor(null);
        getDataBack(true);
    }

    public void getDataBack(final boolean b) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterBack != null) {
                    adapterBack.removeFooter();
                    adapterBack.clear();
                    adapterBack.addAll(listBack);
                    adapterBack.setFooter(footBack);
                }
            }
        }, 100);
    }

    //  --------------- 背景区域 ------------------

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        MyLog.showLog("DRA", "top - " + top + " - oldTop - " + oldTop + " - bottom - " + bottom + " - oldBottom - " + oldBottom);
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > 0)) {
            MyLog.showLog("DRA", "弹起 - ");
            ViewGroup.LayoutParams params = relaKey.getLayoutParams();
            params.height = 1;
            relaKey.setLayoutParams(params);
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > 0)) {
            MyLog.showLog("DRA", "关闭 - ");
            ViewGroup.LayoutParams params = relaKey.getLayoutParams();
            params.height = UtilsTools.dip2px(this, 180);
            relaKey.setLayoutParams(params);
            freshLayout(positionKey);
        }
    }

    //更改宠物名称
    public void showDialogChangePetNc(final String nc) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("花费100元宝修改宠物昵称？");
        tvData.setText("现有元宝：" + MyApplication.getMyApplication().userInfo.getYbcns());
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int need = 100;
                int have = MyApplication.getMyApplication().userInfo.getYbcns();
                dialog.dismiss();
                if (need > have) {
                    showDialog();
                } else {
                    changePetNc(nc);
                }

            }
        });
        dialog.show();
    }


    //失败
    public void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_three);
        dialog.setCancelable(true);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAdvertisemen3();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void gotoAdvertisemen3() {
        Intent intent = new Intent(this, Advertisement3Activity.class);
        startActivityForResult(intent, 101);
    }

    public void showAdDialog(String ybs) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_ad);
        dialog.setCancelable(true);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        tvData.setText("恭喜您获得元宝：" + ybs);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getJCSJ();
            }
        });
        dialog.show();
    }

}
