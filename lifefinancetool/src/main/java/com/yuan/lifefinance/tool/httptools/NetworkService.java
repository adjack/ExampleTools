package com.yuan.lifefinance.tool.httptools;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by jack on 2018/4/11.
 */

public interface NetworkService {

    @POST("{pathName}")
    Call<ResponseBody> httpPost(@Path(value = "pathName", encoded = true) String pathName, @Header("X-JSL-API-AUTH") String authorization, @Body() RequestBody value);

    @POST("{pathName}")
    Call<ResponseBody> httpPost(@Path(value = "pathName", encoded = true) String pathName, @FieldMap Map<String,Object> params);

    @GET("{pathName}")//http://www.baidu.com  http://www.ntsc.ac.cn
    Call<ResponseBody> httpGet(@Path(value = "pathName",encoded = true) String pathName, @QueryMap Map<String,Object> params);

    @GET("http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz002095&scale=5&ma=no&datalen=1")
    Call<ResponseBody> httpGet();
}
