package com.essam.leaderboard.ui.home.fragments.learning;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.essam.leaderboard.Api.ApiClient;
import com.essam.leaderboard.model.Leader;
import com.essam.leaderboard.ui.home.activity.HomeViewModel;
import com.essam.leaderboard.utils.Consts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersViewModel extends ViewModel {
    private static final String TAG = HomeViewModel.class.getSimpleName();
    MutableLiveData<List<Leader>> mListResponseMutableLiveData = new MutableLiveData<>();

    public void getList(){
        Log.i(TAG, "Api request >>>>>>> get learning leaders" );
        ApiClient.getINSTANCE(Consts.GET_LEADERS_BASE_URL).getLearningLeaders(
        ).enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(@NonNull Call<List<Leader>> call, @NonNull Response<List<Leader>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "Get learning leaders response : " + response);
                    mListResponseMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Leader>> call,@NonNull Throwable t) {
                Log.e(TAG, "error" + t);
                mListResponseMutableLiveData.setValue(null);
            }
        });
    }
}

