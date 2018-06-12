package quotebook.theoneandonly.com.eveskillstracker;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


//API call
public interface EveClient {

    @Headers("Accept: application/json")
    @POST("https://login.eveonline.com/oauth/token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Header("Authorization") String authorization,
            @Header("Content-Type") String content,
            @Field("Host") String host,
            @Field("grant_type") String grant,
            @Field("code") String code

    );







}
