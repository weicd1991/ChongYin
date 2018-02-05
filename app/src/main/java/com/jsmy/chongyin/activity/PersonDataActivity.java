package com.jsmy.chongyin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.MyInfoBean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.view.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonDataActivity extends BaseActivity {


    @BindView(R.id.img_tx)
    CircleImageView imgTx;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.rela_vip)
    RelativeLayout relaVip;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_nc)
    TextView tvNc;
    @BindView(R.id.tv_xb)
    TextView tvXb;
    @BindView(R.id.tv_nl)
    TextView tvNl;
    @BindView(R.id.tv_qm)
    TextView tvQm;
    @BindView(R.id.img_dj)
    ImageView imgDj;

    private MyInfoBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_person_data;
    }

    @Override
    protected void initView() {
        isFrendData = getIntent().getStringExtra("isFrendData");
    }

    @Override
    protected void initData() {
        getPersonInfo();
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
                case ServiceCode.GET_WO_MATERIAL_LIST_NUM:
                    bean = gson.fromJson(result, MyInfoBean.class).getData();
                    setPersonInfo(bean);
                    break;
                case ServiceCode.UP_DATE_TX_NUM:
//                    Toast.makeText(this, "头像修改成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(this, "头像修改成功！");
                    getPersonInfo();
                    break;
            }
        } else if ("change".equals(code)) {
            getPersonInfo();
        } else if ("crop".equals(code)) {
            getPersonInfo();
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    //访问我的个人信息
    private void getPersonInfo() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_WO_MATERIAL_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //设置我的信息
    private void setPersonInfo(MyInfoBean.DataBean bean) {
        NetWork.setImgGlide(this, bean.getTx(), imgTx);
        tvId.setText("ID:" + bean.getYhid());
        tvVip.setText("V" + bean.getVipdj());
        if ("0".equals(bean.getVipdj())) {
            relaVip.setVisibility(View.GONE);
        } else {
            relaVip.setVisibility(View.VISIBLE);
            if ("Y".equals(bean.getIsgq())) {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_guoqihuiyuan_da);
            } else {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_dengji_icon);
            }
        }
        tvNc.setText("" + bean.getNc());
        tvXb.setText("" + bean.getXb());
        tvNl.setText("" + bean.getBdate());
        tvQm.setText("" + ToDBC(bean.getQm()));
    }

    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    @OnClick({R.id.img_tx, R.id.tv_nc, R.id.img_nc, R.id.tv_xb, R.id.img_xb, R.id.tv_nl, R.id.img_nl, R.id.img_qm, R.id.tv_qm,
            R.id.img_close, R.id.rela_nc_line, R.id.rela_xb_line, R.id.rela_nl_line, R.id.rela_qm_line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_tx:
                if (!"Y".equals(isFrendData)) {
                    gotoSomeActivity(this, AtyTag.ATY_ChoiceImage, true);
                }
                break;
            case R.id.tv_nc:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getNc();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeName, true);
                }
                break;
            case R.id.img_nc:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getNc();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeName, true);
                }
                break;
            case R.id.rela_nc_line:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getNc();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeName, true);
                }
                break;
            case R.id.tv_xb:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getXb();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeGender, true);
                }
                break;
            case R.id.img_xb:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getXb();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeGender, true);
                }
                break;
            case R.id.rela_xb_line:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getXb();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeGender, true);
                }
                break;
            case R.id.tv_nl:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getBdate();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeAge, true);
                }
                break;
            case R.id.img_nl:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getBdate();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeAge, true);
                }
                break;
            case R.id.rela_nl_line:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getBdate();
                    gotoSomeActivity(this, AtyTag.ATY_PersonChangeAge, true);
                }
                break;
            case R.id.img_qm:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getQm();
                    gotoSomeActivity(this, AtyTag.ATY_PersonSignature, true);
                }
                break;
            case R.id.tv_qm:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getQm();
                    gotoSomeActivity(this, AtyTag.ATY_PersonSignature, true);
                }
                break;
            case R.id.rela_qm_line:
                if (!"Y".equals(isFrendData) && null != bean) {
                    personDate = bean.getQm();
                    gotoSomeActivity(this, AtyTag.ATY_PersonSignature, true);
                }
                break;
            case R.id.img_close:
                finish();
                break;
            default:
                break;
        }
    }
}
