package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tvData);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<GetData>> call = api.getFilm();

        call.enqueue(new Callback<List<GetData>>() {
            @Override
            public void onResponse(Call<List<GetData>> call, Response<List<GetData>> response) {
                if (response.isSuccessful()) {

                    List<GetData> films = response.body();
                    for (GetData data : films){
                        String isidata = "";
                        //Title, Description, Release year, and director
                        isidata += "id : "+ data.getId()+ "\n";
                        isidata += "Title : "+ data.getTitle()+ "\n";
                        isidata += "Description : "+ data.getDescription()+ "\n";
                        isidata += "Release Data : "+ data.getRelease_date()+ "\n";
                        isidata += "Director : "+ data.getDirector()+ "\n";

                        tvData.append(isidata);

                        System.out.println("ini adalah tvdata"+tvData);
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<GetData>> call, Throwable t) {
                tvData.setText(t.getMessage());
            }
        });
    }
}