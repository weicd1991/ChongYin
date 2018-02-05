package com.jsmy.chongyin.NetWork;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 定义请求接口
 * Created by Administrator on 2017/3/28.
 */

public interface Gitapi {
    //Get请求
    @GET
    Call<String> getNetWork(
            @Url String url,
            @QueryMap Map<String, String> options);

    //post请求
    @FormUrlEncoded
    @POST
    Call<String> postNetWork(
            @Url String url,
            @FieldMap Map<String, String> fields);


    // 上传单个文件
    @POST
    Call<String> uploadFile(
            @Url String url,
            @Body RequestBody Body);


    // 上传多个文件
    @Multipart
    @POST
    Call<String> uploadMultipleFiles(
            @Part("description") RequestBody description, @Part MultipartBody.Part file1, @Part MultipartBody.Part file2);

    /**
     * 文件下载
     *
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadFiles(
            @Url String fileUrl);



}
