package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.R;

public class ActivitySpalsh extends ActivityCreate {

    @Override
    protected int getIdLayout() {
        return R.layout.acivity_splash;
    }

    @Override
    protected int getIdView() {
        return R.id.android_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager session=new SessionManager(getApplicationContext());
        UIController uiController=new UIController(this,null);
        ErrorController errorController=new ErrorController();
        CallBackHandlerImpl callBack=new CallBackHandlerImpl(uiController,errorController);
        ImageView zvezda=findViewById(R.id.splashLogo);
        Glide.with(this)
                .load(R.drawable.logo)
                .circleCrop()
                .into(zvezda);
        findViewById(R.id.android_splash).postDelayed(() -> {
            if(session.getAccessToken()==null||session.getRefreshToken()==null){
                callBack.onTools("29","Message");
                callBack.onSuccess(ActivityMain.class);
            }else{
                callBack.onTools("29","Message");
                callBack.onSuccess(ActivityHome.class);
            }
        }, 500);

    }

}
