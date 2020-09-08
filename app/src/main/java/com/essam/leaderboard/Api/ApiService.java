package com.essam.leaderboard.Api;

import com.essam.leaderboard.model.Leader;
import com.essam.leaderboard.utils.Consts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET(Consts.HOURS)
    Call<List<Leader>> getLearningLeaders();

    @GET(Consts.SKILL_IQ)
    Call<List<Leader>> getSkillIqLeaders();

    @POST(Consts.POST_ID)
    @FormUrlEncoded
    Call<Void>submit(
            @Field(Consts.NAME)String firstName,
            @Field(Consts.LAST_NAME)String lastName,
            @Field(Consts.EMAIL_ADDRESS)String email,
            @Field(Consts.LINK_TO_PROJECT)String link
            );
}