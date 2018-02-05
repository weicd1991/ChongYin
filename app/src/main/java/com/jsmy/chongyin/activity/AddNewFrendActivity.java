package com.jsmy.chongyin.activity;

import android.graphics.Color;
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
import com.jsmy.chongyin.bean.FriendZLBean;
import com.jsmy.chongyin.bean.SreachBean;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.view.CircleImageView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class AddNewFrendActivity extends BaseActivity {

    @BindView(R.id.img_tx)
    CircleImageView imgTx;
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
    @BindView(R.id.btn_check)
    TextView btnCheck;
    @BindView(R.id.tv_dj)
    TextView tvDj;
    @BindView(R.id.rela_dj)
    RelativeLayout relaDj;
    @BindView(R.id.img_dj)
    ImageView imgDj;

    private FriendZLBean.DataBean friendBean;
    private String yhid;
    private String type;
    private String zt;
    private SreachBean.DataBean sreachBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_add_new_frend;
    }

    @Override
    protected void initView() {
        zt = getIntent().getStringExtra("zt");
        type = getIntent().getStringExtra("type");
        yhid = getIntent().getStringExtra("friendID");
        MyLog.showLog("ZZZ", "zt - " + zt + " type - " + type + " friendID - " + yhid);
        if ("sreach".equals(type)) {
            sreachFriend();
        } else {
            sreachFriend();
            if (three.equals(zt)) {
                btnCheck.setText("等待验证");
                btnCheck.setTextColor(Color.parseColor("#cccccc"));
                btnCheck.setBackgroundResource(R.drawable.new_frend_button_bg2);
                btnCheck.setEnabled(false);
            } else if (zero.equals(zt)) {
                btnCheck.setText("添加好友");
                btnCheck.setBackgroundResource(R.drawable.add_frend_button_bg);
            } else {
                btnCheck.setText("删除好友");
                btnCheck.setBackgroundResource(R.drawable.delet_frend_button_bg);
            }
        }
        if (yhid.equals(MyApplication.getMyApplication().userInfo.getYhid() + "")) {
            btnCheck.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_FRIEND_ZL_NUM:
                    friendBean = gson.fromJson(result, FriendZLBean.class).getData();
                    setFriendDate();
                    break;
                case ServiceCode.GET_NEW_FRIEND_SS_NUM:
                    sreachBean = gson.fromJson(result, SreachBean.class).getData();
                    setSreachFriend();
                    break;
                case ServiceCode.UP_DATE_FRIEND_SQ_NUM:
//                    Toast.makeText(this, "申请成功，等待对方验证！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(this, "申请成功，等待对方验证！");
                    EventBus.getDefault().post(new DowloadEvent("refresh", "refresh"));
                    finish();
                    break;
                case ServiceCode.UP_DATE_FRIEND_DEL_NUM:
//                    Toast.makeText(this, "删除好友成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(this, "删除好友成功！");
                    EventBus.getDefault().post(new DowloadEvent("refresh", "refresh"));
                    finish();
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
//            Toast.makeText(this,DecodeData.getCodeRoMsg(result,DecodeData.MSG),Toast.LENGTH_SHORT).show();
//            finish();
        }
    }

    //访问好友资料
    private void getFriendDate() {
        map.clear();
        map.put("yhids", yhid);
        NetWork.getNetVolue(ServiceCode.GET_FRIEND_ZL, map, ServiceCode.NETWORK_GET, null);
    }

    //搜索好友资料
    private void sreachFriend() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhid);
        NetWork.getNetVolue(ServiceCode.GET_NEW_FRIEND_SS, map, ServiceCode.NETWORK_GET, null);
    }

    //填充好友资料
    private void setFriendDate() {
        tvDj.setText("V" + friendBean.getVipdj());
        if (0 == Integer.parseInt(friendBean.getVipdj())) {
            relaDj.setVisibility(View.GONE);
        } else {
            relaDj.setVisibility(View.VISIBLE);
            if ("Y".equals(friendBean.getIsgq())) {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_guoqihuiyuan_da);
            } else {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_dengji_icon);
            }
        }
        NetWork.setImgGlide(this, friendBean.getYhtx(), imgTx);
        tvId.setText("ID:" + friendBean.getYhids());
        tvNc.setText(friendBean.getYhnc());
        tvXb.setText(friendBean.getXb());
        tvNl.setText(friendBean.getBdate());
        tvQm.setText(friendBean.getQm());
    }

    //填充搜索好友资料
    private void setSreachFriend() {
        zt = sreachBean.getZt();
        tvDj.setText("V" + sreachBean.getVipdj());
        if (0 == Integer.parseInt(sreachBean.getVipdj())) {
            relaDj.setVisibility(View.GONE);
        } else {
            relaDj.setVisibility(View.VISIBLE);
            if ("Y".equals(sreachBean.getIsgq())) {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_guoqihuiyuan_da);
            } else {
                imgDj.setImageResource(R.mipmap.yonghuzhongxin_dengji_icon);
            }
        }
        NetWork.setImgGlide(this, sreachBean.getTx(), imgTx);
        tvId.setText("ID:" + sreachBean.getYhids());
        tvNc.setText(sreachBean.getNc());
        tvXb.setText(sreachBean.getXb());
        tvNl.setText(sreachBean.getBdate());
        tvQm.setText(sreachBean.getQm());
        if ("Y".equals(sreachBean.getIshy())) {
            zt = one;
            btnCheck.setText("删除好友");
            btnCheck.setBackgroundResource(R.drawable.delet_frend_button_bg);
        } else {
            switch (sreachBean.getZt()) {
                case "3":
                    btnCheck.setText("等待验证");
                    btnCheck.setTextColor(Color.parseColor("#cccccc"));
                    btnCheck.setBackgroundResource(R.drawable.new_frend_button_bg2);
                    btnCheck.setEnabled(false);
                    break;
                default:
                    btnCheck.setText("添加好友");
                    btnCheck.setBackgroundResource(R.drawable.add_frend_button_bg);
                    break;
            }
        }
        if (sreachBean.getYhids() == MyApplication.getMyApplication().userInfo.getYhid()) {
            btnCheck.setVisibility(View.GONE);
        }
    }

    private void setFriend() {
        MyLog.showLog("ZZZ", "zt - " + zt + " type - " + type + " friendID - " + yhid);
        if (sreachBean != null) {
            if ("sreach".equals(type)) {
                if (sreachBean != null)
                    yhids = sreachBean.getYhids() + "";
            } else {
                if (friendBean != null)
                    yhids = friendBean.getYhids() + "";
            }
            yhids = sreachBean.getYhids() + "";

//        if (zero.equals(zt) || two.equals(zt) || "".equals(zt)) {
////            type = ADDFREND;
//
//        } else {
////            type = DELETFREND;
//
//        }

            if ("Y".equals(sreachBean.getIshy())) {
                deletFrriendDialog(yhids);
            } else {
                addFriendDialog(yhids);
            }
        }
    }

    @OnClick({R.id.btn_check, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_check:
                setFriend();
                break;
            case R.id.img_close:
                finish();
                if (type.equals(ADDFREND)) {
//                    gotoSomeActivity(this, AtyTag.ATY_NewFrend, true);
                } else {

                }
                break;
        }
    }

}
