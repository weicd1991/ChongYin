package com.jsmy.chongyin.viewholder;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.jsmy.chongyin.NetWork.Gitapi;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.PetShopActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.PetBean;
import com.jsmy.chongyin.bean.PetTPSiseBean;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.service.DownLoadService;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import cn.lemon.view.adapter.BaseViewHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/5/4.
 */

public class PetShopRecyclerViewHolder extends BaseViewHolder<PetBean.DataBean.ListBean> {
    private PetShopActivity context;

    private ImageView imgPet;
    private TextView tvLeft;
    private ImageView imgRight;
    private TextView tvNum;
    private TextView tvPetName;
    private TextView tvPetNc;
    private TextView tvPetSize;
    private ProgressBar pressPet;
    private Button btnCheck;
    private RelativeLayout relaRight;
    private RelativeLayout relaGroup;

    public PetShopRecyclerViewHolder(ViewGroup parent, PetShopActivity context) {
        super(parent, R.layout.activity_pet_shop_item);
        this.context = context;

    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        imgPet = (ImageView) findViewById(R.id.img_pet);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        imgRight = (ImageView) findViewById(R.id.img_right);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvPetName = (TextView) findViewById(R.id.tv_pet_name);
        tvPetNc = (TextView) findViewById(R.id.tv_pet_nc);
        tvPetSize = (TextView) findViewById(R.id.tv_pet_size);
        pressPet = (ProgressBar) findViewById(R.id.press_pet);
        btnCheck = (Button) findViewById(R.id.btn_check);
        relaRight = (RelativeLayout) findViewById(R.id.rela_right);
        relaGroup = (RelativeLayout) findViewById(R.id.rela_group);
    }

