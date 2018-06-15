package com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos;

import java.util.List;

/**
 * Created by paikhantko on 6/15/18.
 */

public class TedTalksVO {
    private int talkId;
    private String title;
    private SpeakerVO speaker;
    private String ImageUrl;
    private String durationInSec;
    private String description;
    private List<TagVO> tags;
}
