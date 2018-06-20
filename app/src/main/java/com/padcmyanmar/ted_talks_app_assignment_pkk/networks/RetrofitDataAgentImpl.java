package com.padcmyanmar.ted_talks_app_assignment_pkk.networks;

import com.padcmyanmar.ted_talks_app_assignment_pkk.events.ErrorApiEvent;
import com.padcmyanmar.ted_talks_app_assignment_pkk.events.SuccessGetTedTalksEvent;
import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.responses.GetTedTalksResponse;
import com.padcmyanmar.ted_talks_app_assignment_pkk.utils.TedTalksConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paikhantko on 6/19/18.
 */

public class RetrofitDataAgentImpl implements TedDataAgent {

    private static RetrofitDataAgentImpl mobjInstance;
    private TedApi mTheApi;

    private RetrofitDataAgentImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(TedTalksConstants.TED_TALKS_API_BASED_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi=retrofit.create(TedApi.class);
    }

    public static RetrofitDataAgentImpl getobjInstance(){
        if (mobjInstance==null){
            mobjInstance=new RetrofitDataAgentImpl();
        }
        return mobjInstance;
    }

    @Override
    public void loadTalksList(int page, String accessToken) {
        Call<GetTedTalksResponse> loadTedTalksCall=mTheApi.loadTedTalksList(accessToken,page);
        loadTedTalksCall.enqueue(new Callback<GetTedTalksResponse>() {// anonymous inner class
            @Override
            public void onResponse(Call<GetTedTalksResponse> call, Response<GetTedTalksResponse> response) {
                GetTedTalksResponse tedTalksResponse=response.body();
                if(tedTalksResponse !=null && tedTalksResponse.isResponseOK()){
                    SuccessGetTedTalksEvent event=new SuccessGetTedTalksEvent(tedTalksResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                }else{
                    if(tedTalksResponse==null){
                        ErrorApiEvent errorEvent=new ErrorApiEvent("Empty response in network call.");
                        EventBus.getDefault().post(errorEvent);
                    }else{
                        ErrorApiEvent errorEvent=new ErrorApiEvent(tedTalksResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTedTalksResponse> call, Throwable t) {// Server down or api crashes
                ErrorApiEvent errorEvent=new ErrorApiEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
