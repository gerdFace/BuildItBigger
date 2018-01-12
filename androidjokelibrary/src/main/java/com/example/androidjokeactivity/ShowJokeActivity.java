package com.example.androidjokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.androidjokeactivity.LolzConstants.LOLZ;

public class ShowJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);

        String lolz = getIntent().getStringExtra(LOLZ);
        TextView lolzView = findViewById(R.id.lolz_text);
        lolzView.setText(lolz);
    }
}
