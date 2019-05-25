package com.adamk.watermark;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new StupidTask().execute();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.getDelegate().onPostCreate(savedInstanceState);
    }

    private class StupidTask extends AsyncTask<Void, Void, String>{

        protected String doInBackground(Void... params){
            try {
                Thread.sleep(2000);
            }catch (InterruptedException exc){

            }
            return "nice";
        }

        protected void onPostExecute(String result){
            Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
            startActivity(intent);
        }

    }

}
