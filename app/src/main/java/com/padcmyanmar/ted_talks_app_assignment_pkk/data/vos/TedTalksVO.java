package com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by paikhantko on 6/15/18.
 */

public class TedTalksVO {

    @SerializedName("talk_id")
    private int talkId;

    @SerializedName("title")
    private String title;

    @SerializedName("speaker")
    private SpeakerVO speaker;

    @SerializedName("imageUrl")
    private String ImageUrl;

    @SerializedName("durationInSec")
    private String durationInSec;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private List<TagVO> tags;

    public int getTalkId() {
        return talkId;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getDurationInSec() {
        return durationInSec;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTags() {
        return tags;
    }
}
