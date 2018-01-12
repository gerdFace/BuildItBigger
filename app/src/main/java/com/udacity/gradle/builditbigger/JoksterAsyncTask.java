package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class JoksterAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private Context mContext;

    public JoksterAsyncTask(Context context){
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... params){
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                                      new AndroidJsonFactory(), null)

                    .setRootUrl("https://builditbigger-183214.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            myApiService = builder.build();
        }

        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        Intent intent = new Intent(mContext, JokeDisplayActivity.class);

        intent.putExtra("joke", result);
        mContext.startActivity(intent);
    }
}