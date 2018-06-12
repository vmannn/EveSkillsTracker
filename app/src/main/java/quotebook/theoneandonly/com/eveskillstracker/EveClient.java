package quotebook.theoneandonly.com.eveskillstracker;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by fvf on 6/3/18.
 */

public interface EveClient {
   // @Headers("Basic YmJhZmU1MGM3MzIzNDdjMmJmNDI4MTRkNjA5ZTAxNGU6Y1NSSEtBTHZHb0N4amVjRThNR3J4VWtXUGMxekVpWFg1QzljdmtRTQ==")

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
