package com.jsmy.chongyin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.VIPInfobean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.view.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class BeingVIPActivity extends BaseActivity {

    @BindView(R.id.img_tx)
    CircleImageView imgTx;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_lv)
    TextView tvLv;
    @BindView(R.id.tv_curr)
    TextView tvCurr;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.btn_check)
    Button btnCheck;

    private VIPInfobean.DataBean info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_being_vip;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    @Override
    protected void initData() {
        getVIPInfo();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_VIP_NUM:
                    info = gson.fromJson(result, VIPInfobean.class).getData();
                    setDate();
                    break;
                case ServiceCode.UP_DATE_GM_VIP_NUM:
                    getVIPInfo();
                    break;
            }
        } else if ("close".equals(code)) {
            getVIPInfo();

        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }

    }

    //访问VIP数据
    private void getVIPInfo() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_VIP, map, ServiceCode.NETWORK_GET, null);
    }

    //填充数据
    private void setDate() {
        NetWork.setImgGlide(this, info.getTx(), imgTx);
        tvName.setText(info.getNc());
        tvLv.setText("V" + info.getVipdj());
        if ("Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
            tvLv.setBackgroundResource(R.drawable.base_rectangle8);
        } else {
            tvLv.setBackgroundResource(R.drawable.base_rectangle7);
        }
        tvCurr.setText("" + info.getVipjyz());
        tvAll.setText("/" + info.getMaxjyz());
        if (!"0".equals(info.getVipdj())) {
            if ("Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                btnCheck.setText("立即开通");
            } else {
                btnCheck.setText("续费" + "(" + info.getSj() + "到期)");
            }
        } else {
            btnCheck.setText("立即开通");
        }

    }

    @OnClick({R.id.btn_check, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_check:
//                finish();
                gotoSomeActivity(this, AtyTag.ATY_OpenVIP, true);
                break;
            case R.id.img_close:
                finish();
//                gotoSomeActivity(this, AtyTag.ATY_PersonCenter, true);
                break;

        }
    }
}
