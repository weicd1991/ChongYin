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
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonSignatureActivity extends BaseActivity {

    @BindView(R.id.edit_personqm)
    EditText editPersonqm;
    @BindView(R.id.tv_qmnum)
    TextView tvQmnum;
    private String qm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_person_signature;
    }

    @Override
    protected void initView() {
        qm = getIntent().getStringExtra("personDate");
        if (!"".equals(qm)) {
            editPersonqm.setText(qm);
            tvQmnum.setText(qm.length() + "/120");
        }
        editPersonqm.addTextChangedListener(textWatcher);
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
            editStart = editPersonqm.getSelectionStart();
            editEnd = editPersonqm.getSelectionEnd();
            tvQmnum.setText(temp.length() + "/120");
            if (temp.length() > 120) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                editPersonqm.setText(ToDBC(s.toString()));
                editPersonqm.setSelection(tempSelection);
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
                case ServiceCode.UP_DATE_QM_NUM:
//                    Toast.makeText(getApplicationContext(), "签名修改成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"签名修改成功！");
                    EventBus.getDefault().post(new DowloadEvent("change", "change"));
                    finish();
//                    gotoSomeActivity(this, AtyTag.ATY_PersonData, true);
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            switch (check) {
                case ServiceCode.UP_DATE_QM_NUM:
//                    Toast.makeText(getApplicationContext(), "签名修改失败！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"签名修改失败！");
                    break;
            }
        }

    }

    //修改签名
    private void setQM() {
        String qm = editPersonqm.getText().toString().trim();
        if (this.qm.equals(qm)) {
//            Toast.makeText(this, "签名相同", Toast.LENGTH_SHORT).show();
            ToastUtil.showShort(this,"签名相同!");
            finish();
        } else {
            if (qm != null && !"".equals(qm)) {
                map.put("isAND", "Y");
                map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
                map.put("qm", qm);
                NetWork.getNetVolue(ServiceCode.UP_DATE_QM, map, ServiceCode.NETWORK_GET, null);
            } else {
                finish();
//            gotoSomeActivity(this, AtyTag.ATY_PersonData, true);
            }
            editPersonqm.setText("");
        }
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        setQM();
    }
}
