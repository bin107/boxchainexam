package com.example.dobin.boxchainexam.component.presenter;

import com.example.dobin.boxchainexam.component.model.User;
import com.example.dobin.boxchainexam.component.view.UserDetailsView;

import java.util.List;

public interface UserDetailContract {

    public interface Presenter {
        void parseComponentData();

        void callCreateComponent();

        void addDataToView(List<User> users);
    }

    public interface View {

        void onFinish(List<UserDetailsView> userDetailView);

        void onFailure();
    }
}
