package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportlife.AndroidBackGround.Dto.Response.RefreshResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import lombok.RequiredArgsConstructor;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

    @RequiredArgsConstructor
    public class AuthenticatorRefresh implements Authenticator {
        private  final Context context;
        @Nullable
        @Override
        public Request authenticate(@Nullable Route route, @NonNull Response response) {
            SessionManager session=new SessionManager(context);
            RefreshService refreshService=new RefreshService();
            String tokenRefresh= session.getRefreshToken();
            RefreshResponse refresh=refreshService.refresh(tokenRefresh);
            Log.d("[REFRESH]","[REFRESH_2]");
            if(refresh==null){
                return null;
            }
            Log.d("[REFRESH_BODY_1]",refresh.getRefreshToken());
            Log.d("[REFRESH_BODY_2]",refresh.getAccessToken());
            session.saveToken(refresh.getAccessToken(),refresh.getRefreshToken());
            String tokenAccess=refresh.getAccessToken();
            return response.request().newBuilder().header("Authorization", "Bearer " + tokenAccess).build();
        }
    }
