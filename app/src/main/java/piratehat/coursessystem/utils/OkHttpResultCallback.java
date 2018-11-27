package piratehat.coursessystem.utils;


import okhttp3.Call;

public interface OkHttpResultCallback {

    void onError(Call call, Exception e);

    void onResponse(String msg);
}
