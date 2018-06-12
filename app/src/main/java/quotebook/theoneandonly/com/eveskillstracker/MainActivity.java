package quotebook.theoneandonly.com.eveskillstracker;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Node;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.attr.start;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;




public class MainActivity extends AppCompatActivity {

    ProgressBar prg;
    TextView logs;
    private TextView logintext;
    private String clientId = "bbafe50c732347c2bf42814d609e014e";
    private String clientSecret = "cSRHKALvGoCxjecE8MGrxUkWPc1zEiXX5C9cvkQM";
    private String redirectUri = "eveauth-app://callback/";
    private String auth = "Basic YmJhZmU1MGM3MzIzNDdjMmJmNDI4MTRkNjA5ZTAxNGU6Y1NSSEtBTHZHb0N4amVjRThNR3J4VWtXUGMxekVpWFg1QzljdmtRTQ==";
    private String grant = "authorization_code";
    private String content = "application/x-www-form-urlencoded";
   /* String thetoken = "";
    String thename = "";
    String useragent = "macky";
    String theID = "";
    String host = "login.eveonline.com";
    String token_and_type;
    String thecharisma = "";
    String theintelligence = "";
    String thememory = "";
    String theperception = "";
    String thewillpower = "";
    float money = 0;
    String portraitImage;
    List<QUS> queues_of_skill;
    List <mySkills.skills> list_of_skill_levels;
    List <PackagedSkillInfo> my_skill_pack;
    List<skillIDS> myskillids;
    List<skillAttributes> myskillattributes;
    String code;
    int i = 0;*/
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton) findViewById(R.id.login);

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


        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String code = uri.getQueryParameter("code");
            button.setVisibility(View.INVISIBLE);

            String host = "login.eveonline.com";


            SyncPack mysyncpack = new SyncPack(code, host, auth, content, grant);


          /*  Intent intent = new Intent(MainActivity.this, BackgroundService.class);
            intent.putExtra("code", code);
            intent.putExtra("auth", auth);
            intent.putExtra("content", content);
            intent.putExtra("grant", grant);

            startService(intent); */
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);


            new ApiMagic(mysyncpack).execute();

        }
