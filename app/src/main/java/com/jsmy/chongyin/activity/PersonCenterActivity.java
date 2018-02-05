package com.jsmy.chongyin.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.ApplistBean;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.MyInfoBean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.HuaweiUtils;
import com.jsmy.chongyin.utils.MeizuUtils;
import com.jsmy.chongyin.utils.MiuiUtils;
import com.jsmy.chongyin.utils.QikuUtils;
import com.jsmy.chongyin.utils.RomUtils;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.view.CircleImageView;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonCenterActivity extends BaseActivity {

    @BindView(R.id.tv_close_pet)
    TextView tvClosePet;
    @BindView(R.id.img_tx)
    CircleImageView imgTx;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.rela_vip)
    RelativeLayout relaVip;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.img_dj)
    ImageView imgDj;
    @BindView(R.id.img_new)
    CircleImageView imgNew;
    @BindView(R.id.img_dian)
    CircleImageView imgDian;

    private List<ApplistBean.DataBean.ListBean> applist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_person_center;
    }

    @Override
    protected void initView() {
        setDesk(MyApplication.getMyApplication().userInfo.getZmpet());
    }

    private void setDesk(String result) {
        String tag = SharePrefUtil.getStringPet(this, AtyTag.DeskPet, "0");
        if (tag != null && !"".equals(tag) && !"0".equals(tag)) {
            if (checkPermission(this)) {
                tvClosePet.setText(tag + "%");
            } else {
                tvClosePet.setText("关闭");
            }
        } else {
            tvClosePet.setText("关闭");
        }
    }

    private boolean checkPermission(Context context) {
        //6.0 版本之后由于 google 增加了对悬浮窗权限的管理，所以方式就统一了
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                return miuiPermissionCheck(context);
            } else if (RomUtils.checkIsMeizuRom()) {
                return meizuPermissionCheck(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                return huaweiPermissionCheck(context);
            } else if (RomUtils.checkIs360Rom()) {
                return qikuPermissionCheck(context);
            }
        }
        return commonROMPermissionCheck(context);
    }

    private boolean huaweiPermissionCheck(Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }

    private boolean miuiPermissionCheck(Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }

    private boolean meizuPermissionCheck(Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }

    private boolean qikuPermissionCheck(Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }

    private boolean commonROMPermissionCheck(Context context) {
        //最新发现魅族6.0的系统这种方式不好用，天杀的，只有你是奇葩，没办法，单独适配一下
        if (RomUtils.checkIsMeizuRom()) {
            return meizuPermissionCheck(context);
        } else {
            Boolean result = true;
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Class clazz = Settings.class;
                    Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
                    result = (Boolean) canDrawOverlays.invoke(null, context);
                } catch (Exception e) {
                    Log.e(AtyTag.ATY_DesktopPets, Log.getStackTraceString(e));
                }
            }
            return result;
        }
    }

    @Override
    protected void initData() {
        getPersonInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
        getApplyList();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_WO_MATERIAL_LIST_NUM:
                    MyInfoBean.DataBean bean = gson.fromJson(result, MyInfoBean.class).getData();
                    setPersonInfo(bean);
                    break;
                case ServiceCode.UP_DATE_TX_NUM:
                    getPersonInfo();
                    break;
                case ServiceCode.APPLY_LIST_NUM:
                    applist = gson.fromJson(result, ApplistBean.class).getData().getList();
                    if (applist != null && applist.size() > 0) {
                        NetWork.setImgGlide(this,applist.get(0).getTx(),imgNew);
                        imgNew.setVisibility(View.VISIBLE);
                        imgDian.setVisibility(View.VISIBLE);
                    }else {
                        imgNew.setVisibility(View.GONE);
                        imgDian.setVisibility(View.GONE);
                    }
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            if ("desktop".equals(code)) {
                setDesk(result);
            } else if ("close".equals(code)) {
                getPersonInfo();
            } else if ("crop".equals(code)) {
                getPersonInfo();
            } else if ("haoyou".equals(code)) {
                getApplyList();
            }
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
    }


    @OnClick({R.id.img_setting, R.id.img_tx, R.id.img_arrow_pet, R.id.img_arrow_huiyuan, R.id.img_arrow_mark,
            R.id.img_arrow_frend, R.id.img_close, R.id.btn_pet, R.id.btn_vip, R.id.btn_shop, R.id.btn_friend,
            R.id.rela_pet, R.id.rela_huiyan, R.id.rela_mark,R.id.rela_danmu ,R.id.rela_frend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_setting:
                gotoSomeActivity(this, AtyTag.ATY_Setting, false);
                break;
            case R.id.img_tx:
                isFrendData = "N";
                gotoSomeActivity(this, AtyTag.ATY_PersonData, true);
                break;
            case R.id.img_arrow_pet:
                gotoSomeActivity(this, AtyTag.ATY_DesktopPets, true);
                break;
            case R.id.btn_pet:
                gotoSomeActivity(this, AtyTag.ATY_DesktopPets, true);
                break;
            case R.id.rela_pet:
                gotoSomeActivity(this, AtyTag.ATY_DesktopPets, true);
                break;
            case R.id.img_arrow_huiyuan:
                gotoSomeActivity(this, AtyTag.ATY_BeingVIP, true);
                break;
            case R.id.btn_vip:
                gotoSomeActivity(this, AtyTag.ATY_BeingVIP, true);
                break;
            case R.id.rela_huiyan:
                gotoSomeActivity(this, AtyTag.ATY_BeingVIP, true);
                break;
            case R.id.img_arrow_mark:
                gotoSomeActivity(this, AtyTag.ATY_PetShop, true);
                break;
            case R.id.btn_shop:
                gotoSomeActivity(this, AtyTag.ATY_PetShop, true);
                break;
            case R.id.rela_mark:
                gotoSomeActivity(this, AtyTag.ATY_PetShop, true);
                break;
            case R.id.rela_danmu:
                gotoSomeActivity(this, AtyTag.ATY_DanMuLiuYan, false);
                break;
            case R.id.img_arrow_frend:
                gotoSomeActivity(this, AtyTag.ATY_NewFrend, true);
                break;
            case R.id.btn_friend:
                gotoSomeActivity(this, AtyTag.ATY_NewFrend, true);
                break;
            case R.id.rela_frend:
                gotoSomeActivity(this, AtyTag.ATY_NewFrend, true);
                break;
            case R.id.img_close:
                finish();
                break;

        }
    }

}
