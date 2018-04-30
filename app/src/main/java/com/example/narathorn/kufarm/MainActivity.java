package com.example.narathorn.kufarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView information, treePred, history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        information = (ImageView) findViewById(R.id.information);
        treePred = (ImageView) findViewById(R.id.treePred);
        history = (ImageView) findViewById(R.id.history);

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prsInformation();
            }
        });

        treePred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prsTreePred();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prsHistory();
            }
        });
    }

    public void prsInformation() {
        Intent i = new Intent(MainActivity.this, Information.class);
        startActivity(i);
    }

    public void prsTreePred() {
        Intent i = new Intent(MainActivity.this, TreePredicted.class);
        startActivity(i);
    }


    public void prsHistory() {
        Intent i = new Intent(MainActivity.this, History.class);
        startActivity(i);
    }
}
