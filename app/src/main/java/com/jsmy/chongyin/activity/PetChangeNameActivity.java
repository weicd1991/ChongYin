package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class PetChangeNameActivity extends BaseActivity {

    @BindView(R.id.edit_cwnc)
    EditText editCwnc;
    @BindView(R.id.tv_ncnum)
    TextView tvNcnum;
    @BindView(R.id.tv_gold_num)
    TextView tvGoldNum;

    private String petNc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_pet_change_name;
    }

    @Override
    protected void initView() {
        MyLog.showLog("AAA", "personDate   " + getIntent().getStringExtra("personDate"));
        petNc = getIntent().getStringExtra("personDate");
        if (!"".equals(petNc)) {
            editCwnc.setText(petNc);
            tvNcnum.setText(petNc.length() + "/8");
        }
        editCwnc.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = editCwnc.getSelectionStart();
            editEnd = editCwnc.getSelectionEnd();
            tvNcnum.setText(temp.length() + "/8");
            if (temp.length() > 8) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                editCwnc.setText(s);
                editCwnc.setSelection(tempSelection);
            }
        }
    };

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    private void setPetNc() {
        String nc = editCwnc.getText().toString().trim();
        if (petNc.equals(nc)) {
//            Toast.makeText(this, "昵称相同", Toast.LENGTH_SHORT).show();
            ToastUtil.showShort(this,"昵称相同!");
            finish();
        } else {
            if (null != nc && !"".equals(nc)) {
                petnc = nc;
                EventBus.getDefault().post(new DowloadEvent(petnc, "petnc"));
                finish();
            } else {

            }
        }
    }

    @Override
    protected void paresData(String result, String code) {
        if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }


    @OnClick({R.id.btn_check, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_check:
                setPetNc();
                break;
            case R.id.img_close:
                finish();
                break;
        }
    }


}
