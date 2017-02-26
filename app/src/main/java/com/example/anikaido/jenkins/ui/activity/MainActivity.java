package com.example.anikaido.jenkins.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.anikaido.jenkins.R;
import com.example.anikaido.jenkins.domain.JobStatusDomain;
import com.example.anikaido.jenkins.service.JenkinsService;
import com.example.anikaido.jenkins.ui.adapter.MainRecyclerViewAdapter;
import com.example.anikaido.jenkins.ui.helper.MainActivityHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.OnRecyclerListener {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.main_setting_layout)
    LinearLayout mSettingRequiredLayout;

    MainRecyclerViewAdapter mAdapter;

    MainActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolBar();
        mHelper = new MainActivityHelper(this, new JenkinsService());
    }

    /**
     * ツールバーのセットアップ
     */
    private void setupToolBar() {
        mToolbar.setTitle("Jenkins Status");
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mHelper.getHost().isEmpty()) {
            mSettingRequiredLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, mHelper.getColumn()));
            setupRecyclerViewContent();
            mSettingRequiredLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * recycleViewの初期セットアップ
     */
    private void setupRecyclerViewContent() {
        final MainActivity activity = this;

        mHelper.getJobStatus().subscribe(new Action1<List<JobStatusDomain>>() {
            @Override
            public void call(List<JobStatusDomain> jobStatusDomainList) {
                mAdapter = new MainRecyclerViewAdapter(activity, jobStatusDomainList, activity);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onRecyclerClicked(View v, int position) {
    }

    @OnClick(R.id.main_setting_button)
    public void gotoSetting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
