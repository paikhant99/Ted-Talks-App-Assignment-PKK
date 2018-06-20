package com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by paikhantko on 6/15/18.
 */

public class SpeakerVO {

    @SerializedName("speaker_id")
    private int speakerId;

    @SerializedName("name")
    private String speakerName;

    public int getSpeakerId() {
        return speakerId;
    }

    public String getSpeakerName() {
        return speakerName;
    }
}
