package com.padcmyanmar.ted_talks_app_assignment_pkk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.padcmyanmar.ted_talks_app_assignment_pkk.R;
import com.padcmyanmar.ted_talks_app_assignment_pkk.adapters.TedNewsAdapter;
import com.padcmyanmar.ted_talks_app_assignment_pkk.data.models.TedTalksModel;
import com.padcmyanmar.ted_talks_app_assignment_pkk.data.vos.TedTalksVO;
import com.padcmyanmar.ted_talks_app_assignment_pkk.delegates.TedNewsDelegate;
import com.padcmyanmar.ted_talks_app_assignment_pkk.events.SuccessGetTedTalksEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements TedNewsDelegate{

    TedNewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvTedNews=findViewById(R.id.rv_ted_news);
        adapter=new TedNewsAdapter(this);
        rvTedNews.setAdapter(adapter);
        rvTedNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TedTalksModel.getMtalksInstance().loadTalksList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapTedTalks(TedTalksVO tedTalks) {
        Intent intent=new Intent(getApplicationContext(),TedNewsDetailActivity.class);
        intent.putExtra("TalkId",String.valueOf(tedTalks.getTalkId()));
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetTedTalks(SuccessGetTedTalksEvent event){
        adapter.setTedTalksList(event.getTalksList());
    }
}
