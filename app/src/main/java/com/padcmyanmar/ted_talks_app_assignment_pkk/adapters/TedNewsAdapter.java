package com.padcmyanmar.ted_talks_app_assignment_pkk.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted_talks_app_assignment_pkk.R;
import com.padcmyanmar.ted_talks_app_assignment_pkk.delegates.TedNewsDelegate;
import com.padcmyanmar.ted_talks_app_assignment_pkk.viewholders.TedNewsViewHolder;

/**
 * Created by Pai Khant Ko on 6/2/2018.
 */

public class TedNewsAdapter extends RecyclerView.Adapter {

    private TedNewsDelegate mtedNewsDelegate;

    public TedNewsAdapter(TedNewsDelegate tedNewsDelegate) {
        this.mtedNewsDelegate=tedNewsDelegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.view_holder_ted_news,parent,false);
        return new TedNewsViewHolder(itemView,mtedNewsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
