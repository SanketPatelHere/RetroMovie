package com.example.retromovie;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Result> lst;
    CustomAdapter adapter;
    ProgressDialog pd;
    public static final String BASE_URL = "https://api.themoviedb.org/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView)findViewById(R.id.rv);
        lst = new ArrayList<>();

        //lst.add(new DataPojo(1,1000, 500, null));
        //lst.add(new DataPojo(1,"aaa", "ce", "10000",false,false));
        if(!isNetworkConnected())
        {
            Toast.makeText(this, "Internet or Wifi not available", Toast.LENGTH_SHORT).show();
        }
        else
        {
            pd = new ProgressDialog(this);
            pd.setMessage("Loading...");
            pd.show();
            pd.setCancelable(false);
            getRecords();
        }




    }
    private boolean isNetworkConnected() {
        //getSystemService("connectivity");  //or
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public void getRecords()
    {
        //////retrofit result//////////
        //private constructor = object make in that class inside method
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  //encoding to json
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<DataPojo> call = apiService.getMovies();
        Log.i("My call = ",apiService.getMovies()+"");

        call.enqueue(new Callback<DataPojo>() {
            @Override
            public void onResponse(Call<DataPojo> call, Response<DataPojo> response) {
                pd.dismiss();
                Log.i("My JsonResponse = ",call+"\n"+response+"");
                lst = response.body().getResults();
                Log.i("My lst = ",lst+"");
                Log.i("My lst.get(0).getTitle() = ",lst.get(0).getTitle()+"");

                adapter = new CustomAdapter(getApplicationContext(), lst);
                rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                rv.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<DataPojo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "JsonError = "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("My JsonError = ",call+"\n"+t.getMessage());
            }
        });
    }
}
