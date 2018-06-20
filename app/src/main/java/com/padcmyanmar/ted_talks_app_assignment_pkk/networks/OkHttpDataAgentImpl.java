package com.padcmyanmar.ted_talks_app_assignment_pkk.networks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padcmyanmar.ted_talks_app_assignment_pkk.events.ErrorApiEvent;
import com.padcmyanmar.ted_talks_app_assignment_pkk.events.SuccessGetTedTalksEvent;
import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.responses.GetTedTalksResponse;
import com.padcmyanmar.ted_talks_app_assignment_pkk.utils.TedTalksConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by paikhantko on 6/19/18.
 */

public class OkHttpDataAgentImpl implements TedDataAgent {

    private static OkHttpDataAgentImpl objInstance;
    private OkHttpClient okHttpClient;

    private OkHttpDataAgentImpl(){
        okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getObjInstance(){
        if (objInstance==null){
            objInstance=new OkHttpDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                RequestBody formBody=new FormBody.Builder()
                        .add(TedTalksConstants.PARAM_ACCESS_TOKEN,accessToken)
                        .add(TedTalksConstants.PARAM_PAGE,String.valueOf(page))
                        .build();

                Request request=new Request.Builder()
                        .url(TedTalksConstants.TED_TALKS_API_BASED_URL+TedTalksConstants.GET_TED_TALKS)
                        .post(formBody)
                        .build();

                try {
                    Response response = okHttpClient.newCall(request).execute();

                    if(response.isSuccessful()){
                        String responseString=response.toString();
                        return responseString;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                GetTedTalksResponse getTedTalksResponse=new Gson().fromJson(responseString,GetTedTalksResponse.class);
                if(getTedTalksResponse.isResponseOK()){
                    SuccessGetTedTalksEvent successGetTedTalksEvent=new SuccessGetTedTalksEvent(getTedTalksResponse.getTedTalks());
                    EventBus.getDefault().post(successGetTedTalksEvent);
                }else {
                    ErrorApiEvent errorEvent=new ErrorApiEvent(getTedTalksResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }
            }
        }.execute();
    }
}
