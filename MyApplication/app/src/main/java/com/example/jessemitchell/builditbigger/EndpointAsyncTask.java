package com.example.jessemitchell.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jesse.mitchell.builditbigger.jokesapi.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.jessemitchell.builditbigger.BuildConfig.APP_ENGINE_END_POINT;
import static com.example.jessemitchell.builditbigger.BuildConfig.APP_ENGINE_PORT;
import static com.example.jessemitchell.builditbigger.BuildConfig.APP_ENGINE_URL;

/**
 * Created by jesse.mitchell on 3/16/2017.
 */

public class EndpointAsyncTask extends AsyncTask<String, Void, List<String>> {
    private MyApi myApiService = null;
    private String APP_ENGINE_ROOT = APP_ENGINE_URL + ":" + APP_ENGINE_PORT + APP_ENGINE_END_POINT;

    @Override
    protected List<String> doInBackground(String... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(APP_ENGINE_ROOT)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }
        List<String> data = new ArrayList<>();
        String name = params[0];
        int number = Integer.parseInt(params[1]);

        try {
            switch (name)
            {
                case "qa":
                    data =myApiService.questionAnswer(number).execute().getData();
                    break;
                case "story":
                    data = myApiService.story(number).execute().getData();
                    break;
                default:
                    data = myApiService.knockKnock(number).execute().getData();
                    break;
            }
        } catch (IOException e) {
            Log.d("AsychTask:",e.getMessage());
        }
        return data;
    }
}
