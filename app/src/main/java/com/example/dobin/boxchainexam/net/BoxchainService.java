package com.example.dobin.boxchainexam.net;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BoxchainService {

    @GET("5afaa7cd2e00005000279004")
    Call<JsonObject> getElementEndpoint();

    @GET("5afaa6902e00005d00278ffb")
    Call<JsonObject> getData();


}
