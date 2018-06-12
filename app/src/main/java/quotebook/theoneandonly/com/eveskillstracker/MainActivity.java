package quotebook.theoneandonly.com.eveskillstracker;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    ProgressBar prg;//progress bar
    TextView logs;//progress text
    private String clientId = "bbafe50c732347c2bf42814d609e014e"; //the client id from registration
    private String redirectUri = "eveauth-app://callback/";
    //auth -encryption from secret key and client id
    private String auth = "Basic YmJhZmU1MGM3MzIzNDdjMmJmNDI4MTRkNjA5ZTAxNGU6Y1NSSEtBTHZHb0N4amVjRThNR3J4VWtXUGMxekVpWFg1QzljdmtRTQ==";
    private String grant = "authorization_code";
    private String content = "application/x-www-form-urlencoded";
    private ImageButton button; //login button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton) findViewById(R.id.login);

        //Click login
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://login.eveonline.com/oauth/authorize/?response_type=code&redirect_uri=" + redirectUri + "&client_id=" + clientId + "&scope=esi-skills.read_skills.v1%20esi-skills.read_skillqueue.v1%20esi-wallet.read_character_wallet.v1"));
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        //after redirecting back
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String code = uri.getQueryParameter("code");
            button.setVisibility(View.INVISIBLE);

            String host = "login.eveonline.com";

            //pass into the background code (works with the API)
            SyncPack mysyncpack = new SyncPack(code, host, auth, content, grant);

            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //begin API calls
            new ApiMagic(mysyncpack).execute();

        }

    }

    //NOTE: The nested OnResponse calls are implemented to guarantee asynchronous
    //performance. Some information needs to be found before other information is
    // found and it needs to be guaranteed to make sure we don't get unexpected errors
    public class ApiMagic extends AsyncTask<Void, Void, Void>{
        private SyncPack mysyncpack;    //Character info
        private String thetoken = "";
        private String thename = "";
        private String useragent = "macky";
        private String theID = "";
        private String host = "login.eveonline.com";
        private String token_and_type;
        private String thecharisma = "";
        private String theintelligence = "";
        private String thememory = "";
        private String theperception = "";
        private String thewillpower = "";
        private float money = 0;
        private String portraitImage;
        private List<QUS> queues_of_skill; //skill arrays
        private List <mySkills.skills> list_of_skill_levels;
        private ArrayList <PackagedSkillInfo> my_skill_pack = new ArrayList<>();
        private ArrayList <Packagedqueue> myqueuepack = new ArrayList<>();
        private List<skillIDS> myskillids;
        private int i = 0; //looping variables
        private int j = 0;
        private int k = 0;

        ApiMagic(SyncPack mysyncpack){
            this.mysyncpack = mysyncpack;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



        }

        @Override
        protected Void doInBackground(Void... aVoid) {

            prg = (ProgressBar) findViewById(R.id.progressBar);

            logs = (TextView) findViewById(R.id.logs);

            //Note: Retrofit performs the calls. It gets mapped into classes
            //with fields of the same name and mapping as the information. Very
            //useful tool.

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://eveonline.com")
                    .addConverterFactory(GsonConverterFactory.create());


            Retrofit retrofit = builder.build();
            EveClient client = retrofit.create(EveClient.class);
            Call<AccessToken> accessTokenCall = client.getAccessToken(mysyncpack.getAuth(),
                    mysyncpack.getContent(), mysyncpack.getHost(), mysyncpack.getGrant(), mysyncpack.getCode()
            );


            accessTokenCall.enqueue(new Callback<AccessToken>() { //get access token
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    thetoken = response.body().getAccess_token();
                    prg.setProgress(1428);
                    logs.setText("Retrieving access...(14% done)");

                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();

                    Retrofit.Builder builder1 = new Retrofit.Builder()
                            .baseUrl("https://eveonline.com")
                            .addConverterFactory(GsonConverterFactory.create(gson));

                    Retrofit retrofit1 = builder1.build();
                    userId client1 = retrofit1.create(userId.class);
                    token_and_type = "Bearer " + thetoken;
                    Call<UserCreds> UserCredsCall = client1.credential_for_user(token_and_type);
                    UserCredsCall.enqueue(new Callback<UserCreds>() { //get user id and username
                        @Override
                        public void onResponse(Call<UserCreds> call, Response<UserCreds> respond) {
                            thename = respond.body().getChar_name();
                            theID = respond.body().getChar_id();
                            logs.setText("Retrieving access...(29% done)");
                            prg.setProgress(2856);
                            Gson gson = new GsonBuilder()
                                    .setLenient()
                                    .create();

                            Retrofit.Builder builder1 = new Retrofit.Builder()
                                    .baseUrl("https://esi.evetech.net/")
                                    .addConverterFactory(GsonConverterFactory.create(gson));

                            Retrofit retrofit1 = builder1.build();
                            CharacterSkills client1 = retrofit1.create(CharacterSkills.class);
                            Call<Skillsheet> skillsheetCall = client1.attributes(token_and_type, theID);
                            skillsheetCall.enqueue(new Callback<Skillsheet>() {
                                @Override
                                public void onResponse(Call<Skillsheet> call, Response<Skillsheet> response) {// get attributes
                                    thecharisma = response.body().getCharisma();
                                    theintelligence = response.body().getIntelligence();
                                    thememory = response.body().getMemory();
                                    theperception = response.body().getPerception();
                                    thewillpower = response.body().getWillpower();
                                    logs.setText("Retrieving character attributes...(42% done)");
                                    prg.setProgress(4284);



                                    Retrofit.Builder builder = new Retrofit.Builder()
                                            .baseUrl("https://esi.evetech.net/")
                                            .addConverterFactory(GsonConverterFactory.create());

                                    Retrofit retrofit = builder.build();

                                    skillsQueue client = retrofit.create(skillsQueue.class);
                                    Call<List<QUS>> myQUScall = client.queues(token_and_type, theID);
                                    myQUScall.enqueue(new Callback<List<QUS>>() {
                                        @Override
                                        public void onResponse(Call<List<QUS>> call, Response<List<QUS>> response) {
                                            queues_of_skill = response.body();
                                            logs.setText("Retrieving character attributes...(57% done)");
                                            prg.setProgress(5712);







                                            Retrofit.Builder builder = new Retrofit.Builder()
                                                    .baseUrl("https://esi.evetech.net/")
                                                    .addConverterFactory(GsonConverterFactory.create());

                                            Retrofit retrofit = builder.build();

                                            SkillLevel client = retrofit.create(SkillLevel.class);
                                            Call<mySkills> mySkillscall = client.skills(token_and_type, theID);
                                            mySkillscall.enqueue(new Callback<mySkills>() {
                                                @Override
                                                public void onResponse(Call<mySkills> call, Response<mySkills> response) { //get my skills
                                                    list_of_skill_levels = response.body().getSkills();
                                                    logs.setText("Retrieving skills...(81% done)");
                                                    prg.setProgress(8140);




                                                    Gson gson = new GsonBuilder()
                                                            .setLenient()
                                                            .create();



                                                    Retrofit.Builder builder = new Retrofit.Builder()
                                                            .baseUrl("https://esi.evetech.net/")
                                                            .addConverterFactory(GsonConverterFactory.create());



                                                    Retrofit retrofit = builder.build();

                                                    Wallet client = retrofit.create(Wallet.class);
                                                    Call<Integer> myCurrencyCall = client.walletvalue(token_and_type, theID);
                                                    myCurrencyCall.enqueue(new Callback<Integer>() {
                                                        @Override
                                                        public void onResponse(Call<Integer> call, Response<Integer> response) { //Get wallet
                                                            money = response.body();
                                                            logs.setText("Finishing up...");
                                                            prg.setProgress(10000);






                                                            Retrofit.Builder builder = new Retrofit.Builder()
                                                                    .baseUrl("https://esi.evetech.net/")
                                                                    .addConverterFactory(GsonConverterFactory.create());

                                                            Retrofit retrofit = builder.build();

                                                            getPortrait client = retrofit.create(getPortrait.class);
                                                            Call<Portrait> PortraitCall = client.portraitcreds(token_and_type, theID);
                                                            PortraitCall.enqueue(new Callback<Portrait>() {
                                                                @Override
                                                                public void onResponse(Call<Portrait> call, Response<Portrait> response) { //get portrait
                                                                    portraitImage = response.body().getpx512x512();


                                                                    myskillids = new LinkedList<skillIDS>();


                                                                    //We need to get the skill Id's to get the names of each skill so put
                                                                    //the ID's in a list
                                                                    for(; i < list_of_skill_levels.size(); ++i){

                                                                        skillIDS skillidclass = new skillIDS(list_of_skill_levels.get(i).getSkill_id());
                                                                        myskillids.add(skillidclass);

                                                                    }
                                                                    i = 0;


                                                                    Retrofit.Builder builder = new Retrofit.Builder()
                                                                            .baseUrl("https://esi.evetech.net/")
                                                                            .addConverterFactory(GsonConverterFactory.create());

                                                                    Retrofit retrofit = builder.build();

                                                                    attributes clients = retrofit.create(attributes.class);


                                                                    while(k < queues_of_skill.size()) {
                                                                        Call<skillAttributes> skillAttributesCall = clients.Attribution(token_and_type, queues_of_skill.get(k).getSkill_id());
                                                                        try {

                                                                            Response<skillAttributes> attributesResponse = skillAttributesCall.execute();

                                                                            //all the necessary info of a queued skill can conviently be packaged in a class
                                                                            Packagedqueue queuepack = new Packagedqueue(queues_of_skill.get(k).getFinish_date(),
                                                                                    attributesResponse.body().getName(),
                                                                                    attributesResponse.body().getDescription(),
                                                                                    queues_of_skill.get(k).getQueue_position(),
                                                                                    queues_of_skill.get(k).getStrart_date()
                                                                            );

                                                                            //add it to an array
                                                                            myqueuepack.add(queuepack);

                                                                            ++k;


                                                                        }catch(IOException e) {
                                                                            Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();

                                                                            ++k;

                                                                        }

                                                                    }




                                                                    while(i < myskillids.size()) {//since we have a loop, this call must be synchrounous
                                                                        Call<skillAttributes> skillAttributesCall = clients.Attribution(token_and_type, myskillids.get(i).getType_id());
                                                                        try {

                                                                            Response<skillAttributes> attributesResponse = skillAttributesCall.execute();


                                                                            for(; j < attributesResponse.body().getDogma_attributes().size(); ++j){

                                                                                if(attributesResponse.body().getDogma_attributes().get(j).getAttribute_id() == 275) {
                                                                                //275 is the id for the skill rank which is used as a multiplier for our projection
                                                                                    //calculation
                                                                                    break;
                                                                                }

                                                                            }




                                                                            //all the necessary info of a skill can conveniently be packaged in a class

                                                                            PackagedSkillInfo pack = new PackagedSkillInfo(attributesResponse.body().getName(),
                                                                                    attributesResponse.body().getDescription(),
                                                                                    attributesResponse.body().getDogma_attributes().get(j).getValue(),
                                                                                    list_of_skill_levels.get(i).getActive_skill_level(),
                                                                                    list_of_skill_levels.get(i).getSkillpoints_in_skill(),
                                                                                    list_of_skill_levels.get(i).getTrained_skill_level());

                                                                            j = 0;

                                                                            //add it to a list
                                                                            my_skill_pack.add(pack);


                                                                            //when we are done with our loop, start new activity
                                                                            if (i == myskillids.size() - 1) {
                                                                                Intent intent = new Intent(MainActivity.this, LoggedIn.class);
                                                                                Bundle extras = new Bundle();
                                                                                extras.putString("charisma", thecharisma);
                                                                                extras.putString("intelligence", theintelligence);
                                                                                extras.putString("memory", thememory);
                                                                                extras.putString("perception", theperception);
                                                                                extras.putString("willpower", thewillpower);
                                                                                extras.putFloat("money", money);
                                                                                extras.putString("picture", portraitImage);
                                                                                extras.putString("name", thename);
                                                                                extras.putSerializable("skills", (Serializable) list_of_skill_levels);
                                                                                extras.putSerializable("queuedskills", (Serializable) queues_of_skill);
                                                                                extras.putSerializable("skillpack", (Serializable) my_skill_pack);
                                                                                extras.putSerializable("qpack", (Serializable) myqueuepack);
                                                                                Toast.makeText(MainActivity.this, "Welcome " + thename + "!", Toast.LENGTH_SHORT).show();

                                                                                intent.putExtras(extras);
                                                                                startActivity(intent);
                                                                            }

                                                                            ++i;


                                                                        } catch(IOException e) {
                                                                            Toast.makeText(MainActivity.this, "Network failure...moving forward", Toast.LENGTH_SHORT).show();

                                                                            ++i;

                                                                        }

                                                                    }


                                                                        }





                                                                @Override
                                                                public void onFailure(Call<Portrait> call, Throwable t) {
                                                                    Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();


                                                                }
                                                            });









                                                        }

                                                        @Override
                                                        public void onFailure(Call<Integer> call, Throwable t) {
                                                            Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });








                                                }

                                                @Override
                                                public void onFailure(Call<mySkills> call, Throwable t) {
                                                    Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();


                                                }
                                            });


                                        }

                                        @Override
                                        public void onFailure(Call<List<QUS>> call, Throwable t) {
                                            Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();

                                        }
                                    });




                                }

                                @Override
                                public void onFailure(Call<Skillsheet> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();


                                }
                            });




                        }

                        @Override
                        public void onFailure(Call<UserCreds> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();


                        }
                    });



                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Network failure. Please try again", Toast.LENGTH_SHORT).show();


                }
            });

            return null;
        }
    }





}


