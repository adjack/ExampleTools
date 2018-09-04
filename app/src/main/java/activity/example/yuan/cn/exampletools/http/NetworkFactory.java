package activity.example.yuan.cn.exampletools.http;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jack on 2018/4/11.
 */

public class NetworkFactory {
    private static final int DEFAULT_TIMEOUT = 5;
    private NetworkService networkService;
    private static NetworkFactory instance = null;

    private Handler myHandler = new Handler(Looper.getMainLooper());

    private NetworkFactory() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        try {
            SSLSocketUtils.ignoreClientCertificate(builder);
            // SSLSocketUtils.addClientCertificates(LocalCertificates.getCertificatesData(), builder);
        } catch (Exception e) {
        }
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
//                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .baseUrl("域名")
                .build();

        networkService = retrofit.create(NetworkService.class);
    }


    //获取单例
    public static NetworkFactory getInstance() {
        if(instance == null){
            instance = new NetworkFactory();
        }
        return instance;
    }

    //在访问NetworkFactory时创建单例
    private static class SingletonHolder {
        private static final NetworkFactory INSTANCE = new NetworkFactory();
    }

    /**
     * 生成RequestBody
     * @param object
     * @return
     */
    private RequestBody getRequestBody(Object object) {
        String body = object.toString();
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
    }

    /**
     * 获取Url部分
     * @param url
     * @return
     */
    private String getPathName(String url){
//        LogUtil.d("testRequest",url);
        String urls = url.substring(url.indexOf("://")+3);
        urls = urls.substring(urls.indexOf("/")+1);
        return urls;
    }


    /**
     * 处理接口返回的数据
     */
    class MycallBack implements Callback<ResponseBody> {
        public ResponseCallBack<JSONObject> responseCallBack;

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if(response == null || response.body() == null){
                if(responseCallBack != null){
                    responseCallBack.onFailure(new Exception("error"),"error");
                }
                return;
            }
            try {
                String rawData = new String(response.body().bytes());
                JSONObject dataObject = new JSONObject(rawData);

                if(responseCallBack != null){
                    responseCallBack.onSuccess(isDataOk(dataObject),getTotalNum(dataObject),dataObject,rawData);
                }
            }
            catch (Exception ex){
                if(responseCallBack != null){
                    responseCallBack.onFailure(ex,response.body().toString());
                }
            }
        }

        public MycallBack(ResponseCallBack<JSONObject> responseCallBack){
            this.responseCallBack = responseCallBack;
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            if(responseCallBack != null){
                responseCallBack.onFailure(t,"");
            }
        }

    }

    /**
     * 检查数据返回状态
     * @param dataObject
     * @return
     */
    private boolean isDataOk(JSONObject dataObject){
        try {
            String result = dataObject.getString("Result");
            return result.equals("true");
        }
        catch (Exception ex){}
        return false;
    }

    /**
     * 请求条目总数
     * @param dataObject
     * @return
     */
    private int getTotalNum(JSONObject dataObject){
        try {
            return Integer.valueOf(dataObject.getJSONObject("Data").optString("total"));
        }
        catch (Exception ex){}
        return 0;
    }

    //业务接口分割线=========================================================================================================
    public synchronized void post(final String url, final String param, final ResponseCallBack<JSONObject> callBack){
//        if(!checkNetwork()){//判断网络
//            return;
//        }
        runBusinessInterface(url,param,callBack);
    }

    //执行具体业务接口
    private void runBusinessInterface(final String url, final String param, final ResponseCallBack<JSONObject> callBack){
        Call<ResponseBody> call =  networkService.httpPost(getRequestBody(param));
        call.enqueue(new MycallBack(callBack));
    }
}
