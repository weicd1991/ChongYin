package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.PetShopRecyclerAdapter2;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.bean.PetBean;
import com.jsmy.chongyin.bean.PetTPSiseBean;
import com.jsmy.chongyin.bean.PetTpListBean;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.service.DownLoadService;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.CheckFiel;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;
import com.jsmy.chongyin.view.RefreshRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PetShopActivity extends BaseActivity implements NetWork.CallListener {
    @BindView(R.id.pet_recycler)
    RefreshRecyclerView petRecycler;
    private PetShopRecyclerAdapter2 adapter2;
    private Handler handler;
    private List<PetBean.DataBean.ListBean> list;
    private Context context;
    private List<PetTpListBean.DataBean.ListBean> petTpList;

    private int item;
    public int num = 1;
    private int petid;
    public boolean isDowload = false;
    public boolean isEnable = true;
    private View header;
    private RelativeLayout relaHeader;
    private PetTPSiseBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_pet_shop;
    }

    @Override
    protected void initView() {
        creatFolder();
        context = this;
        list = new ArrayList<>();
        initRecycler();
    }

    private void initRecycler() {
        handler = new Handler();
        adapter2 = new PetShopRecyclerAdapter2(this);
        petRecycler.setLayoutManager(new LinearLayoutManager(this));
        petRecycler.setAdapter(adapter2);
        petRecycler.setItemAnimor(null);
        header = LayoutInflater.from(this).inflate(R.layout.recycler_headview, null);
        relaHeader = (RelativeLayout) header.findViewById(R.id.rela_header);
    }

    public void getData(final boolean b, final int item) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (b) {
                    adapter2.notifyItemChanged(item);
                } else {
                    adapter2.removeHeader();
                    adapter2.removeFooter();
                    adapter2.clear();
                    adapter2.addAll(list);
                    adapter2.setHeader(header);
                    adapter2.setFooter(R.layout.recycler_footview2);
                }
            }
        }, 100);
    }

    @Override
    protected void initData() {
        getPetDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    //访问宠物商店
    private void getPetDate() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_PET_SHOP_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //购买宠物
    public void buyPet(int petid, String hqlx, String ybcns, int item) {
        this.petid = petid;
        this.item = item;
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("petid", petid + "");
        map.put("hqlx", hqlx + "");
        map.put("ybcns", ybcns + "");
        NetWork.getNetVolue(ServiceCode.UP_DATE_PET_SHOP, map, ServiceCode.NETWORK_GET, null);
    }

    //访问宠物下载列表
    public void getPetTpList(int petid, int item) {
        this.petid = petid;
        this.item = item;
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("petid", petid + "");
        map.put("petdj", "1");
        NetWork.getNetVolue(ServiceCode.GET_XZ_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //后台记录下载
    public void setPetDowload(String petid) {
        MyLog.showLog("zzz", " -- " + petid + " -- ");
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("petid", petid);
        NetWork.getNetVolue(ServiceCode.UP_DATE_IS_PETTP, map, ServiceCode.NETWORK_GET, null);
    }

    //设置宠物
    public void setPet(String petid) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("petid", petid);
        NetWork.getNetVolue(ServiceCode.UP_DATE_PET, map, ServiceCode.NETWORK_GET, null);
    }

    //更新登录数据
    private void loginData() {
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("openid", SharePrefUtil.getString(this, "openid", ""));
        NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
    }
    //访问登录接口，更新数据
    private void getLoginDate() {
        String yhid = SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + "");
        String openid = SharePrefUtil.getString(this, "openid", "");
        String dh = SharePrefUtil.getString(this, "dh", MyApplication.getMyApplication().userInfo.getDh() + "");
        String mm = SharePrefUtil.getString(this, "mm", MyApplication.getMyApplication().userInfo.getMm() + "");
        map.clear();
        if (!"".equals(openid)) {
            map.put("yhid", yhid);
            map.put("openid", openid);
            NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
        } else {
            map.put("dh", dh);
            map.put("mm", mm);
            NetWork.getNetVolue(ServiceCode.GET_DH_LOGIN, map, ServiceCode.NETWORK_GET, null);
        }

    }

    //更新基础数据
    private void getJCSJ() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SY_JCSJ, map, ServiceCode.NETWORK_GET, null);
    }

    @Override
    protected void paresData(String result, String code) {
        switch (code) {
            case "download":
                MyLog.showLog("CCC", "result = " + result);
                if (!"".equals(result) && !"main".equals(result)) {
                    int i = Integer.parseInt(result);
                    list.get(i - 1).setPress(list.get(i - 1).getPress() + 1);
                    MyLog.showLog("CCC", "数据返回 itme =  " + result + " press = " + list.get(i - 1).getPress() + " isDowload = " + isDowload + " num = " + num);
                    if (isDowload && num <= 26) {
                        getData(true, i);
                        tpDowload(num);
                    } else if (num > 26) {
                        setPetDowload(petid + "");
                    }
                }
                break;
            case "Y":
                seletData(result);
                break;
            case "N":

                break;
            case "network":
                choseDialog(Integer.parseInt(result));
                break;
        }
    }

    //分发数据
    private void seletData(String result) {
        String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
        switch (check) {
            case ServiceCode.GET_PET_SHOP_LIST_NUM:
                list.clear();
                list.addAll(gson.fromJson(result, PetBean.class).getData().getList());
                ViewGroup.LayoutParams params = relaHeader.getLayoutParams();
                switch (list.size()) {
                    case 0:
                        params.height = UtilsTools.dip2px(this, 540);
                        break;
                    case 1:
                        params.height = UtilsTools.dip2px(this, 360);
                        break;
                    case 2:
                        params.height = UtilsTools.dip2px(this, 180);
                        break;
                    default:
                        params.height = UtilsTools.dip2px(this, 140);
                        break;
                }
                relaHeader.setLayoutParams(params);
                getData(false, 0);
                break;
            case ServiceCode.UP_DATE_PET_NUM:
                showDialogSucess("宠物更换成功！");
                isEnable = true;
                closeDowload();
                num = 1;
                setPetDowload(petid + "");
                getPetDate();
                getLoginDate();
                getJCSJ();
                break;
            case ServiceCode.GET_PET_TP_NUM:
                dataBean = gson.fromJson(result, PetTPSiseBean.class).getData();
                if (0 == petTpList.size()) {

                } else {
                    if (CheckNetWork.getNetWorkType(this) == CheckNetWork.NETWORKTYPE_WIFI) {
                        tpDowload(num);
                    } else {
                        showDialogWIFI(num);
                    }
                }
                break;
            case ServiceCode.GET_XZ_LIST_NUM:
                petTpList = gson.fromJson(result, PetTpListBean.class).getData().getList();
                getPetSise(petid + "");
                break;
            case ServiceCode.UP_DATE_PET_SHOP_NUM:
//                getPetDate();
                list.get(item - 1).setIsyy("Y");
                getData(true, item);
                getPetTpList(petid, item);
                getJCSJ();
                break;
            case ServiceCode.GET_LOGIN_NUM:
                MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                EventBus.getDefault().post(new DowloadEvent("reflashpet", "reflashpet"));
                openDeskPet("o");
                break;
            case ServiceCode.UP_DATE_IS_PETTP_NUM:
                getPetDate();
                break;
        }

    }

    //下载
    public void tpDowload(int i) {
        isDowload = true;
        //眨眼 1-12
        //吃 13-33
        //打招呼 34-48
        //哭泣 49-63
        //笑 64-75
        Intent intent = new Intent(context, DownLoadService.class);
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
        intent.putExtra("item", item + "");
        MyLog.showLog("CCC", "传递的item " + item);
        startService(intent);
        num++;
    }

    //暂停下载
    public void cancelDowload(int i) {
        isDowload = false;
        Intent intent = new Intent(this, DownLoadService.class);
        stopService(intent);
    }

    //下载完成，关闭service
    private void closeDowload() {
        Intent intent = new Intent(context, DownLoadService.class);
        stopService(intent);
    }

    @OnClick({R.id.img_close})
    public void onViewClicked() {
        finish();
    }

    //购买宠物
    public void showDialogChangePetNc(final String yb, final int petid, final int item, final String hqlx) {
        this.petid = petid;
        this.item = item;
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("花费" + yb + "元宝购买宠物？");
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
//                int need = Integer.parseInt(yb);
//                int have = MyApplication.getMyApplication().userInfo.getYbcns();
//                if (need > have) {
//                    showDialogFail();
//                } else {
//                    checkBuyPet(yb, petid, item, hqlx);
//                }
                buyPet(petid, hqlx, yb, item);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //确定购买宠物
    public void checkBuyPet(final String ybcns, final int petid, final int item, final String hqlx) {
        this.item = item;
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("确定购买宠物吗？");
        tvData.setText("现有元宝：" + MyApplication.getMyApplication().userInfo.getYbcns());
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
                dialog.dismiss();
                buyPet(petid, hqlx, ybcns, item);
            }
        });
        dialog.show();
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
                PetShopActivity.this.gotoSomeActivity(PetShopActivity.this, AtyTag.ATY_OpenVIP, true);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //更换宠物1
    public void showDialogGH(String title, final int petid, final String hqlx, final String ybcns, final int item) {
        this.petid = petid;
        this.item = item;
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
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyPet(petid, hqlx, ybcns, item);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //更换宠物2
    public void showDialogGH2(String title, final int petid, final int item) {
        this.petid = petid;
        this.item = item;
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
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPetImg(petid + "", "1") < 25) {
                    getPetTpList(petid, item);
                } else {
                    setPet(petid + "");
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //判断环境
    public void showDialogWIFI(final int num) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        DecimalFormat df = new DecimalFormat("#.0");
        tvTitle.setText("当前为非WiFi环境，下载需要消耗流量" + dataBean.getVzsize() + "M。\n确定下载吗？");
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
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tpDowload(num);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void getPetSise(String petid) {
        map.clear();
        map.put("petid", petid);
        map.put("petdj", "1");
        NetWork.getNetVolue(ServiceCode.GET_PET_TP, map, ServiceCode.NETWORK_GET, null);
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

    private double getSise(int i) {
        PetBean.DataBean.ListBean listBean = list.get(i);
        double d = listBean.getZyjsize1() + listBean.getZyjsize2() + listBean.getZyjsize3() + listBean.getZyjsize4() +
                listBean.getCsize1() + listBean.getCsize2() + listBean.getCsize3() + listBean.getCsize4() + listBean.getCsize5() + listBean.getCsize6() + listBean.getCsize7() +
                listBean.getDzhsize1() + listBean.getDzhsize2() + listBean.getDzhsize3() + listBean.getDzhsize4() + listBean.getDzhsize5() +
                listBean.getKusize1() + listBean.getKusize2() + listBean.getKusize3() + listBean.getKusize4() + listBean.getKusize5() +
                listBean.getXsize1() + listBean.getXsize2() + listBean.getXsize3() + listBean.getXsize4();
        return d;
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == 101) {
                NetWork.updateYhybs(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), this);
            }
        }
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

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {

            if (type.equals(API.UPDATE_YHYBS)) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String data = jsonObject.optString("data");
                    showAdDialog(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } else {
            ToastUtil.showShort(this, msg);
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }
}
