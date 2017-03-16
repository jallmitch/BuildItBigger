package com.example.jessemitchell.builditbigger;

import android.os.AsyncTask;

import com.example.jesse.mitchell.builditbigger.jokesapi.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import static com.example.jessemitchell.builditbigger.BuildConfig.APP_ENGINE_END_POINT;
import static com.example.jessemitchell.builditbigger.BuildConfig.APP_ENGINE_PORT;
import static com.example.jessemitchell.builditbigger.BuildConfig.APP_ENGINE_URL;

/**
 * Created by jesse.mitchell on 3/16/2017.
 */

public class EndpointAsyncTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    private String APP_ENGINE_ROOT = APP_ENGINE_URL + ":" + APP_ENGINE_PORT + APP_ENGINE_END_POINT;

    @Override
    protected String doInBackground(String... params) {
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

        String name = params[0];

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
//            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
