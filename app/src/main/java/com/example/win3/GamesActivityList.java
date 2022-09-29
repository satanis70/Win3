package com.example.win3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.example.win3.api.Api;
import com.example.win3.holders.GamesHolder;
import com.example.win3.model.Game;
import com.example.win3.model.GamesModel;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GamesActivityList extends AppCompatActivity {

    ArrayList<Game> arrayListGames = new ArrayList<>();
    Context contex;
    String nameApp;
    TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        textViewName = findViewById(R.id.text_view_games);
        nameApp = "<font color=#ffffff>WIN</font><font color=#1AF423>SPORT2</font>";
        textViewName.setText(Html.fromHtml(nameApp));
        contex = this;
        RecyclerView recyclerView = findViewById(R.id.recycler_view_games);
        getData(recyclerView);
    }

    private void getData(RecyclerView recyclerView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://49.12.202.175/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.getGames()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GamesModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GamesModel gamesModel) {
                        for (int i = 0; i < gamesModel.games.size(); i++) {
                            arrayListGames.add(gamesModel.games.get(i));
                            Log.i("dataGames", gamesModel.games.get(i).toString());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("dataGAMESERROR", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        GamesHolder holder = new GamesHolder(contex, arrayListGames);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(contex);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(holder);
                    }
                });
    }
}