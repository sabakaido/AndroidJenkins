package com.example.anikaido.jenkins.ui.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.anikaido.jenkins.domain.JobStatus;
import com.example.anikaido.jenkins.service.JenkinsService;

import java.util.ArrayList;
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

    public void filterWithLike(List<JobStatus> jobStatusList) {
        String like = getLike();
        List<JobStatus> j = new ArrayList<JobStatus>();

        if (! like.isEmpty()) {
            return;
        }

        for (JobStatus jobStatusDomain : jobStatusList) {
            if (jobStatusDomain.getName().contains(like)) {
                j.add(jobStatusDomain);
            }
        }

        jobStatusList.clear();
        jobStatusList.addAll(j);
    }

    public void filterWithCheck(List<JobStatus> jobStatusList) {
        if (! isChecked() {
            return;
        }

        List<JobStatus> j = new ArrayList<JobStatus>();

        for (JobStatus jobStatusDomain : jobStatusList) {
            if (jobStatusDomain.getColor().equals("red")) {
                j.add(jobStatusDomain);
            }
        }

        jobStatusList.clear();
        jobStatusList.addAll(j);
    }

    public String getHost() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getString("host", "");
    }

    public String getLike() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getString("like", "");
    }

    public int getColumn() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getInt("column", 2);
    }

    public Boolean isChecked() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getBoolean("check", false);
    }

    public Single<List<JobStatus>> getJobStatus() {
        return mJenkinsService.getJobStatusList(getHost());
    }
}
