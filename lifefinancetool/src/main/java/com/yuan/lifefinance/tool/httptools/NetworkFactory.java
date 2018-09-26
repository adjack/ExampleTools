package com.yuan.lifefinance.tool.httptools;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
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
    private String baseDefaultUrl = "http://money.finance.sina.com.cn/";
    private static final int DEFAULT_TIMEOUT = 12;
//    private NetworkService networkService;
    private static NetworkFactory instance = null;
    private  OkHttpClient.Builder builder;
    private NetworkFactory() {
        initOkHttpClientBuilder();
        initRetrofitBuilder();
    }

    public static NetworkFactory getInstance() {
        if(instance == null){
            synchronized (NetworkFactory.class){
                if(instance == null){
                    instance = new NetworkFactory();
                }
            }
        }
        return instance;
    }

    private void initOkHttpClientBuilder(){
        if(builder == null){
            builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//            if(WyjrConfiguration.openLog){
            builder.addInterceptor(HttpLogger.getHttpInterceptor());
            builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//            }
//            try {
//                SSLSocketUtils.ignoreClientCertificate(builder);
//            }
//            catch (Exception ex){}
        }
    }

    Retrofit.Builder retrofitBuilder;
    public void initRetrofitBuilder(){
        if(retrofitBuilder == null){
            retrofitBuilder = new Retrofit.Builder()
                    .client(builder.build());
            //.addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析;
        }
        retrofitBuilder.baseUrl(baseDefaultUrl);
    }

    private NetworkService getNetworkService(String url){
        if(retrofitBuilder == null){
            initRetrofitBuilder();
        }
        retrofitBuilder.baseUrl(url);
        return retrofitBuilder.build().create(NetworkService.class);
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
     * 设置head参数
     * @param url
     * @return
     */
    private String getHeadParam(String url){
//        return ToolUtits.buildHeader(url);
        return "head***";
    }

    /**
     * 获取Url部分
     * @param url
     * @return
     */
    private String getPathName(String url){
//        String urls = url.substring(url.indexOf("://")+3);
//        urls = urls.substring(urls.indexOf("/")+1);
//        Log.d("MycallBack",urls);
        return url;
    }

    private String urlHeadDeal(String url){
//        if(TextUtils.isEmpty(url)){
//            return "";
//        }
//        String urls = url.substring(url.indexOf("://")+3);
//        urls = urls.substring(urls.indexOf("/")+1);
//        return url.substring(0,url.indexOf(urls));
        return url;
    }

    private String getUrlHead(String url){
        return urlHeadDeal(url);
    }

    /**
     * 处理接口返回的数据
     */
    class MycallBack implements Callback<ResponseBody>{
        public ResponseCallBack<JSONObject> responseCallBack;
        private String methodName;
        private String param;
        private Object requestOb;
        public MycallBack(Object requestOb,ResponseCallBack<JSONObject> responseCallBack,String methodName,String param){
            this.responseCallBack = responseCallBack;
            this.methodName = methodName;
            this.param = param;
            this.requestOb = requestOb;
        }
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if(response == null || response.body() == null){
                if(responseCallBack != null){
                    responseCallBack.onFailure(requestOb,new Exception("error"),"error");
                }
                return;
            }
            try {

                String rawData = new String(response.body().bytes());
                JSONObject dataObject = new JSONObject();
                if(responseCallBack != null){
                    responseCallBack.onSuccess(requestOb,isDataOk(dataObject),getTotalNum(dataObject),dataObject,rawData);
                }
            }
            catch (Exception ex){
                if(responseCallBack != null){
                    responseCallBack.onFailure(requestOb,ex,response.body().toString());
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("MycallBack","error:"+t.toString()+"\n");
            if(responseCallBack != null){
                responseCallBack.onFailure(requestOb,t,"");
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

    private boolean checkNetwork(){
        return true;
    }

    //post执行具体业务接口
    public void runBusinessInterface(Object requestOb,final String url, String methodName,final String params, final ResponseCallBack<JSONObject> callBack){
        Call<ResponseBody> call =  getNetworkService(getUrlHead(url)).httpPost(getPathName(methodName),getHeadParam(url),getRequestBody(params));
        call.enqueue(new MycallBack(requestOb,callBack,getPathName(methodName),params));
    }

    //get请求具体接口
    //http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz002095&scale=60&ma=no&datalen=1023
    public void runBusinessInterface(Object requestOb,String url,final ResponseCallBack<JSONObject> callBack){
        url = url.replace("?","###");
        Log.d("runBusinessInterface",url+"");
        String[] urls = url.split("###");
        Log.d("runBusinessInterface",urls[0]+"");
        Map<String,Object> map = new HashMap<>();
        String[] values = urls[1].split("&");
        for(int i = 0; i<values.length;i++){
            Log.d("runBusinessInterface",values[i]);
            String[] value_temp = values[i].split("=");
            Log.d("runBusinessInterface",value_temp.length+"");
            map.put(value_temp[0],value_temp[1]);
        }
        Call<ResponseBody> call =  getNetworkService(baseDefaultUrl)
                .httpGet(urls[0],map);
        call.enqueue(new MycallBack(requestOb,callBack,getPathName(urls[0]),null));
    }

    //业务接口分割线=========================================================================================================
    public synchronized void post(Object requestOb,final String methodName, final String param, final ResponseCallBack<JSONObject> callBack){
        if(!checkNetwork()){
            if(callBack != null){
                callBack.onFailure(requestOb,new Exception(),"no net");
            }
            return;
        }
        Log.d("MycallBack","下发参数："+param);
        runBusinessInterface(requestOb,baseDefaultUrl,methodName,param,callBack);
    }

    public synchronized void get(final String url, final ResponseCallBack<JSONObject> callBack){
        if(!checkNetwork()){
            if(callBack != null){
                callBack.onFailure(null,new Exception(),"no net");
            }
            return;
        }
        runBusinessInterface(null,url,callBack);
    }

    public synchronized void get(Object requestOb,final String url, final ResponseCallBack<JSONObject> callBack){
        if(!checkNetwork()){
            if(callBack != null){
                callBack.onFailure(requestOb,new Exception(),"no net");
            }
            return;
        }
        runBusinessInterface(requestOb,url,callBack);
    }

}
