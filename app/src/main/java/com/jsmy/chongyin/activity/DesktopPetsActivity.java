package com.jsmy.chongyin.activity;

import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.customclass.PickerScrollView;
import com.jsmy.chongyin.service.FloatWindowService;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.HuaweiUtils;
import com.jsmy.chongyin.utils.MeizuUtils;
import com.jsmy.chongyin.utils.MiuiUtils;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.QikuUtils;
import com.jsmy.chongyin.utils.RomUtils;
import com.jsmy.chongyin.utils.SharePrefUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DesktopPetsActivity extends BaseActivity {

    @BindView(R.id.picker)
    PickerScrollView picker;
    private List<String> list;
    private String msg = null;
    private String zmpet = "";

    private boolean open = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_desktop_pets;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add("50%");
        list.add("100%");
        list.add("关闭");
        list.add("150%");
        list.add("200%");
        picker.setData(list);
        String tag = SharePrefUtil.getStringPet(this, AtyTag.DeskPet, "0");
        if (tag != null && !"".equals(tag) && !"0".equals(tag)) {
            if (checkPermission(this)) {
                picker.setSelected(tag + "%");
            } else {
                picker.setSelected(2);
            }
        } else {
            picker.setSelected(2);
        }

        picker.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(String str) {
                msg = str;
                setDeskPetWidth();
                applyOrShowFloatWindow(DesktopPetsActivity.this);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.UP_DATE_PET_ZM_NUM:
                    MyApplication.getMyApplication().userInfo.setZmpet(zmpet);
                    finish();
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    private void setPetsize() {
        if (msg != null) {
            switch (msg) {
                case "50%":
                    zmpet = "50";
                    break;
                case "100%":
                    zmpet = "100";
                    break;
                case "150%":
                    zmpet = "150";
                    break;
                case "200%":
                    zmpet = "200";
                    break;
                default:
                    zmpet = "0";
                    break;
            }
            map.clear();
            map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
            map.put("petid", MyApplication.getMyApplication().userInfo.getPetid());
            map.put("zmpet", zmpet);
            NetWork.getNetVolue(ServiceCode.UP_DATE_PET_ZM, map, ServiceCode.NETWORK_GET, null);
        } else {
            finish();
        }
    }

    private void setDeskPetWidth() {
        if (msg != null) {
            switch (msg) {
                case "50%":
                    SharePrefUtil.saveStringPet(this, AtyTag.DeskPet, "50");
                    EventBus.getDefault().post(new DowloadEvent("50", "desktop"));
                    break;
                case "100%":
                    SharePrefUtil.saveStringPet(this, AtyTag.DeskPet, "100");
                    EventBus.getDefault().post(new DowloadEvent("100", "desktop"));
                    break;
                case "150%":
                    SharePrefUtil.saveStringPet(this, AtyTag.DeskPet, "150");
                    EventBus.getDefault().post(new DowloadEvent("150", "desktop"));
                    break;
                case "200%":
                    SharePrefUtil.saveStringPet(this, AtyTag.DeskPet, "200");
                    EventBus.getDefault().post(new DowloadEvent("200", "desktop"));
                    break;
                default:
                    SharePrefUtil.saveStringPet(this, AtyTag.DeskPet, "0");
                    EventBus.getDefault().post(new DowloadEvent("0", "desktop"));
                    break;
            }
        }
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        setPetsize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
        if (open) {
            setDeskPetWidth();
            applyOrShowFloatWindow(DesktopPetsActivity.this);
        }
    }

    public void applyOrShowFloatWindow(Context context) {
        MyLog.showLog("PPP", "-- " + msg + " ---");
        if (checkPermission(context)) {
            if (msg != null) {
                switch (msg) {
                    case "50%":
                        openDeskPet("Y");
                        break;
                    case "100%":
                        openDeskPet("Y");
                        break;
                    case "150%":
                        openDeskPet("Y");
                        break;
                    case "200%":
                        openDeskPet("Y");
                        break;
                    default:
                        Intent intent = new Intent(this, FloatWindowService.class);
                        stopService(intent);
                        break;
                }
            }
        } else {
            applyPermission(context);
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

    private void applyPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                miuiROMPermissionApply(context);
            } else if (RomUtils.checkIsMeizuRom()) {
                meizuROMPermissionApply(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                huaweiROMPermissionApply(context);
            } else if (RomUtils.checkIs360Rom()) {
                ROM360PermissionApply(context);
            }
        }
        commonROMPermissionApply(context);
    }

    private void ROM360PermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    QikuUtils.applyPermission(context);
                } else {
                    Log.e(AtyTag.ATY_DesktopPets, "ROM:360, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void huaweiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    HuaweiUtils.applyPermission(context);
                } else {
                    Log.e(AtyTag.ATY_DesktopPets, "ROM:huawei, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void meizuROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    MeizuUtils.applyPermission(context);
                } else {
                    Log.e(AtyTag.ATY_DesktopPets, "ROM:meizu, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void miuiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    MiuiUtils.applyMiuiPermission(context);
                } else {
                    Log.e(AtyTag.ATY_DesktopPets, "ROM:miui, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    /**
     * 通用 rom 权限申请
     */
    private void commonROMPermissionApply(final Context context) {
        //这里也一样，魅族系统需要单独适配
        if (RomUtils.checkIsMeizuRom()) {
            meizuROMPermissionApply(context);
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                showConfirmDialog(context, new OnConfirmResult() {
                    @Override
                    public void confirmResult(boolean confirm) {
                        if (confirm) {
                            try {
                                Class clazz = Settings.class;
                                Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");

                                Intent intent = new Intent(field.get(null).toString());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setData(Uri.parse("package:" + context.getPackageName()));
                                context.startActivity(intent);
                            } catch (Exception e) {
                                Log.e(AtyTag.ATY_DesktopPets, Log.getStackTraceString(e));
                            }
                        } else {
                            Log.d(AtyTag.ATY_DesktopPets, "user manually refuse OVERLAY_PERMISSION");
                            //需要做统计效果
                        }
                    }
                });
            }
        }
    }

    private void showConfirmDialog(Context context, OnConfirmResult result) {
        showConfirmDialog(context, "您的手机没有授予悬浮窗权限，请开启后再试", result);
    }

    private void showConfirmDialog(Context context, String message, final OnConfirmResult result) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        tvTitle.setText("开启桌面宠物需要悬浮窗权限\n确认授权！");
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setText("狠心拒绝");
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.confirmResult(false);
                dialog.dismiss();
            }
        });
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setText("马上开启");
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = true;
                result.confirmResult(true);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private interface OnConfirmResult {
        void confirmResult(boolean confirm);
    }

    @Override
    public void onBackPressed() {
        setPetsize();
    }

}
