package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import android.util.Log;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Client.SessionContext;
import com.example.sportlife.AndroidBackGround.Dto.Request.RefreshRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.RefreshResponse;
import com.example.sportlife.AndroidBackGround.Security.AuthenticatorRefresh;
import com.example.sportlife.AndroidBackGround.Security.SecurityInterceptor;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import retrofit2.Response;

public class RefreshService{

            OkHttpClient httpClient= new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .build();




    public RefreshResponse refresh(String tokenRefresh) {
        ApiRepository apiRepositor= RetrofitClient.getApiRepository();
        RefreshRequest request=new RefreshRequest(tokenRefresh);
        Response<RefreshResponse> responseCall = null;
        try {
            responseCall = apiRepositor.refresh(request).execute();
            if(responseCall.isSuccessful()&&responseCall.body()!=null) {
                return responseCall.body();
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}
