package com.example.win3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.win3.api.Api;
import com.example.win3.holders.TeamsInfoHolder;
import com.example.win3.model.CsgoModel;
import com.example.win3.model.Game;
import com.example.win3.model.Team;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTeamsActivity extends AppCompatActivity {

    ArrayList<Team> arrayListTeams = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_teams);
        context = this;
        RecyclerView recyclerView = findViewById(R.id.recycler_view_teams);
        Intent intent = getIntent();
        String team = intent.getStringExtra("team");
        if (Objects.equals(team, "csgo")){
            getDataCsgo(recyclerView);
        } else if (Objects.equals(team, "dota2")){
            getDataDota2(recyclerView);
        }
    }

    private void getDataDota2(RecyclerView recyclerView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://49.12.202.175/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.getDota2Teams()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CsgoModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CsgoModel csgoModel) {
                        for (int i = 0; i < csgoModel.teams.size(); i++) {
                            arrayListTeams.add(csgoModel.teams.get(i));
                            Log.i("TEAMS", csgoModel.teams.get(i).title);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        TeamsInfoHolder teamsInfoHolder = new TeamsInfoHolder(arrayListTeams, context);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(teamsInfoHolder);
                    }
                });
    }

    private void getDataCsgo(RecyclerView recyclerView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://49.12.202.175/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.getCsGOTeams()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CsgoModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CsgoModel csgoModel) {
                        for (int i = 0; i < csgoModel.teams.size(); i++) {
                            arrayListTeams.add(csgoModel.teams.get(i));
                            Log.i("TEAMS", csgoModel.teams.get(i).title);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        TeamsInfoHolder teamsInfoHolder = new TeamsInfoHolder(arrayListTeams, context);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(teamsInfoHolder);
                    }
                });
    }
}