/*


            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://eveonline.com")
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();
            EveClient client = retrofit.create(EveClient.class);
            Call<AccessToken> accessTokenCall = client.getAccessToken(auth,
                    content, host, grant, code
                     );


            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    thetoken = response.body().getAccess_token();
                    Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();



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
                    UserCredsCall.enqueue(new Callback<UserCreds>() {
                        @Override
                        public void onResponse(Call<UserCreds> call, Response<UserCreds> respond) {
                            thename = respond.body().getChar_name();
                            theID = respond.body().getChar_id();
                            Toast.makeText(MainActivity.this, "HELLO " + thename, Toast.LENGTH_LONG).show();

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
                                public void onResponse(Call<Skillsheet> call, Response<Skillsheet> response) {
                                    Toast.makeText(MainActivity.this, "yay", Toast.LENGTH_LONG).show();
                                    thecharisma = response.body().getCharisma();
                                    theintelligence = response.body().getIntelligence();
                                    thememory = response.body().getMemory();
                                    theperception = response.body().getPerception();
                                    thewillpower = response.body().getWillpower();

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







                                            Retrofit.Builder builder = new Retrofit.Builder()
                                                    .baseUrl("https://esi.evetech.net/")
                                                    .addConverterFactory(GsonConverterFactory.create());

                                            Retrofit retrofit = builder.build();

                                            SkillLevel client = retrofit.create(SkillLevel.class);
                                            Call<mySkills> mySkillscall = client.skills(token_and_type, theID);
                                            mySkillscall.enqueue(new Callback<mySkills>() {
                                                @Override
                                                public void onResponse(Call<mySkills> call, Response<mySkills> response) {
                                                    list_of_skill_levels = response.body().getSkills();




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
                                                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                                                            money = response.body();





                                                            Retrofit.Builder builder = new Retrofit.Builder()
                                                                    .baseUrl("https://esi.evetech.net/")
                                                                    .addConverterFactory(GsonConverterFactory.create());

                                                            Retrofit retrofit = builder.build();

                                                            getPortrait client = retrofit.create(getPortrait.class);
                                                            Call<Portrait> PortraitCall = client.portraitcreds(token_and_type, theID);
                                                            PortraitCall.enqueue(new Callback<Portrait>() {
                                                                @Override
                                                                public void onResponse(Call<Portrait> call, Response<Portrait> response) {
                                                                    portraitImage = response.body().getpx512x512();
                                                                    Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();




                                                                    myskillids = new LinkedList<skillIDS>();

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














                                                                            while(i < myskillids.size()) {
                                                                                Call<skillAttributes> skillAttributesCall = clients.Attribution(token_and_type, myskillids.get(i).getType_id());
                                                                                skillAttributesCall.enqueue(new Callback<skillAttributes>() {
                                                                                    @Override
                                                                                    public void onResponse(Call<skillAttributes> call, Response<skillAttributes> response) {

                                                                                        Toast.makeText(MainActivity.this, "we cool!", Toast.LENGTH_SHORT).show();
                                                                                        // myskillattributes = response.body();


                                                                                        PackagedSkillInfo pack = new PackagedSkillInfo(response.body().getName(),
                                                                                                response.body().getDescription(),
                                                                                                response.body().getDogma_attributes().get(5).getValue(),
                                                                                                list_of_skill_levels.get(i).getActive_skill_level(),
                                                                                                list_of_skill_levels.get(i).getSkillpoints_in_skill(),
                                                                                                list_of_skill_levels.get(i).getTrained_skill_level());

                                                                                        my_skill_pack.add(pack);

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
                                                                                            intent.putExtras(extras);
                                                                                            startActivity(intent);
                                                                                        }

                                                                                        ++i;
                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(Call<skillAttributes> call, Throwable t) {

                                                                                        Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();

                                                                                        ++i;
                                                                                    }
                                                                                });

                                                                            }















                                                                }

                                                                @Override
                                                                public void onFailure(Call<Portrait> call, Throwable t) {
                                                                    Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();


                                                                }
                                                            });









                                                        }

                                                        @Override
                                                        public void onFailure(Call<Integer> call, Throwable t) {
                                                            Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });








                                                }

                                                @Override
                                                public void onFailure(Call<mySkills> call, Throwable t) {

                                                }
                                            });


                                        }

                                        @Override
                                        public void onFailure(Call<List<QUS>> call, Throwable t) {
                                            Toast.makeText(MainActivity.this, "nooooooooo", Toast.LENGTH_LONG).show();
                                        }
                                    });




                                }

                                @Override
                                public void onFailure(Call<Skillsheet> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "noo", Toast.LENGTH_LONG).show();

                                }
                            });




                        }

                        @Override
                        public void onFailure(Call<UserCreds> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_LONG).show();

                        }
                    });



                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();

                }
            });




        }

*/

    }


    public class ApiMagic extends AsyncTask<Void, Void, Void>{
        private SyncPack mysyncpack;
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
        private List<QUS> queues_of_skill;
        private List <mySkills.skills> list_of_skill_levels;
        private ArrayList <PackagedSkillInfo> my_skill_pack = new ArrayList<>();
        private ArrayList <Packagedqueue> myqueuepack = new ArrayList<>();
        private List<skillIDS> myskillids;
        private List<skillAttributes> myskillattributes;
        private int i = 0;
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


            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://eveonline.com")
                    .addConverterFactory(GsonConverterFactory.create());


            Retrofit retrofit = builder.build();
            EveClient client = retrofit.create(EveClient.class);
            Call<AccessToken> accessTokenCall = client.getAccessToken(mysyncpack.getAuth(),
                    mysyncpack.getContent(), mysyncpack.getHost(), mysyncpack.getGrant(), mysyncpack.getCode()
            );


            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    thetoken = response.body().getAccess_token();
                    Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();
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
                    UserCredsCall.enqueue(new Callback<UserCreds>() {
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
                                public void onResponse(Call<Skillsheet> call, Response<Skillsheet> response) {
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
                                                public void onResponse(Call<mySkills> call, Response<mySkills> response) {
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
                                                        public void onResponse(Call<Integer> call, Response<Integer> response) {
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
                                                                public void onResponse(Call<Portrait> call, Response<Portrait> response) {
                                                                    portraitImage = response.body().getpx512x512();











                                                                    myskillids = new LinkedList<skillIDS>();

                                                                   // Toast.makeText(MainActivity.this, "Gathering Data.....", Toast.LENGTH_LONG).show();

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


                                                                            Packagedqueue queuepack = new Packagedqueue(queues_of_skill.get(k).getFinish_date(),
                                                                                    attributesResponse.body().getName(),
                                                                                    attributesResponse.body().getDescription(),
                                                                                    queues_of_skill.get(k).getQueue_position(),
                                                                                    queues_of_skill.get(k).getStrart_date()
                                                                            );


                                                                            myqueuepack.add(queuepack);

                                                                            ++k;


                                                                        }catch(IOException e) {
                                                                            Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();

                                                                            ++k;

                                                                        }

                                                                    }




                                                                    while(i < myskillids.size()) {
                                                                        Call<skillAttributes> skillAttributesCall = clients.Attribution(token_and_type, myskillids.get(i).getType_id());
                                                                        try {

                                                                            Response<skillAttributes> attributesResponse = skillAttributesCall.execute();

                                                                            Toast.makeText(MainActivity.this, "Welcome !" + thename, Toast.LENGTH_SHORT).show();
                                                                            // myskillattributes = response.body();



                                                                            for(; j < attributesResponse.body().getDogma_attributes().size(); ++j){

                                                                                if(attributesResponse.body().getDogma_attributes().get(j).getAttribute_id() == 275) {

                                                                                    break;
                                                                                }

                                                                            }





                                                                            PackagedSkillInfo pack = new PackagedSkillInfo(attributesResponse.body().getName(),
                                                                                    attributesResponse.body().getDescription(),
                                                                                    attributesResponse.body().getDogma_attributes().get(j).getValue(),
                                                                                    list_of_skill_levels.get(i).getActive_skill_level(),
                                                                                    list_of_skill_levels.get(i).getSkillpoints_in_skill(),
                                                                                    list_of_skill_levels.get(i).getTrained_skill_level());

                                                                            j = 0;

                                                                            my_skill_pack.add(pack);

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
                                                                                intent.putExtras(extras);
                                                                                startActivity(intent);
                                                                            }

                                                                            ++i;


                                                                        } catch(IOException e) {
                                                                            Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();

                                                                            ++i;

                                                                        }

                                                                    }















                                                                        }





                                                                @Override
                                                                public void onFailure(Call<Portrait> call, Throwable t) {
                                                                    Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();


                                                                }
                                                            });









                                                        }

                                                        @Override
                                                        public void onFailure(Call<Integer> call, Throwable t) {
                                                            Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });








                                                }

                                                @Override
                                                public void onFailure(Call<mySkills> call, Throwable t) {

                                                }
                                            });


                                        }

                                        @Override
                                        public void onFailure(Call<List<QUS>> call, Throwable t) {
                                            Toast.makeText(MainActivity.this, "nooooooooo", Toast.LENGTH_LONG).show();
                                        }
                                    });




                                }

                                @Override
                                public void onFailure(Call<Skillsheet> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "noo", Toast.LENGTH_LONG).show();

                                }
                            });




                        }

                        @Override
                        public void onFailure(Call<UserCreds> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_LONG).show();

                        }
                    });



                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show();

                }
            });

            return null;
        }
    }





}


