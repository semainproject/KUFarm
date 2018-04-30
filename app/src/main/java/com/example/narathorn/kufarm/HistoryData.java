package com.example.narathorn.kufarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryData extends AppCompatActivity {
    TextView date;
    ListView listView;
    DatabaseReference db;
    int day, month, year;
    List<InformationGetter> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_data);

        date = (TextView) findViewById(R.id.date);
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        day = getIntent().getExtras().getInt("day", -1);
        month = getIntent().getExtras().getInt("month", -1);
        year = getIntent().getExtras().getInt("year", -1);
        String days = String.valueOf(day);
        String years = String.valueOf(year);
        String months;
        if(month < 10) {
            months = "0" + String.valueOf(month);
        } else {
            months = String.valueOf(month);
        }
        date.setText("Date : " + days + "/" + months + "/" + years);
        db = FirebaseDatabase.getInstance().getReference("History").child(years).child(months).child(days);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        InformationGetter informationGetter = list.get(position);

                        AlertDialog.Builder alert = new AlertDialog.Builder(HistoryData.this);
                        alert.setMessage("ข้อมูลต่างๆ\n\n" + "อุณหภูมิ : " + informationGetter.getTempAir() + "˚C\n\n" +
                                         "ความชื้นในอากาศ : " + informationGetter.getHumidAir() + " %\n\n" +
                                         "ความชื้นในดิน : " + informationGetter.getHumidSoil() + " %\n\n" +
                                         "ค่า pH : " + informationGetter.getPh() + "\n\n" +
                                         "UV : " + informationGetter.getUv() + "\n\n" +
                                         "ชนิดของดิน : " + informationGetter.getSoil() + "\n")
                                .setCancelable(false)
                                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot infSnapshot : dataSnapshot.getChildren()) {
                    InformationGetter informationGetter = infSnapshot.getValue(InformationGetter.class);
                    list.add(informationGetter);

                }

                HistoryList adapter = new HistoryList(HistoryData.this, list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
