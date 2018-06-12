package quotebook.theoneandonly.com.eveskillstracker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Created by fvf on 6/3/18.
 */

public interface userId {

    @Headers("Accept: application/json")
    @GET("https://login.eveonline.com/oauth/verify")
    Call<UserCreds> credential_for_user(@Header("Authorization") String token_and_type


    );
}

