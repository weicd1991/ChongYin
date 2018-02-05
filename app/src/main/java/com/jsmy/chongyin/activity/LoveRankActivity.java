package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.DialogPm2Adapter;
import com.jsmy.chongyin.adapter.LoveRankRecyclerAdapter;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.LoveRankBean;
import com.jsmy.chongyin.bean.ZAXbean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;
import com.jsmy.chongyin.view.CircleImageView;
import com.jsmy.chongyin.view.RefreshRecyclerView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.OnClick;

public class LoveRankActivity extends BaseActivity implements NetWork.CallListener {

    private TextView tvNumber;
    @BindView(R.id.recycler_love)
    RefreshRecyclerView recyclerLove;
    private LoveRankRecyclerAdapter adapter;
    private List<LoveRankBean.DataBean.ListBean> list;
    private Handler handler;
    private LoveRankActivity context;

    public String jrksax;

    private View view;
    private RelativeLayout relaheader;

    private CircleImageView imgTx;
    private RelativeLayout relaVip;
    private TextView tvVip;
    private ImageView imgNumber;
    private TextView tvMath;
    private TextView tvName;
    private ImageView imgLove;
    private TextView tvLoveNum;
    private TextView tvLoveLeftNum;
    private TextView tvLoveall;
    private ImageView imgVip;
    private ImageView imgZhou;
    private List<ZAXbean.DataBean> zaxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_love_rank;
    }

    @Override
    protected void initView() {
        context = this;
        view = LayoutInflater.from(this).inflate(R.layout.love_rank_heardview, null);
        relaheader = (RelativeLayout) view.findViewById(R.id.rela_heard);
        imgTx = (CircleImageView) view.findViewById(R.id.img_tx);
        relaVip = (RelativeLayout) view.findViewById(R.id.rela_vip);
        imgVip = (ImageView) view.findViewById(R.id.img_vip);
        tvVip = (TextView) view.findViewById(R.id.tv_vip);
        imgNumber = (ImageView) view.findViewById(R.id.img_number);
        tvMath = (TextView) view.findViewById(R.id.tv_math);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        imgLove = (ImageView) view.findViewById(R.id.img_love);
        tvLoveNum = (TextView) view.findViewById(R.id.tv_love_num);
        tvLoveLeftNum = (TextView) view.findViewById(R.id.tv_love_left_num);
        tvLoveall = (TextView) view.findViewById(R.id.tv_loveall);
        tvNumber = (TextView) view.findViewById(R.id.tv_number);
        imgZhou = (ImageView) view.findViewById(R.id.img_zhou);
        imgZhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zaxList != null)
                    showZhouPMDialog2();
            }
        });

        handler = new Handler();
        adapter = new LoveRankRecyclerAdapter(this);
        recyclerLove.setLayoutManager(new LinearLayoutManager(this));
        recyclerLove.setItemAnimor(null);
        recyclerLove.setAdapter(adapter);
    }

    private void getDate() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.removeHeader();
                adapter.removeFooter();
                adapter.clear();
                adapter.addAll(list);
                adapter.setHeader(view);
                adapter.setFooter(R.layout.recycler_footview3);
            }
        }, 100);
    }

    @Override
    protected void initData() {
        getPaiHangBang();
        NetWork.getyhaxph(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    private void setFirstline(LoveRankBean.DataBean.ListBean object, int pm) {
        if ("0".equals(MyApplication.getMyApplication().userInfo.getVipdj())) {
            relaVip.setVisibility(View.INVISIBLE);
        } else {
            relaVip.setVisibility(View.VISIBLE);
            tvVip.setText("V" + MyApplication.getMyApplication().userInfo.getVipdj());
            if ("Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                imgVip.setImageResource(R.mipmap.haoyouliebiao_guojidengji_xiao);
            } else {
                imgVip.setImageResource(R.mipmap.haoyouliebiao_touxianghuangguan_icon);
            }
        }
        NetWork.setImgGlide(context, object.getTx(), imgTx);
        switch (pm) {
            case 1:
                imgNumber.setVisibility(View.VISIBLE);
                tvMath.setVisibility(View.INVISIBLE);
                imgNumber.setImageResource(R.mipmap.aixinpaihangbang_no1);
                break;
            case 2:
                imgNumber.setVisibility(View.VISIBLE);
                tvMath.setVisibility(View.INVISIBLE);
                imgNumber.setImageResource(R.mipmap.aixinpaihangbang_no2);
                break;
            case 3:
                imgNumber.setVisibility(View.VISIBLE);
                tvMath.setVisibility(View.INVISIBLE);
                imgNumber.setImageResource(R.mipmap.aixinpaihangbang_no3);
                break;
            default:
                imgNumber.setVisibility(View.INVISIBLE);
                tvMath.setVisibility(View.VISIBLE);
                tvMath.setText(object.getXh() + "");
                break;
        }
        tvName.setText("" + object.getNc());
        tvLoveall.setText("爱心总数：" + object.getZaxcns());
        tvLoveLeftNum.setText("" + object.getAxcns());
        if (!"".equals(object.getYhsax()) && 0 != Integer.parseInt(object.getYhsax())) {
            tvLoveNum.setVisibility(View.VISIBLE);
            tvLoveNum.setText("" + object.getYhsax());
            imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_xuanzhong);
        } else {
            tvLoveNum.setVisibility(View.INVISIBLE);
            imgLove.setImageResource(R.mipmap.aixinpaihangbang_aixin_weixuanzhong);
        }
    }

    //访问排行榜列表
    private void getPaiHangBang() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_PHB_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //好友详情
    public void goToFriend(String yhid) {
        type = DELETFREND;
        zt = one;
        friendID = yhid;
        gotoSomeActivity(context, AtyTag.ATY_AddNewFrend, true);
    }

    //送爱心
    public void setLove(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        NetWork.getNetVolue(ServiceCode.UP_DATE_AXS, map, ServiceCode.NETWORK_GET, null);
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_PHB_LIST_NUM:
                    setRankList(result);
                    break;
                case ServiceCode.UP_DATE_AXS_NUM:
                    getJCSJ();
                    getPaiHangBang();
                    break;
                case ServiceCode.GET_SY_JCSJ_NUM:
                    getPaiHangBang();
                    break;
            }
        } else if ("refresh".equals(code)) {
            getPaiHangBang();
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    //访问基础数据
    private void getJCSJ() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SY_JCSJ, map, ServiceCode.NETWORK_GET, null);
    }

    //设置排行榜数据
    private void setRankList(String result) {
        LoveRankBean.DataBean dataBean = gson.fromJson(result, LoveRankBean.class).getData();
        tvNumber.setText("" + dataBean.getJrksax());
        jrksax = dataBean.getJrksax() + "";
        list = gson.fromJson(result, LoveRankBean.class).getData().getList();
        for (int i = 0; i < list.size(); i++) {
            if (!"".equals(list.get(i).getYhid()) && !"".equals(Integer.parseInt(list.get(i).getXh()))) {
                if (Integer.parseInt(list.get(i).getYhid()) == MyApplication.getMyApplication().userInfo.getYhid()) {
                    setFirstline(list.get(i), Integer.parseInt(list.get(i).getXh()));
                }
            }
            if ("".equals(list.get(i).getYhid())) {
                list.remove(i);
            }
        }
        ViewGroup.LayoutParams params = relaheader.getLayoutParams();
        switch (list.size()) {
            case 1:
                params.height = UtilsTools.dip2px(this, 320);
                break;
            case 2:
                params.height = UtilsTools.dip2px(this, 250);
                break;
            case 3:
                params.height = UtilsTools.dip2px(this, 180);
                break;
            default:
                params.height = UtilsTools.dip2px(this, 140);
                break;
        }
        relaheader.setLayoutParams(params);
        getDate();
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
    }

    boolean isGet = false;
    int posi = 10003;
    private Dialog dialog;

    private void showZhouPMDialog() {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        if (dialog != null && !dialog.isShowing()) {
            dialog = null;
        }
        dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_num_pm);
        dialog.setCancelable(true);
        ImageView imgClose = (ImageView) dialog.findViewById(R.id.img_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        CircleImageView tvNum1Tx = (CircleImageView) dialog.findViewById(R.id.tv_num1_tx);
        NetWork.setImgGlide(this, zaxList.get(0).getTx(), tvNum1Tx);
        TextView tvNum1Name = (TextView) dialog.findViewById(R.id.tv_num1_name);
        tvNum1Name.setText(zaxList.get(0).getNc());

        CircleImageView tvNum2Tx = (CircleImageView) dialog.findViewById(R.id.tv_num2_tx);
        NetWork.setImgGlide(this, zaxList.get(1).getTx(), tvNum2Tx);
        TextView tvNum2Name = (TextView) dialog.findViewById(R.id.tv_num2_name);
        tvNum2Name.setText(zaxList.get(1).getNc());

        CircleImageView tvNum3Tx = (CircleImageView) dialog.findViewById(R.id.tv_num3_tx);
        NetWork.setImgGlide(this, zaxList.get(2).getTx(), tvNum3Tx);
        TextView tvNum3Name = (TextView) dialog.findViewById(R.id.tv_num3_name);
        tvNum3Name.setText(zaxList.get(2).getNc());

        CircleImageView tvNum4Tx = (CircleImageView) dialog.findViewById(R.id.tv_num4_tx);
        NetWork.setImgGlide(this, zaxList.get(3).getTx(), tvNum4Tx);
        TextView tvNum4Name = (TextView) dialog.findViewById(R.id.tv_num4_name);
        tvNum4Name.setText(zaxList.get(3).getNc());

        CircleImageView tvNum5Tx = (CircleImageView) dialog.findViewById(R.id.tv_num5_tx);
        NetWork.setImgGlide(this, zaxList.get(4).getTx(), tvNum5Tx);
        TextView tvNum5Name = (TextView) dialog.findViewById(R.id.tv_num5_name);
        tvNum5Name.setText(zaxList.get(4).getNc());

        for (int i = 0; i < zaxList.size(); i++) {
            if (zaxList.get(i).getId().equals(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""))) {
                isGet = true;
                posi = i;
                break;
            }
        }
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        if (isGet) {
            if ("Y".equals(zaxList.get(posi).getPmjl())) {
                tvCheck.setText("已领取");
                tvCheck.setBackgroundResource(R.drawable.button_bg2);
            } else {
                tvCheck.setText("领取");
                tvCheck.setBackgroundResource(R.drawable.button_bg);
                tvCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (posi) {
                            case 0:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "200",
                                        LoveRankActivity.this);
                                break;
                            case 1:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "150",
                                        LoveRankActivity.this);
                                break;
                            case 2:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "100",
                                        LoveRankActivity.this);
                                break;
                            case 3:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "50",
                                        LoveRankActivity.this);
                                break;
                            case 4:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "30",
                                        LoveRankActivity.this);
                                break;
                        }
                        dialog.dismiss();
                    }
                });
            }

        } else {
            tvCheck.setText("知道了");
            tvCheck.setBackgroundResource(R.drawable.button_bg2);
        }
        dialog.show();
    }

    private void showZhouPMDialog2() {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        if (dialog != null && !dialog.isShowing()) {
            dialog = null;
        }
        dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_num_pm_2);
        dialog.setCancelable(true);
        ImageView imgClose = (ImageView) dialog.findViewById(R.id.img_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        RecyclerView myRecycler = (RecyclerView) dialog.findViewById(R.id.my_recyclerview);
        myRecycler.setLayoutManager(new LinearLayoutManager(context));
        myRecycler.setItemAnimator(null);
        DialogPm2Adapter adapter = null;
        if (zaxList != null && zaxList.size() > 0) {
            if (zaxList.size() <= 5) {
                adapter = new DialogPm2Adapter(context, zaxList);
                for (int i = 0; i < zaxList.size(); i++) {
                    if (zaxList.get(i).getId().equals(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""))) {
                        isGet = true;
                        posi = i;
                        break;
                    }
                }
            } else {
                List<ZAXbean.DataBean> newList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    newList.add(zaxList.get(i));
                }
                adapter = new DialogPm2Adapter(context, newList);
                for (int i = 0; i < 5; i++) {
                    if (newList.get(i).getId().equals(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""))) {
                        isGet = true;
                        posi = i;
                        break;
                    }
                }
            }
        }
        myRecycler.setAdapter(adapter);


        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        if (isGet) {
            if ("Y".equals(zaxList.get(posi).getPmjl())) {
                tvCheck.setText("已领取");
                tvCheck.setBackgroundResource(R.drawable.button_bg2);
            } else {
                tvCheck.setText("领取");
                tvCheck.setBackgroundResource(R.drawable.button_bg);
                tvCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (posi) {
                            case 0:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "200",
                                        LoveRankActivity.this);
                                break;
                            case 1:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "150",
                                        LoveRankActivity.this);
                                break;
                            case 2:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "100",
                                        LoveRankActivity.this);
                                break;
                            case 3:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "50",
                                        LoveRankActivity.this);
                                break;
                            case 4:
                                NetWork.updateYhybsl(SharePrefUtil.getString(LoveRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "30",
                                        LoveRankActivity.this);
                                break;
                        }
                        dialog.dismiss();
                    }
                });
            }

        } else {
            tvCheck.setText("知道了");
            tvCheck.setBackgroundResource(R.drawable.button_bg2);
        }
        dialog.show();
    }


    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {
            switch (type) {
                case API.GET_YH_AXPH:
                    zaxList = gson.fromJson(result, ZAXbean.class).getData();
                    break;
                case API.UPDATE_YHYBSL:
                    ToastUtil.showShort(this, msg);
                    NetWork.getyhaxph(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), this);
                    getJCSJ();
                    break;
            }
        } else {
            ToastUtil.showShort(this, msg);
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }
}
