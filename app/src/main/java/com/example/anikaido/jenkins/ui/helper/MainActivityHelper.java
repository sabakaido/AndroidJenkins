package com.example.anikaido.jenkins.ui.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.anikaido.jenkins.domain.JobStatusDomain;
import com.example.anikaido.jenkins.service.JenkinsService;

import java.util.List;

import rx.Single;

/**
 * Created by anikaido on 2017/02/26.
 */

public class MainActivityHelper {
    private Activity mActivity;

    private JenkinsService mJenkinsService;

    public MainActivityHelper(Activity activity, JenkinsService jenkinsService) {
        mActivity = activity;
        mJenkinsService = jenkinsService;
    }

    public String getHost() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getString("host", "");
    }

    public int getColumn() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getInt("column", 2);
    }

    public Single<List<JobStatusDomain>> getJobStatus() {
        return mJenkinsService.getJobStatusList(getHost());
    }
}
