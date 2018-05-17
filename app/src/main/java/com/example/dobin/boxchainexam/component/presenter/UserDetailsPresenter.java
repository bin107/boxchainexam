package com.example.dobin.boxchainexam.component.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.dobin.boxchainexam.component.model.Elements;
import com.example.dobin.boxchainexam.component.model.User;
import com.example.dobin.boxchainexam.component.view.UserDetailsView;
import com.example.dobin.boxchainexam.net.BoxchainService;
import com.example.dobin.boxchainexam.net.RetrofitHelper;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsPresenter implements UserDetailContract.Presenter {

    private UserDetailContract.View view;
    private Elements elementsData;
    private UserDetailsView userDetail;
    private Context mContext;

    public UserDetailsPresenter(final UserDetailContract.View view,Context context) {
        this.view = view;
        mContext=context;
    }

    @Override
    public void callCreateComponent() {
        BoxchainService apiService = RetrofitHelper.getApiInstance("");
        apiService.getElementEndpoint().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                elementsData = Elements.parseElementsFromResponse(response.body());
//                elementsData = Elements.parseElementsFromResponse2();
                parseComponentData();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    @Override
    public void addDataToView(List<User> users) {
        List<UserDetailsView> userDetailView = new ArrayList<>();
        for (User user : users) {
            userDetail = new UserDetailsView(mContext);
            userDetail.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            for (Elements.Fields fields : elementsData.fields) {
                Log.e("Elements", fields.id + " " + fields.type + " " + fields.label);

                Field[] myFields = User.class.getFields();
                for (Field f : myFields) {
                    String value = "";
                    if (f.getName().equals("fullName"))
                        value = user.fullName;
                    else if (f.getName().equals("age"))
                        value = user.age + "";
                    else if (f.getName().equals("contactNo"))
                        value = user.contactNo;
                    else if (f.getName().equals("email"))
                        value = user.email;

                    if (f.getName().equals(fields.id))
                        userDetail.addTextView(fields.label + ": " + value);
                }

            }
            userDetailView.add(userDetail);
        }
        view.onFinish(userDetailView);
    }

    @Override
    public void parseComponentData() {
        BoxchainService apiService = RetrofitHelper.getApiInstance(elementsData.data + "/");
        apiService.getData().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                addDataToView(User.parseUserListFromResponse(response.body()));

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                view.onFailure();
            }
        });
    }


}
