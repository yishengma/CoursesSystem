package piratehat.coursessystem.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import piratehat.coursessystem.constant.Constant;

/**
 * Created by PirateHat on 2018/10/27.
 */

public class OkHttpUtil {
    private OkHttpClient mOkHttpClient;
    private static OkHttpUtil sOkHttpUtil;
    private Handler mHandler;

    private OkHttpUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cookieJar(new OkHttpCookieJar())
                .readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
        mHandler = new Handler(Looper.getMainLooper());

    }

    public static OkHttpUtil getInstance() {
        if (sOkHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                if (sOkHttpUtil == null) {
                    sOkHttpUtil = new OkHttpUtil();
                }
            }
        }
        return sOkHttpUtil;
    }

    /**
     * 异步的Get请求
     */
    public void getAsync(String url, OkHttpResultCallback okHttpResultCallback) {
        Request request = new Request.Builder().url(url).build();
        deliveryResult(okHttpResultCallback, request);
    }

    /**
     * 异步带请求头的get请求
     *
     * @param url
     * @param okHttpResultCallback
     * @param headers
     */
    public void getAsync(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> headers) {
        Request request = buildGetRequest(url, headers);
        deliveryResult(okHttpResultCallback, request);
    }

    /**
     * 异步的post请求
     */
    public void postAsync(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params, List<File> files) {
        Request request = buildPostRequest(url, params, files);
        deliveryResult(okHttpResultCallback, request);
    }

    /**
     * 异步的post请求,无文件传输
     */
    public void postAsync(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params) {
        Request request = buildPostRequest(url, params);
        deliveryResult(okHttpResultCallback, request);
    }

    /**
     * 异步的post请求,无文件传输
     */
    public void postAsyncFormData(String url, OkHttpResultCallback okHttpResultCallback, Map<String, String> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String key : params.keySet()) {
            builder.addFormDataPart(key, params.get(key));
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        deliveryResult(okHttpResultCallback, request);
    }

    /**
     * 构造get请求
     *
     * @param url
     * @param headers
     * @return
     */
    private Request buildGetRequest(String url, Map<String, String> headers) {

        Headers.Builder headersBuilder = new Headers.Builder();
        if (headers != null) {
            Set<Map.Entry<String, String>> paramsEntries = headers.entrySet();
            for (Map.Entry<String, String> entry : paramsEntries) {
                headersBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        return new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .build();
    }

    /**
     * 构建post请求参数
     */
    private Request buildPostRequest(String url, Map<String, String> params, List<File> files) {

        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        if (params != null) {
            Set<Map.Entry<String, String>> paramsEntries = params.entrySet();
            for (Map.Entry<String, String> entry : paramsEntries) {
                multipartBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if (files != null) {
            for (File file : files) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                multipartBodyBuilder.addFormDataPart("file", file.getName(), requestBody);
            }
        }
        MultipartBody multipartBody = multipartBodyBuilder.setType(MultipartBody.FORM).build();

        return new Request.Builder()
                .url(url)

                .post(multipartBody)
                .build();
    }

    /**
     * 构建post请求参数, 无文件传输
     */
    private Request buildPostRequest(String url, Map<String, String> params) {

        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        if (params != null) {
            Set<Map.Entry<String, String>> paramsEntries = params.entrySet();
            for (Map.Entry<String, String> entry : paramsEntries) {
                multipartBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (params != null) {
            Set<Map.Entry<String, String>> paramsEntries = params.entrySet();
            for (Map.Entry<String, String> entry : paramsEntries) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = formBodyBuilder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }


    /**
     * 调用call.enqueue，将call加入调度队列，执行完成后在callback中得到结果
     */
    private void deliveryResult(final OkHttpResultCallback okHttpResultCallback, final Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {
                sendFailedCallback(call, e, okHttpResultCallback);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    switch (response.code()) {
                        case 200:
                            sendSuccessCallback(response.body().bytes(), okHttpResultCallback);
                            break;
                        case 302:
                            sendSuccessCallback(response.body().bytes(), okHttpResultCallback);
                            break;
                        default:
                            sendSuccessCallback(response.body().bytes(), okHttpResultCallback);
                            throw new IOException();
                    }
                } catch (IOException e) {
                    sendFailedCallback(call, e, okHttpResultCallback);
                }
            }
        });
    }


    /**
     * 调用请求失败对应的回调方法，利用handler.post使得回调方法在UI线程中执行
     */
    private void sendFailedCallback(final Call call, final Exception e, final OkHttpResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(call, e);
                }
            }
        });
    }

    /**
     * 调用请求成功对应的回调方法，利用handler.post使得回调方法在UI线程中执行
     */
    private void sendSuccessCallback(final byte[] bytes, final OkHttpResultCallback okHttpResultCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (okHttpResultCallback != null) {
                    try {
                        okHttpResultCallback.onResponse(new String(bytes, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 取消所有请求，防止进入新界面时，旧界面请求仍在进行，造成阻塞
     */
    public void cancelAllRequest() {
        mOkHttpClient.dispatcher().cancelAll();
    }
}
