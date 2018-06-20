package com.padcmyanmar.ted_talks_app_assignment_pkk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ted_talks_app_assignment_pkk.R;
import com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app_assignment_pkk.delegates.TedNewsDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pai Khant Ko on 6/2/2018.
 */

public class TedNewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_ted_talk_person)
    TextView tvSpeaker;

    @BindView(R.id.tv_ted_talk_title)
    TextView tvTitle;

    @BindView(R.id.tv_ted_talk_time)
    TextView tvTalkTime;

    @BindView(R.id.iv_ted_news_image)
    ImageView ivTedNews;

    private TedNewsDelegate mtedNewsDelegate;
    private TedTalksVO tedTalks;

    public TedNewsViewHolder(View itemView, TedNewsDelegate tedNewsDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mtedNewsDelegate=tedNewsDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtedNewsDelegate.onTapTedTalks(tedTalks);
            }
        });
    }

    public void setTedTalksData(TedTalksVO tedTalks){
        this.tedTalks=tedTalks;
        tvSpeaker.setText(tedTalks.getSpeaker().getSpeakerName());
        tvTitle.setText(tedTalks.getTitle());
        tvTalkTime.setText(tedTalks.getDurationInSec());
        Glide.with(ivTedNews.getContext()).load(tedTalks.getImageUrl()).into(ivTedNews);
    }
}
