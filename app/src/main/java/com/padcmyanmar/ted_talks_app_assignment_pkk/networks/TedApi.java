package com.padcmyanmar.ted_talks_app_assignment_pkk.networks;


import com.padcmyanmar.ted_talks_app_assignment_pkk.networks.responses.GetTedTalksResponse;
import com.padcmyanmar.ted_talks_app_assignment_pkk.utils.TedTalksConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by paikhantko on 6/19/18.
 */

public interface TedApi {

    @FormUrlEncoded
    @POST(TedTalksConstants.GET_TED_TALKS)
    Call<GetTedTalksResponse> loadTedTalksList(
            @Field(TedTalksConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedTalksConstants.PARAM_PAGE) int page);
}
