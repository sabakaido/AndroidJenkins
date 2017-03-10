package com.example.anikaido.jenkins.ui.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anikaido on 2017/02/26.
 */

public class SettingActivityHelper {
    private Activity mActivity;

    public SettingActivityHelper(Activity activity) {
        mActivity = activity;
    }

    public void saveHost(String host) {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("host", host);
        editor.apply();
    }

    public String getHost() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getString("host", "");
    }

    public void saveLike(String like) {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("like", like);
        editor.apply();
    }

    public String getLike() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getString("like", "");
    }

    public void saveCheck(Boolean isChecked) {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putBoolean("check", isChecked);
        editor.apply();
    }

    public Boolean isChecked() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getBoolean("check", false);
    }

    public void saveColumn(int column) {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("column", column);
        editor.apply();
    }

    public int getColumn() {
        SharedPreferences data = mActivity.getSharedPreferences("JenkinsData", Context.MODE_PRIVATE);
        return data.getInt("column", 2);
    }
}
