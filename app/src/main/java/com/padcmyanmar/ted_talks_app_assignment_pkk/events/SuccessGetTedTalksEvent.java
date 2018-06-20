package com.padcmyanmar.ted_talks_app_assignment_pkk.events;

import com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos.TedTalksVO;

import java.util.List;

/**
 * Created by paikhantko on 6/19/18.
 */

public class SuccessGetTedTalksEvent {
    private List<TedTalksVO> talksList;

    public SuccessGetTedTalksEvent(List<TedTalksVO> talksList) {
        this.talksList = talksList;
    }

    public List<TedTalksVO> getTalksList(){
        return talksList;
    }
}
