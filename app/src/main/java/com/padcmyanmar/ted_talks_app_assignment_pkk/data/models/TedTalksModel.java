package com.padcmyanmar.ted_talks_app_assignment_pkk.data.models;

import android.util.Log;

import com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app_assignment_pkk.events.SuccessGetTedTalksEvent;
import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.OkHttpDataAgentImpl;
import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.RetrofitDataAgentImpl;
import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.TedDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paikhantko on 6/15/18.
 */

public class TedTalksModel {

    private static TedTalksModel mtalksInstance;
    private TedDataAgent mdataAgent;
    private Map<Integer,TedTalksVO> mtalksMap;
    public static final String DUMMY_TOKEN="b002c7e1a528b7cb460933fc2875e916";

    private TedTalksModel() {
//        mdataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
//        mdataAgent= OkHttpDataAgentImpl.getObjInstance();
        mdataAgent= RetrofitDataAgentImpl.getobjInstance();
        mtalksMap=new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static TedTalksModel getMtalksInstance() {
        if (mtalksInstance==null){
            mtalksInstance=new TedTalksModel();
        }
        return mtalksInstance;
    }

    public void loadTalksList(){
        mdataAgent.loadTalksList(1,DUMMY_TOKEN);
    }

    public TedTalksVO getTedTalksById(int talkId){
        return mtalksMap.get(talkId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetTedTalks(SuccessGetTedTalksEvent event){
        for (TedTalksVO tedTalks:event.getTalksList()) {
            mtalksMap.put(tedTalks.getTalkId(),tedTalks);
        }
    }
}
