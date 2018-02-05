package com.jsmy.chongyin.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.listener.IPermission;
import com.jsmy.chongyin.service.LocationService;
import com.jsmy.chongyin.service.UpFileService;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.GetPathFromUri4kitkat;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.OnClick;

public class ChoiceImageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_choice_image;
    }

    @Override
    protected void initView() {
        isBackGroud = getIntent().getStringExtra("isBackGroud");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getBuildVERSION();
        playMusic();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.UP_DATE_TX_NUM:
//                    Toast.makeText(this, "头像修改成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(this,"头像修改成功！");
                    EventBus.getDefault().post(new DowloadEvent("change", "change"));
                    if (tempFile.exists()) {
                        tempFile.delete();
                    }

                    break;
//                case ServiceCode.UP_DATE_BJTUSC_NUM:
//                    Toast.makeText(this, "背景修改成功！", Toast.LENGTH_SHORT).show();
//                    if (tempFile.exists()) {
//                        tempFile.delete();
//                    }
//                    loginData();
//                    getBJTP();
//                    break;
            }
        }  else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }else {

            if ("crop".equals(code)) {
                MyLog.showLog("CCC", "-- " + code + " -- " + result);
                if (tempFile != null && tempFile.exists()) {
                    tempFile.delete();
                }
                if (result.equals("bg")) {
                    upFile(ServiceCode.BASE_PATH + ServiceCode.BASE_IMG_CROP);
                    EventBus.getDefault().post(new DowloadEvent("upbackgruop", "upbackgruop"));
                } else if (result.equals("tx")) {
                    upFile(ServiceCode.BASE_PATH + ServiceCode.BASE_IMG_TX);
                    EventBus.getDefault().post(new DowloadEvent("tx", "tx"));
                }
                finish();
            }
        }
    }

    //上传头像
    private void upFile(String tx) {
        Intent intent = new Intent(this, UpFileService.class);
        intent.putExtra("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        intent.putExtra("isBackGroud", isBackGroud);
        intent.putExtra("tx", tx);
        startService(intent);
    }

    //访问登录接口
    private void loginData() {
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("openid", SharePrefUtil.getString(this, "openid", ""));
        NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
    }

    //访问背景图
    private void getBJTP() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_BJTP_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    @OnClick({R.id.tv_phone, R.id.tv_grall, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                takePictureForCamera();
                break;
            case R.id.tv_grall:
                takePictureForGallery();
                break;
            case R.id.img_close:
                finish();

                break;
        }
    }

    private Bitmap bitmap;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private static final String PHOTO_FILE_NAME2 = "temp_photo2.jpg";
    private File tempFile;
    private Uri uritempFile;
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private void takePictureForGallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private void takePictureForCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), PHOTO_FILE_NAME)));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(this,new File(Environment.getExternalStorageDirectory().getAbsolutePath(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    private static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.jsmy.chongyin.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    //跳转自定义裁剪
    private void toCropImg(Uri uri) {
        String path = GetPathFromUri4kitkat.getPath(this, uri);
        if ("Y".equals(isBackGroud)) {
            drawable = path;
            cropWidth = 225;
            cropHeight = 400;
        } else {
            drawable = path;
            cropWidth = 250;
            cropHeight = 250;
        }
        gotoSomeActivity(this, AtyTag.ATY_Crop, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                toCropImg(uri);
//                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            tempFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), PHOTO_FILE_NAME);
            toCropImg(Uri.fromFile(tempFile));
//            crop(Uri.fromFile(tempFile));
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                upFile(GetPathFromUri4kitkat.getPath(this, uritempFile));
                if (tempFile.exists()) {
                    tempFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 剪切图片
     *
     * @param uri
     * @function:
     * @author:Jerry
     * @date:2013-12-30
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        if ("Y".equals(isBackGroud)) {
            // 裁剪框的比例，1：1
            intent.putExtra("aspectX", 9);
            intent.putExtra("aspectY", 16);
            intent.putExtra("outputX", 1080);
            intent.putExtra("outputY", 1920);
        } else {
            // 裁剪框的比例，1：1
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 250);
        }
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.putExtra("return-data", true);
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public static File myCaptureFile = null;

    /**
     * 将Bitmap转换成文件
     * 保存文件
     *
     * @param bm
     * @throws IOException
     */
    public static File saveFile(Bitmap bm) throws IOException {
        myCaptureFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), PHOTO_FILE_NAME2);
        if (myCaptureFile.exists()) {
            myCaptureFile.delete();
        }
        myCaptureFile.createNewFile();
        FileOutputStream bos = new FileOutputStream(myCaptureFile);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    //检查权限
    public void getBuildVERSION() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                getPermission(ChoiceImageActivity.this);
            } else {

            }
        } else {

        }
    }

    //申请权限
    private void getPermission(final Activity activity) {
        requestRunTimePermission(activity, new String[]{Manifest.permission.CAMERA}, new IPermission() {

            //用户同意时的回调
            @Override
            public void onGranted() {

            }

            //用户拒绝时的回调，并打印出具体拒绝了什么权限
            @Override
            public void onDenied(List<String> deniedPermissions) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    for (String deniedPermission : deniedPermissions) {
                        boolean b = shouldShowRequestPermissionRationale(deniedPermission);
                        if (!b) {
//                            Toast.makeText(ChoiceImageActivity.this, "权限申请被取消！", Toast.LENGTH_SHORT).show();
                            ToastUtil.showShort(ChoiceImageActivity.this,"权限申请被取消！");
                        }
                    }
                }
            }
        });
    }

}
