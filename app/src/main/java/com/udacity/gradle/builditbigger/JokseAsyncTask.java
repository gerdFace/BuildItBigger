package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.example.androidjokeactivity.ShowJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class JokseAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private Context mContext;

    public JokseAsyncTask(Context context){
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                                      new AndroidJsonFactory(), null)

                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke("joke").execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        Intent intent = new Intent(mContext, ShowJokeActivity.class);

        intent.putExtra("lolz", result);
        Log.d(TAG, "onPostExecute: the joke is: " + result);
        mContext.startActivity(intent);
    }
}