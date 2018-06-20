package com.padcmyanmar.ted_talks_app_assignment_pkk.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ted_talks_app_assignment_pkk.R;
import com.padcmyanmar.ted_talks_app_assignment_pkk.adapters.TedNewsMoreAdapter;
import com.padcmyanmar.ted_talks_app_assignment_pkk.data.models.TedTalksModel;
import com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos.TedTalksVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pai Khant Ko on 6/8/2018.
 */

public class TedNewsDetailActivity extends BaseActivity {

    @BindView(R.id.iv_ted_details_news_backdrop)
    ImageView ivDetailsNewsBackdrop;

    @BindView(R.id.tv_ted_details_talk_person)
    TextView tvDetailsSpeaker;

    @BindView(R.id.tv_ted_details_talk_title)
    TextView tvDetailsTitle;

    @BindView(R.id.tv_ted_details_talk_long_time)
    TextView tvDetailsTime;

    @BindView(R.id.tv_ted_details_text)
    TextView tvDetailsText;

    @BindView(R.id.tv_ted_details_speaker_name)
    TextView tvDetailsSpeakerName;

    @BindView(R.id.tv_ted_details_speaker_type)
    TextView tvDetailsSpeakerType;

    @BindView(R.id.tv_ted_details_speaker_desc)
    TextView tvDetailsSpeakerDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ted_news_detail);
        ButterKnife.bind(this,this);

        Toolbar toolbar=findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int talkId=Integer.parseInt(getIntent().getStringExtra("TalkId"));

        TedTalksVO talksVO=TedTalksModel.getMtalksInstance().getTedTalksById(talkId);
        bindData(talksVO);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TedNewsDetailActivity.super.onBackPressed();
            }
        });

        RecyclerView rvTedNewsMore=findViewById(R.id.rv_ted_news_more);
        TedNewsMoreAdapter adapter=new TedNewsMoreAdapter();
        rvTedNewsMore.setAdapter(adapter);
        rvTedNewsMore.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }

    public void bindData(TedTalksVO tedTalks){
        Glide.with(ivDetailsNewsBackdrop.getContext()).load(tedTalks.getImageUrl()).into(ivDetailsNewsBackdrop);
        tvDetailsSpeaker.setText(tedTalks.getSpeaker().getSpeakerName());
        tvDetailsTitle.setText(tedTalks.getTitle());
        tvDetailsTime.setText(tedTalks.getDurationInSec());
        tvDetailsText.setText(tedTalks.getDescription());
        tvDetailsSpeakerName.setText(tedTalks.getSpeaker().getSpeakerName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
