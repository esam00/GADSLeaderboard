package com.essam.leaderboard.Api;
import com.essam.leaderboard.model.Leader;
import com.essam.leaderboard.utils.Consts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private ApiService mApiService;
    private static ApiClient INSTANCE;
    private static ApiClient postInstance;

    public ApiClient(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public static ApiClient getINSTANCE(String baseUrl) {
        if (INSTANCE == null){
            INSTANCE = new ApiClient(baseUrl);
        }
        return INSTANCE;
    }

    public Call<List<Leader>> getLearningLeaders(){
        return mApiService.getLearningLeaders();
    }

    public Call<List<Leader>> getSkillIqLeaders(){
        return mApiService.getSkillIqLeaders();
    }

    public static ApiClient getPostInstance(String baseUrl) {
        if (postInstance == null){
            postInstance = new ApiClient(baseUrl);
        }
        return postInstance;
    }

    public Call<Void> submit(String firstName, String lastName, String email, String link){
        return mApiService.submit(firstName, lastName, email, link);
    }
}
