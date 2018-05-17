package com.example.dobin.boxchainexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dobin.boxchainexam.component.model.Elements;
import com.example.dobin.boxchainexam.component.model.User;
import com.example.dobin.boxchainexam.component.presenter.UserDetailContract;
import com.example.dobin.boxchainexam.component.presenter.UserDetailsPresenter;
import com.example.dobin.boxchainexam.component.view.UserDetailsView;
import com.example.dobin.boxchainexam.net.BoxchainService;
import com.example.dobin.boxchainexam.net.RetrofitHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserDetailContract.View {

    private LinearLayout root;
    private ProgressBar pbLoading;
    private UserDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.root);
        pbLoading = findViewById(R.id.pbLoading);
        presenter = new UserDetailsPresenter(this, this);
        presenter.callCreateComponent();
    }


    @Override
    public void onFinish(List<UserDetailsView> userDetailView) {
        for (UserDetailsView detailsView : userDetailView) {
            root.addView(detailsView);
        }
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "Failed to download data", Toast.LENGTH_SHORT).show();
    }
}