    @Override
    public void setData(final PetBean.DataBean.ListBean data) {
        super.setData(data);

        if (1 == getLayoutPosition()) {
            relaGroup.setBackgroundResource(R.drawable.base_rectangle3);
        } else {
            relaGroup.setBackgroundResource(R.drawable.base_rectangle3_2);
        }
        switch (data.getBq()) {
            case "1":
                tvLeft.setBackgroundResource(R.mipmap.biaoqian_new);
                break;
            case "2":
                tvLeft.setBackgroundResource(R.mipmap.biaoqian_hot);
                break;
            case "3":
                break;
        }
        NetWork.setImgGlide(context, data.getTpurl(), imgPet);
        switch (data.getHqlx()) {
            case "1":
                imgRight.setVisibility(View.GONE);
                tvNum.setText("免费");
                relaRight.setBackgroundResource(R.mipmap.chongwushangdian_biaoqian1);
                break;
            case "2":
                imgRight.setVisibility(View.GONE);
                tvNum.setText("VIP");
                relaRight.setBackgroundResource(R.mipmap.chongwushangdian_biaoqian1);
                break;
            case "3":
                imgRight.setVisibility(View.VISIBLE);
                tvNum.setText(data.getJb() + "");
                relaRight.setBackgroundResource(R.mipmap.chongwushangdian_biaoqian2);
                break;
        }
        tvPetName.setText(data.getMc());
        tvPetNc.setText(data.getMs());


//        if ("Y".equals(data.getIsyy())) {
        if ("Y".equals(data.getZt())) {
            btnCheck.setBackgroundResource(R.drawable.petshopbutton_bg2);
            btnCheck.setText("已设置");
            btnCheck.setTextColor(Color.parseColor("#d5d5d5"));
            pressPet.setProgress(data.getPress());
        } else {
            if (0 < data.getPress() && data.getPress() < 25) {
                btnCheck.setBackgroundResource(R.drawable.petshopbutton_bg1);
                btnCheck.setText("暂停");
                btnCheck.setTextColor(Color.parseColor("#000000"));
                pressPet.setProgress(data.getPress());
                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (context.isDowload) {
                            context.cancelDowload(getLayoutPosition());
                            btnCheck.setText("继续");
                        } else {
                            context.num--;
                            context.tpDowload(context.num);
                            btnCheck.setText("暂停");
                        }
                    }
                });
            } else if (data.getPress() >= 25) {
                btnCheck.setBackgroundResource(R.drawable.petshopbutton_bg3);
                btnCheck.setText("设置");
                btnCheck.setTextColor(Color.parseColor("#ffffff"));
                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.showDialogGH2("确定更换该宠物吗？", data.getId(), getLayoutPosition());
                    }
                });
            } else {
                if ("Y".equals(data.getIsxz())) {
                    btnCheck.setBackgroundResource(R.drawable.petshopbutton_bg3);
                    btnCheck.setText("设置");
                    btnCheck.setTextColor(Color.parseColor("#ffffff"));
                    btnCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (data.getHqlx()) {
                                case "1":
                                    context.showDialogGH2("确定更换该宠物吗？", data.getId(), getLayoutPosition());
                                    break;
                                case "2":
                                    if (!"0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) && !"Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                                        context.showDialogGH2("确定更换该宠物吗？", data.getId(), getLayoutPosition());
                                    } else {
                                        context.showDialogVIP("开通VIP即可获取该宠物");
                                    }
                                    break;
                                case "3":
                                    context.showDialogGH2("确定更换该宠物吗？", data.getId(), getLayoutPosition());
                                    break;
                            }
                        }
                    });
                } else {
                    if ("Y".equals(data.getIsyy())) {
                        btnCheck.setBackgroundResource(R.drawable.petshopbutton_bg1);
                        btnCheck.setText("获取");
                        btnCheck.setTextColor(Color.parseColor("#5dc080"));
                        btnCheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (context.isEnable) {
                                    switch (data.getHqlx()) {
                                        case "1":
                                            context.showDialogGH2("确定更换该宠物吗？", data.getId(), getLayoutPosition());
//                                context.buyPet(data.getId(), data.getHqlx(), data.getJb(), getLayoutPosition());
                                            break;
                                        case "2":
                                            if (!"0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) && !"Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                                                context.showDialogGH2("确定更换该宠物吗？", data.getId(), getLayoutPosition());
                                            } else {
                                                context.showDialogVIP("开通VIP即可获取该宠物");
                                            }
                                            break;
                                        case "3":
                                            context.showDialogGH2("确定更换该宠物吗？", data.getId(), getLayoutPosition());
                                            break;
                                    }
                                }
                            }
                        });
                    } else {
                        btnCheck.setBackgroundResource(R.drawable.petshopbutton_bg1);
                        btnCheck.setText("获取");
                        btnCheck.setTextColor(Color.parseColor("#5dc080"));
                        btnCheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (context.isEnable) {
                                    switch (data.getHqlx()) {
                                        case "1":
                                            context.showDialogGH("确定更换该宠物吗？", data.getId(), data.getHqlx(), data.getJb() + "", getLayoutPosition());
                                            break;
                                        case "2":
                                            if (!"0".equals(MyApplication.getMyApplication().userInfo.getVipdj()) && !"Y".equals(MyApplication.getMyApplication().userInfo.getIsgq())) {
                                                context.showDialogGH("确定更换该宠物吗？", data.getId(), data.getHqlx(), data.getJb() + "", getLayoutPosition());
                                            } else {
                                                context.showDialogVIP("开通VIP即可获取该宠物");
                                            }
                                            break;
                                        case "3":
                                            if (MyApplication.getMyApplication().userInfo.getYbcns() < Integer.parseInt(data.getJb() + "")) {
//                                                Toast.makeText(context, "元宝不足！", Toast.LENGTH_SHORT).show();
//                                                ToastUtil.showShort(context,"元宝不足！");
                                                context.showDialog();
                                            } else {
                                                context.showDialogChangePetNc(data.getJb() + "", data.getId(), getLayoutPosition(), data.getHqlx());
                                            }
                                            break;
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
//        double d = data.getZyjsize1() + data.getZyjsize2() + data.getZyjsize3() + data.getZyjsize4() +
//                data.getCsize1() + data.getCsize2() + data.getCsize3() + data.getCsize4() + data.getCsize5() + data.getCsize6() + data.getCsize7() +
//                data.getDzhsize1() + data.getDzhsize2() + data.getDzhsize3() + data.getDzhsize4() + data.getDzhsize5() +
//                data.getKusize1() + data.getKusize2() + data.getKusize3() + data.getKusize4() + data.getKusize5() +
//                data.getXsize1() + data.getXsize2() + data.getXsize3() + data.getXsize4();
//        DecimalFormat df = new DecimalFormat("#.0");
//        tvPetSize.setText(df.format(d) + "M");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceCode.SERVICE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Gitapi gitapi = retrofit.create(Gitapi.class);
        Map<String, String> map = new HashMap<>();
        map.put("petid", data.getId() + "");
        map.put("petdj", "1");
        Call<String> call = gitapi.getNetWork(ServiceCode.SERVICE_URL + ServiceCode.GET_PET_TP, map);
        try {
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Gson gson = new Gson();
                    PetTPSiseBean.DataBean dataBean = gson.fromJson(response.body(), PetTPSiseBean.class).getData();
                    tvPetSize.setText(dataBean.getVzsize() + "M");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemViewClick(PetBean.DataBean.ListBean data) {
        super.onItemViewClick(data);
    }

}
