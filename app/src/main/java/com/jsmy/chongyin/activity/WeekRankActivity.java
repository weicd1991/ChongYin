package com.jsmy.chongyin.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.ZAXbean;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.view.CircleImageView;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WeekRankActivity extends BaseActivity implements NetWork.CallListener {

    @BindView(R.id.tv_num1_tx)
    CircleImageView tvNum1Tx;
    @BindView(R.id.tv_num1_name)
    TextView tvNum1Name;
    @BindView(R.id.tv_num2_tx)
    CircleImageView tvNum2Tx;
    @BindView(R.id.tv_num2_name)
    TextView tvNum2Name;
    @BindView(R.id.tv_num3_tx)
    CircleImageView tvNum3Tx;
    @BindView(R.id.tv_num3_name)
    TextView tvNum3Name;
    @BindView(R.id.tv_num4_tx)
    CircleImageView tvNum4Tx;
    @BindView(R.id.tv_num4_name)
    TextView tvNum4Name;
    @BindView(R.id.tv_num5_tx)
    CircleImageView tvNum5Tx;
    @BindView(R.id.tv_num5_name)
    TextView tvNum5Name;
    @BindView(R.id.tv_check)
    TextView tvCheck;

    private List<ZAXbean.DataBean> zaxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_week_rank;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        NetWork.getyhaxph(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), this);
    }

    @Override
    protected void paresData(String result, String code) {

    }
    boolean isGet = false;
    int posi = 10003;
    private void setTextWeek(){
        tvNum1Name.setText(zaxList.get(0).getNc());
//        Glide.with(this).load(zaxList.get(0).getTx()).into(tvNum1Tx);
        NetWork.setImgGlide(this,zaxList.get(0).getTx(),tvNum1Tx);
        tvNum2Name.setText(zaxList.get(1).getNc());
//        Glide.with(this).load(zaxList.get(1).getTx()).into(tvNum2Tx);
        NetWork.setImgGlide(this,zaxList.get(1).getTx(),tvNum2Tx);
        tvNum3Name.setText(zaxList.get(2).getNc());
//        Glide.with(this).load(zaxList.get(2).getTx()).into(tvNum3Tx);
        NetWork.setImgGlide(this,zaxList.get(2).getTx(),tvNum3Tx);
        tvNum4Name.setText(zaxList.get(3).getNc());
//        Glide.with(this).load(zaxList.get(3).getTx()).into(tvNum3Tx);
        NetWork.setImgGlide(this,zaxList.get(3).getTx(),tvNum4Tx);
        tvNum5Name.setText(zaxList.get(4).getNc());
//        Glide.with(this).load(zaxList.get(4).getTx()).into(tvNum5Tx);
        NetWork.setImgGlide(this,zaxList.get(4).getTx(),tvNum5Tx);
        for (int i = 0; i < zaxList.size(); i++) {
            if (zaxList.get(i).getId().equals(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""))) {
                isGet = true;
                posi = i;
                break;
            }
        }
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
                                NetWork.updateYhybsl(SharePrefUtil.getString(WeekRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "200",
                                        WeekRankActivity.this);
                                break;
                            case 1:
                                NetWork.updateYhybsl(SharePrefUtil.getString(WeekRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "150",
                                        WeekRankActivity.this);
                                break;
                            case 2:
                                NetWork.updateYhybsl(SharePrefUtil.getString(WeekRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "100",
                                        WeekRankActivity.this);
                                break;
                            case 3:
                                NetWork.updateYhybsl(SharePrefUtil.getString(WeekRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "50",
                                        WeekRankActivity.this);
                                break;
                            case 4:
                                NetWork.updateYhybsl(SharePrefUtil.getString(WeekRankActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                                        "30",
                                        WeekRankActivity.this);
                                break;
                        }
                    }
                });
            }

        } else {
            tvCheck.setText("知道了");
            tvCheck.setBackgroundResource(R.drawable.button_bg2);
        }
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {
            switch (type) {
                case API.GET_YH_AXPH:
                    zaxList = gson.fromJson(result, ZAXbean.class).getData();
                    setTextWeek();
                    break;
                case API.UPDATE_YHYBSL:
                    ToastUtil.showShort(this, msg);
                    break;
            }
        } else {
            ToastUtil.showShort(this, msg);
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                finish();
                break;
        }
    }
}
