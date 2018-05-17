package com.example.dobin.boxchainexam.component.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {

    public String id;
    public String fullName;
    public int age;
    public String contactNo;
    public String email;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readString();
        fullName = in.readString();
        age = in.readInt();
        contactNo = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(fullName);
        parcel.writeInt(age);
        parcel.writeString(contactNo);
        parcel.writeString(email);
    }


    public static List<User> parseUserListFromResponse(JsonObject response) {

        JsonArray data = response.getAsJsonArray("users");
        List<User> users = new ArrayList<>();
        for (JsonElement jobsElem : data) {
            JsonObject obj = jobsElem.getAsJsonObject();

            User user = new User();
            user.fullName = obj.get("fullName").isJsonNull() ? "" : obj.get("fullName").getAsString();
            user.age = obj.get("age").isJsonNull() ? 0 : obj.get("age").getAsInt();
            user.contactNo = obj.get("contactNo").isJsonNull() ? "" : obj.get("contactNo").getAsString();
            user.email = obj.get("email").isJsonNull() ? "" : obj.get("email").getAsString();

            users.add(user);
        }
        return users;
    }
}
