package com.padcmyanmar.ted_talks_app_assignment_pkk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.ted_talks_app_assignment_pkk.delegates.TedNewsDelegate;

/**
 * Created by Pai Khant Ko on 6/2/2018.
 */

public class TedNewsViewHolder extends RecyclerView.ViewHolder {

    private TedNewsDelegate mtedNewsDelegate;

    public TedNewsViewHolder(View itemView, TedNewsDelegate tedNewsDelegate) {
        super(itemView);
        this.mtedNewsDelegate=tedNewsDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtedNewsDelegate.onTapTedTalks();
            }
        });


    }
}
