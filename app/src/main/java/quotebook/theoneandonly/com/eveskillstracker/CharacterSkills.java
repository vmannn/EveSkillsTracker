package quotebook.theoneandonly.com.eveskillstracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fvf on 6/4/18.
 */

public interface CharacterSkills {

    @Headers("Accept: application/json")
    @GET("/dev/characters/{character_id}/attributes/")
    Call<Skillsheet> attributes(@Header("Authorization") String token_and_type,
                                        @Path("character_id") String character_id);


}