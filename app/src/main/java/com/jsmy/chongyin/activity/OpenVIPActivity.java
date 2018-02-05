package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class OpenVIPActivity extends BaseActivity {

    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.tv_six)
    TextView tvSix;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_je)
    TextView tvJe;

    private String nr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_open_vip;
    }

    @Override
    protected void initView() {
        tvId.setText("ID:" + MyApplication.getMyApplication().userInfo.getYhid());
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

        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            if ("close".equals(code)) {
                finish();
            }
        }

    }

    @OnClick({R.id.tv_other, R.id.tv_six, R.id.tv_three, R.id.tv_one, R.id.btn_check, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_other:
                showInputDialog();
                break;
            case R.id.tv_six:
                choiceMoon(tvSix);
                tvJe.setText(6 * 20 + "");
                moon = 6 + "";
                price = 6 * 20 + "";
                break;
            case R.id.tv_three:
                choiceMoon(tvThree);
                tvJe.setText(3 * 20 + "");
                moon = 3 + "";
                price = 3 * 20 + "";
                break;
            case R.id.tv_one:
                choiceMoon(tvOne);
                tvJe.setText(1 * 20 + "");
                moon = 1 + "";
                price = 1 * 20 + "";
                break;
            case R.id.btn_check:
                payType = "huiyuan";
//                finish();
                gotoSomeActivity(this, AtyTag.ATY_PayVIP, true);
                break;
            case R.id.img_close:
                finish();
//                gotoSomeActivity(this, AtyTag.ATY_BeingVIP, true);
                break;
        }
    }

    private void choiceMoon(TextView btn) {
        tvOne.setTextColor(Color.parseColor("#333333"));
        tvThree.setTextColor(Color.parseColor("#333333"));
        tvSix.setTextColor(Color.parseColor("#333333"));
        tvOther.setTextColor(Color.parseColor("#333333"));
        tvOne.setBackgroundResource(R.drawable.openvip_unchoice_button_bg);
        tvThree.setBackgroundResource(R.drawable.openvip_unchoice_button_bg);
        tvSix.setBackgroundResource(R.drawable.openvip_unchoice_button_bg);
        tvOther.setBackgroundResource(R.drawable.openvip_unchoice_button_bg);
        btn.setTextColor(Color.parseColor("#ffffff"));
        btn.setBackgroundResource(R.drawable.openvip_choice_button_bg);
    }

    private void showInputDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_moon);
        dialog.setCancelable(false);
        final EditText editText = (EditText) dialog.findViewById(R.id.edit_moon);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        if (!"".equals(nr))
            editText.setText(nr);
        TextView btnCheck = (TextView) dialog.findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nr = editText.getText().toString().trim();
                if (null != nr && !"".equals(nr)) {
                    int i = Integer.parseInt(nr);
                    if (i > 120) {
//                        Toast.makeText(getApplicationContext(), "超出限制，请重新输入！", Toast.LENGTH_SHORT).show();
                        ToastUtil.showShort(getApplicationContext(),"超出限制，请重新输入！");
                    } else {
                        choiceMoon(tvOther);
                        tvOther.setText(" " + nr + " ");
                        tvJe.setText(i * 20 + "");
                        price = i * 20 + "";
                        moon = nr;
                    }

                } else {

                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
