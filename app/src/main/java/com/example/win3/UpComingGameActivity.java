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
import com.example.win3.holders.UpcomingHolder;
import com.example.win3.model.UpcomingGame;
import com.example.win3.model.UpcomingModel;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpComingGameActivity extends AppCompatActivity {

    TextView textViewName;
    ArrayList<UpcomingGame> arrayListUpcoming = new ArrayList<>();
    String nameApp = "<font color=#ffffff>WIN</font><font color=#1AF423>SPORT2</font>";
    Context contex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_game);
        textViewName = findViewById(R.id.text_view_upcoming);
        textViewName.setText(Html.fromHtml(nameApp));
        contex = this;
        RecyclerView recyclerView = findViewById(R.id.recycler_view_upcoming);
        getData(recyclerView);

    }

    public void getData(RecyclerView recyclerView){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://49.12.202.175/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.getUpcomingGame()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpcomingModel>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpcomingModel upcomingModel) {
                        for (int i = 0; i < upcomingModel.games.size(); i++) {
                            arrayListUpcoming.add(upcomingModel.games.get(i));
                            Log.i("UPCOMING", upcomingModel.games.get(i).description);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("UPCOMING", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        UpcomingHolder upcomingHolder = new UpcomingHolder(arrayListUpcoming, contex);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(contex);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(upcomingHolder);
                    }
                });
    }
}