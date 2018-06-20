package com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by paikhantko on 6/15/18.
 */

public class TagVO {

    @SerializedName("tag_id")
    private int tagId;

    @SerializedName("tag")
    private String tag;

    @SerializedName("description")
    private String description;
}
