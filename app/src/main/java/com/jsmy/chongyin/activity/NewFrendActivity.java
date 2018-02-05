package com.jsmy.chongyin.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.NewFrenRecyclerAdapter;
import com.jsmy.chongyin.adapter.NewFrenRecyclerAdapter2;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.NewFriendBena;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lemon.view.adapter.Action;

public class NewFrendActivity extends BaseActivity {

    @BindView(R.id.edit_id)
    EditText editId;
    @BindView(R.id.newfrend_recycler)
    RecyclerView newfrendRecycler;

    private Handler handler;
    private NewFrenRecyclerAdapter adapter;
    private List<NewFriendBena.DataBean.ListBean> list;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_new_frend;
    }

    @Override
    protected void initView() {
        context = this;
        editId.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
//        initRecycler();
    }

    private void initRecycler() {
        handler = new Handler();
        adapter = new NewFrenRecyclerAdapter(this,list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newfrendRecycler.setLayoutManager(layoutManager);
//        newfrendRecycler.setItemAnimor(null);
        newfrendRecycler.setAdapter(adapter);
//        newfrendRecycler.setRefreshAction(new Action() {
//            @Override
//            public void onAction() {
//                getNewFriendList();
//            }
//        });
//        newfrendRecycler.setLoadMoreAction(new Action() {
//            @Override
//            public void onAction() {
//                getNewFriendList();
//            }
//        });
//        newfrendRecycler.post(new Runnable() {
//            @Override
//            public void run() {
//                getNewFriendList();
//            }
//        });
    }

//    private void getDate() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                adapter.removeFooter();
//                adapter.clear();
//                adapter.addAll(list);
//                adapter.setFooter(R.layout.recycler_footview1);
//            }
//        }, 100);
//    }


    @Override
    protected void initData() {
        getNewFriendList();
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
                case ServiceCode.GET_NEW_FRIEND_LIST_NUM:
                    list = gson.fromJson(result, NewFriendBena.class).getData().getList();
                    initRecycler();
//                    getDate();
                    break;
                case ServiceCode.UP_DATE_NEW_FRIEND_SSTJ_NUM:
                    getNewFriendList();
                    getMyFriendList();
                    getApplyList();
                    break;
                case ServiceCode.UP_DATE_FRIEND_JJ_NUM:
                    getNewFriendList();
                    getApplyList();
                    break;
                case ServiceCode.DEL_THYSQJL_NUM:
                    getNewFriendList();
                    break;
                case ServiceCode.GET_WO_MATERIAL_LIST_NUM:
                    break;
                case ServiceCode.GET_NEW_FRIEND_SS_NUM:
                    gotoSomeActivity(this, AtyTag.ATY_AddNewFrend, true);
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            switch (code) {
                case "refresh":
                    getNewFriendList();
                    break;
                case "N":
                    if (ServiceCode.GET_NEW_FRIEND_SS_NUM.equals(DecodeData.getCodeRoMsg(result, DecodeData.CHECK)))
                    ToastUtil.showShort(getApplicationContext(),"无此用户!");
                    break;
                case "haoyou":
                    getNewFriendList();
                    break;
            }

        }
    }

    //访问好友申请列表
    private void getNewFriendList() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_NEW_FRIEND_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //同意好友申请
    public void agreeFrend(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        map.put("zt", "1");
        NetWork.getNetVolue(ServiceCode.UP_DATE_NEW_FRIEND_SSTJ, map, ServiceCode.NETWORK_GET, null);

    }

    //拒绝好友申请
    public void refuseFrend(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        map.put("zt", "1");
        NetWork.getNetVolue(ServiceCode.UP_DATE_FRIEND_JJ, map, ServiceCode.NETWORK_GET, null);
    }

    //清除记录
    private void clearFrend() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.DEL_THYSQJL, map, ServiceCode.NETWORK_GET, null);
    }

    //搜索好友
    private void sreachFrend() {
        String yhid = editId.getText().toString().trim();
        if (null != yhid && !"".equals(yhid)) {
            zt = zero;
            type = "sreach";
            friendID = yhid;
            sreachFriend(yhid);
        } else {

        }

    }

    //搜索好友资料
    private void sreachFriend(String yhid) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhid);
        NetWork.getNetVolue(ServiceCode.GET_NEW_FRIEND_SS, map, ServiceCode.NETWORK_GET, null);
    }

    //访问好友列表
    public void getMyFriendList() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_FRIEND_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    @OnClick({R.id.img_close, R.id.img_sreach, R.id.tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_sreach:
                sreachFrend();
                break;
            case R.id.tv_clear:
                clearFrend();
                break;
            case R.id.img_close:
                finish();
//                gotoSomeActivity(this,AtyTag.ATY_PersonCenter,true);
                break;
        }
    }

}
