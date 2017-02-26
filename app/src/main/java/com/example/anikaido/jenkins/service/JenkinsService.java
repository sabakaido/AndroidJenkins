package com.example.anikaido.jenkins.service;

import android.util.Log;

import com.example.anikaido.jenkins.domain.JobStatusDomain;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by anikaido on 2017/02/26.
 */
public class JenkinsService {

    public Single<List<JobStatusDomain>> getJobStatusList(String host) {

        final String url = "http://" + host + "/api/json";

        return Single.create(new Single.OnSubscribe<List<JobStatusDomain>>() {
            @Override
            public void call(SingleSubscriber<? super List<JobStatusDomain>> singleSubscriber) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = client.newCall(request).execute();

                    String jsonString = response.body().string();
                    JSONObject json = new JSONObject(jsonString);

                    ObjectMapper mapper = new ObjectMapper();

                    List<JobStatusDomain> jobStatusDomainList = mapper.readValue(json.get("jobs").toString(), new TypeReference<List<JobStatusDomain>>() {
                    });

                    singleSubscriber.onSuccess(jobStatusDomainList);
                    return;
                } catch (IOException e) {
                    Log.d("hoge", e.getMessage());
                } catch (JSONException e) {
                    Log.d("hoge", e.getMessage());
                } catch (Exception e) {
                    Log.d("hoge", e.getMessage());
                }
                singleSubscriber.onSuccess(new ArrayList<JobStatusDomain>());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
