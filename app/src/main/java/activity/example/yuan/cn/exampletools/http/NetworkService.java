package activity.example.yuan.cn.exampletools.http;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jack on 2018/4/11.
 */

public interface NetworkService {

    @POST("{pathName}")
    Call<ResponseBody> httpPost(@Path(value = "pathName", encoded = true) String pathName, @Header("X-JSL-API-AUTH") String authorization, @Body() RequestBody value);

    @POST("sas/sasa/fjdjfk")
    Call<ResponseBody> httpPost(@Body() RequestBody value);

    @POST("{name}")
    Call<String> getCall(@Path("name") String pathname, @Header("X-JSL-API-AUTH") String authorization, @Body() RequestBody value);
}
