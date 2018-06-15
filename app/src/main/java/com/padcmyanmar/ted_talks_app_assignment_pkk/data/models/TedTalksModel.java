package com.padcmyanmar.ted_talks_app_assignment_pkk.data.models;

import android.util.Log;

import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.TedDataAgent;

/**
 * Created by paikhantko on 6/15/18.
 */

public class TedTalksModel {

    private static TedTalksModel mtalksInstance;
    private TedDataAgent mdataAgent;
    public static final String DUMMY_TOKEN="b002c7e1a528b7cb460933fc2875e916";

    private TedTalksModel() {
        mdataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
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
}
