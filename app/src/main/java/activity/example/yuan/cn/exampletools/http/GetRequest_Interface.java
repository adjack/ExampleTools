package activity.example.yuan.cn.exampletools.http;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 123 on 2018/4/3.
 */

public interface GetRequest_Interface{

    @GET()
    Call<Object> getCall();
}
