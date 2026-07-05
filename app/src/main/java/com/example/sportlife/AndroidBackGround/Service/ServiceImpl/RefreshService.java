package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import android.util.Log;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.RefreshRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.RefreshResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

public class RefreshService{

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
