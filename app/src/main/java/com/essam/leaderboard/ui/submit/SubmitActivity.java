package com.essam.leaderboard.ui.submit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.essam.leaderboard.R;

import retrofit2.Response;

enum DialogState{
    confirm,
    success,
    failed
}
public class SubmitActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mFirstNameEt, mLastNameEt, mEmailEt, mGithubLinkEt;
    private String firstName, lastName, email, link;
    private SubmitViewModel mSubmitViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        mFirstNameEt = findViewById(R.id.et_first_name);
        mLastNameEt = findViewById(R.id.et_last_name);
        mEmailEt = findViewById(R.id.et_email_address);
        mGithubLinkEt = findViewById(R.id.et_github_link);
        findViewById(R.id.back_btn).setOnClickListener(this);
        findViewById(R.id.submit_btn).setOnClickListener(this);

        mSubmitViewModel = new ViewModelProvider(this).get(SubmitViewModel.class);
        mSubmitViewModel.responseLiveData.observe(this, new Observer<Response<Void>>() {
            @Override
            public void onChanged(Response<Void> voidResponse) {
                if (voidResponse.isSuccessful()){
                    showDialog(DialogState.success);
                }else {
                    showDialog(DialogState.failed);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                onBackPressed();
                break;
            case R.id.submit_btn:
                submit();
                break;
            case R.id.iv_cancel: // cancel button in dialog
                dialog.dismiss();
                break;
            case R.id.yes_btn: // yes button in dialog
                dialog.dismiss();
                mSubmitViewModel.submit(firstName,lastName,email,link);
        }
    }

    private void submit() {
        if (allInputValid()){
            firstName = mFirstNameEt.getText().toString();
            lastName = mLastNameEt.getText().toString();
            email = mEmailEt.getText().toString();
            link = mGithubLinkEt.getText().toString();
            showDialog(DialogState.confirm);
        }else {
            Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean allInputValid() {
     return  isNotEmpty(mFirstNameEt) &&
             isNotEmpty(mLastNameEt) &&
             isNotEmpty(mEmailEt) &&
             isNotEmpty(mGithubLinkEt);
    }

    private boolean isNotEmpty(EditText editText){
        return  !editText.getText().toString().isEmpty();
    }

    private Dialog dialog;
    public void showDialog(DialogState state) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);

        switch (state){
            case confirm:
                dialog.setContentView(R.layout.confirm_dialog);
                initConfirmDialogButtons();
                break;
            case success:
                dialog.setContentView(R.layout.success_status_dialog);
                break;
            default:
                dialog.setContentView(R.layout.failed_status_dialog);

        }

        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.disabledColor)));
        }
    }

    private void initConfirmDialogButtons() {
        ImageView cancelIv = (ImageView) dialog.findViewById(R.id.iv_cancel);
        Button yesButton = (Button) dialog.findViewById(R.id.yes_btn);

        cancelIv.setOnClickListener(this);
        yesButton.setOnClickListener(this);
    }

}