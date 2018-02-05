package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.AddFoodPayRecyclerAdapter;
import com.jsmy.chongyin.adapter.AddFoodPayRecyclerAdapter2;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.ShiWuBean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.ActivityTack;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;
import com.jsmy.chongyin.view.RefreshRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddFoodPayActivity extends BaseActivity implements NetWork.CallListener {

    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.recycler_food)
    RefreshRecyclerView recyclerFood;
    private Handler handler;
    private AddFoodPayRecyclerAdapter2 adapter;
    private List<ShiWuBean.DataBean.ListBean> list;
    private View header;
    private RelativeLayout relaHeader;

    private int position;

    private boolean isBuy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        //布局重用
        return R.layout.activity_add_food_pay;
    }

    @Override
    protected void initView() {
        addType = getIntent().getStringExtra("addType");
        imgClose.setImageResource(R.mipmap.fanhui);
        list = new ArrayList<>();

    }

    @Override
    protected void initData() {
        getSWList();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_FOOD_SHOP_LIST_NUM:
                    list.addAll(gson.fromJson(result, ShiWuBean.class).getData().getList());
                    initRecycler();
                    break;
                case ServiceCode.UP_DATE_SP_SHOP_NUM:
                    list.get(position).setCns(list.get(position).getCns() + 1);
                    getData(true, position + 1);
//                    Toast.makeText(this, "食物购买成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(this, "食物购买成功!");
                    isBuy = true;
                    break;
                default:
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    //访问食物列表
    private void getSWList() {
        map.clear();
        map.put("spflid", addType);
        NetWork.getNetVolue(ServiceCode.GET_FOOD_SHOP_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    private void initRecycler() {
        handler = new Handler();
        adapter = new AddFoodPayRecyclerAdapter2(this);
        recyclerFood.setLayoutManager(new LinearLayoutManager(this));
        recyclerFood.setAdapter(adapter);
        recyclerFood.setItemAnimor(null);
        header = LayoutInflater.from(this).inflate(R.layout.recycler_headview, null);
        relaHeader = (RelativeLayout) header.findViewById(R.id.rela_header);
        ViewGroup.LayoutParams params = relaHeader.getLayoutParams();
        switch (list.size()) {
            case 1:
                params.height = UtilsTools.dip2px(this, 420);
                break;
            case 2:
                params.height = UtilsTools.dip2px(this, 350);
                break;
            case 3:
                params.height = UtilsTools.dip2px(this, 280);
                break;
            case 4:
                params.height = UtilsTools.dip2px(this, 210);
                break;
            default:
                params.height = UtilsTools.dip2px(this, 140);
                break;
        }
        header.setLayoutParams(params);
        getData(false, 0);
    }

    public void getData(final boolean b, final int item) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (b) {
                    adapter.notifyItemChanged(item);
                } else {
                    adapter.removeHeader();
                    adapter.removeFooter();
                    adapter.clear();
                    adapter.addAll(list);
                    adapter.setHeader(header);
                    adapter.setFooter(R.layout.recycler_footview_pay);
                }
            }
        }, 100);
    }

    //购买食物
    public void showDialogOne(String spid, String cns, String yuanbao, int position) {
        this.position = position;
        int need = Integer.parseInt(yuanbao);
        int have = MyApplication.getMyApplication().userInfo.getYbcns();
        if (need > have) {
            showDialog();
        } else {
            if (isBuy) {
                isBuy = false;
                buyFood(spid, cns, yuanbao);
            } else {
//                Toast.makeText(this,"您购买的速度已超过当前网速，购买失败",Toast.LENGTH_SHORT).show();
                ToastUtil.showShort(this, "您购买的速度已超过当前网速，购买失败!");
            }
        }
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
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
        if (!"".equals(addType)) {
//            gotoSomeActivity(this, AtyTag.ATY_AddFood, true);
        } else {

        }
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
    //访问基础数据
    private void getJCSJ() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SY_JCSJ, map, ServiceCode.NETWORK_GET, null);
    }
}
