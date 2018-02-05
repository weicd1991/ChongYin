package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.tv_icon)
    TextView tvIcon;
    private ClipboardManager cm;
    private String tencent = "3293773372";
    private String mailbox = "Develop@urmer.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void initData() {
        tvIcon.setText("宠印 " + getVersionName(this));
    }

    private String getVersionName(Context context) {
        String versionCode = "";
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo("com.jsmy.chongyin", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    protected void paresData(String result, String code) {
        if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    @OnClick({R.id.img_close, R.id.tv_tiaokuan,R.id.tv_connet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.tv_tiaokuan:
                gotoSomeActivity(this, AtyTag.ATY_UseTerms, false);
                break;
            case R.id.tv_connet:
                showDialogCopy();
                break;
        }
    }

    private void showDialogCopy() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_service);
        dialog.setCancelable(false);
        TextView copyTencent = (TextView) dialog.findViewById(R.id.copy_tencent);
        copyTencent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("QQ", tencent);
                cm.setPrimaryClip(clipData);
                ToastUtil.showShort(AboutUsActivity.this, "复制成功！");
            }
        });
        TextView copyMailbox = (TextView) dialog.findViewById(R.id.copy_mailbox);
        copyMailbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("邮箱", mailbox);
                cm.setPrimaryClip(clipData);
                ToastUtil.showShort(AboutUsActivity.this, "复制成功！");
            }
        });
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
