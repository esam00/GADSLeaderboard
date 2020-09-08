package com.essam.leaderboard.ui.submit;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.essam.leaderboard.Api.ApiClient;
import com.essam.leaderboard.utils.Consts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitViewModel extends ViewModel {
    private static final String TAG = SubmitViewModel.class.getSimpleName();
    MutableLiveData<Response<Void>> responseLiveData = new MutableLiveData<>();

    public void submit(String firstName, String lastName, String email, String link){
        ApiClient.getPostInstance(Consts.SUBMIT_BASE_URL).submit(
                firstName,
                lastName,
                email,
                link

        ).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,@NonNull Response<Void> response) {
                Log.i(TAG, "submit response: " + response);
                responseLiveData.setValue(response);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
                Log.i(TAG, "submit failed: " + t);
                responseLiveData.setValue(null);
            }
        });
    }

}

