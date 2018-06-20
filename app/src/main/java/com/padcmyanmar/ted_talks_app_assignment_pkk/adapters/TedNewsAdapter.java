package com.padcmyanmar.ted_talks_app_assignment_pkk.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted_talks_app_assignment_pkk.R;
import com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app_assignment_pkk.delegates.TedNewsDelegate;
import com.padcmyanmar.ted_talks_app_assignment_pkk.viewholders.TedNewsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pai Khant Ko on 6/2/2018.
 */

public class TedNewsAdapter extends RecyclerView.Adapter<TedNewsViewHolder> {

    private TedNewsDelegate mtedNewsDelegate;
    private List<TedTalksVO> mtedTalksList;

    public TedNewsAdapter(TedNewsDelegate tedNewsDelegate) {
        this.mtedNewsDelegate=tedNewsDelegate;
        mtedTalksList=new ArrayList<>();
    }

    @NonNull
    @Override
    public TedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.view_holder_ted_news,parent,false);
        return new TedNewsViewHolder(itemView,mtedNewsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull TedNewsViewHolder holder, int position) {
        holder.setTedTalksData(mtedTalksList.get(position));
    }

    @Override
    public int getItemCount() {
        return mtedTalksList.size();
    }

    public void setTedTalksList(List<TedTalksVO> tedTalksList){
        mtedTalksList=tedTalksList;
        notifyDataSetChanged();
    }
}
