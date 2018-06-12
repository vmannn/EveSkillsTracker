package quotebook.theoneandonly.com.eveskillstracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface attributes {


    @Headers("Accept: application/json")
    @GET("/dev/universe/types/{type_id}/")
    Call<skillAttributes> Attribution (@Header("Authorization") String token_and_type,
                                       @Path("type_id") int type_id);



}
