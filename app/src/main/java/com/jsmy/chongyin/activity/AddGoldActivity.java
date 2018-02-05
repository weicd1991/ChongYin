package com.jsmy.chongyin.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.AddGoldRecyclerAdapter;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.GoldBean;
import com.jsmy.chongyin.bean.YuanbaoShopBean;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddGoldActivity extends BaseActivity {

    @BindView(R.id.real_new)
    RelativeLayout realNew;
    private AddGoldRecyclerAdapter adapter;
    private List<GoldBean> list;
    private Context context;
    private String zflx = "微信";

    private YuanbaoShopBean.DataBean bean;
    private boolean isVIP = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_add_gold;
    }

    @Override
    protected void initView() {
        context = this;
        if ("0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) || "Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
            isVIP = false;
        } else {
            isVIP = true;
        }
    }

    @Override
    protected void initData() {
        getyuanbaoShao();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.UP_DATE_YB_SHOP_NUM:
                    getyuanbaoShao();
                    EventBus.getDefault().post(new DowloadEvent("refrsh", "yuanbao"));
                    break;
                case ServiceCode.GET_YB_SHOP_LIST_NUM:
                    bean = gson.fromJson(result, YuanbaoShopBean.class).getData();
                    if ("Y".equals(bean.getIsgmxslb())) {
                        realNew.setVisibility(View.GONE);
                    } else {
                        realNew.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }

    }

    private void getyuanbaoShao() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_YB_SHOP_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }
    @OnClick({R.id.rela_gold_11, R.id.rela_gold_55, R.id.rela_gold_120, R.id.rela_gold_250, R.id.rela_gold_600,
            R.id.rela_gold_new, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rela_gold_11:
                MyApplication.getMyApplication().isxs = "N";
                moon = "11";
                if (!isVIP) {
                    price = "3.00";
//                    price = "0.01";
                } else {
                    price = "2.70";
//                    price = "0.01";
                }
                gotoSomeActivity(this, AtyTag.ATY_PayVIP, true);
                break;
            case R.id.rela_gold_55:
                MyApplication.getMyApplication().isxs = "N";
                moon = "55";
                if (!isVIP) {
                    price = "14.99";
                } else {
                    price = "13.49";
                }
                gotoSomeActivity(this, AtyTag.ATY_PayVIP, true);
                break;
            case R.id.rela_gold_120:
                MyApplication.getMyApplication().isxs = "N";
                moon = "120";
                if (!isVIP) {
                    price = "29.99";
                } else {
                    price = "26.99";
                }
                gotoSomeActivity(this, AtyTag.ATY_PayVIP, true);
                break;
            case R.id.rela_gold_250:
                MyApplication.getMyApplication().isxs = "N";
                moon = "250";
                if (!isVIP) {
                    price = "59.99";
                } else {
                    price = "53.99";
                }
                gotoSomeActivity(this, AtyTag.ATY_PayVIP, true);
                break;
            case R.id.rela_gold_600:
                MyApplication.getMyApplication().isxs = "N";
                moon = "600";
                if (!isVIP) {
                    price = "99.99";
                } else {
                    price = "89.99";
                }
                gotoSomeActivity(this, AtyTag.ATY_PayVIP, true);
                break;
            case R.id.rela_gold_new:
                MyApplication.getMyApplication().isxs = "Y";
                moon = "120";
                if (!isVIP) {
                    price = "14.99";
                } else {
                    price = "13.49";
                }
                gotoSomeActivity(this, AtyTag.ATY_PayVIP, true);
                break;
            case R.id.img_close:
                finish();
                break;
        }
    }

}
