package com.jsmy.chongyin.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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

public class PersonChangeNameActivity extends BaseActivity {

    @BindView(R.id.edit_personnc)
    EditText editPersonnc;
    @BindView(R.id.tv_ncnum)
    TextView tvNcnum;

    private String nc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_person_change_name;
    }

    @Override
    protected void initView() {
        MyLog.showLog("AAA", "personDate   " + getIntent().getStringExtra("personDate"));
        nc = getIntent().getStringExtra("personDate");
        if (!"".equals(nc)) {
            editPersonnc.setText(nc);
            tvNcnum.setText(nc.length() + "/12");
        }
        editPersonnc.addTextChangedListener(textWatcher);
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
            editStart = editPersonnc.getSelectionStart();
            editEnd = editPersonnc.getSelectionEnd();
            tvNcnum.setText(temp.length() + "/12");
            if (temp.length() > 12) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                editPersonnc.setText(s);
                editPersonnc.setSelection(tempSelection);
            }
        }
    };

    @Override
    protected void initData() {
        isFrendData = "N";
    }
    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }
    @Override
    protected void paresData(String result, String code) {
        String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
        if ("Y".equals(code)) {
            switch (check) {
                case ServiceCode.UP_DATE_NC_NUM:
//                    Toast.makeText(getApplicationContext(), "昵称修改成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"昵称修改成功！");
                    EventBus.getDefault().post(new DowloadEvent("change", "change"));
                    finish();
//                    gotoSomeActivity(this, AtyTag.ATY_PersonData, true);
                    break;
            }
        }  else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }else {
            switch (check) {
                case ServiceCode.UP_DATE_NC_NUM:
//                    Toast.makeText(getApplicationContext(), "昵称修改失败！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"昵称修改失败！");
                    break;
            }

        }

    }

    //修改昵称
    private void setNC() {
        String nc = editPersonnc.getText().toString().trim();
        if (this.nc.equals(nc)) {
//            Toast.makeText(this, "昵称相同", Toast.LENGTH_SHORT).show();
            ToastUtil.showShort(this,"昵称相同!");
            finish();
        } else {
            if (nc != null && !"".equals(nc)) {
                map.put("isAND", "Y");
                map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
                map.put("nc", nc);
                NetWork.getNetVolue(ServiceCode.UP_DATE_NC, map, ServiceCode.NETWORK_GET, null);
            } else {
                finish();
//            gotoSomeActivity(this, AtyTag.ATY_PersonData, true);
            }
            editPersonnc.setText("");
        }
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        setNC();
    }

}
