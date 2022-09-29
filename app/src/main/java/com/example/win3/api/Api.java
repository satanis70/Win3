package com.example.win3.api;

import com.example.win3.model.CsgoModel;
import com.example.win3.model.GamesModel;
import com.example.win3.model.Team;
import com.example.win3.model.UpcomingModel;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface Api {
    @GET("/win3/populargames.json")
    Flowable<GamesModel> getGames();

    @GET("/win3/upcominggames.json")
    Flowable<UpcomingModel> getUpcomingGame();

    @GET("/win3/csgopopularteam.json")
    Flowable<CsgoModel> getCsGOTeams();

    @GET("/win3/dota2popularteam.json")
    Flowable<CsgoModel> getDota2Teams();

}
