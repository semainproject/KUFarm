package com.example.narathorn.kufarm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Information extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tempAir, humidAir, humidSoil, ph, uv, lastUpdate;
    Button submit;
    Spinner spinner;
    String soilType;
    DatabaseReference db, dbSave;
    DocumentReference dr = FirebaseFirestore.getInstance().document("history/160218");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        tempAir = (TextView) findViewById(R.id.tempAir);
        humidAir = (TextView) findViewById(R.id.humidAir);
        humidSoil = (TextView) findViewById(R.id.humidSoil);
        ph = (TextView) findViewById(R.id.ph);
        uv = (TextView) findViewById(R.id.uv);
        lastUpdate = (TextView) findViewById(R.id.lastUpdate);
        submit = (Button) findViewById(R.id.submit);
        spinner = (Spinner) findViewById(R.id.spinner);
        db = FirebaseDatabase.getInstance().getReference("Information");
        dbSave = FirebaseDatabase.getInstance().getReference("History");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.soilTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                soilType = text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        queryInformation();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    public void queryInformation() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                InformationGetter getter = dataSnapshot.getValue(InformationGetter.class);
                tempAir.setText(String.valueOf(getter.getTempAir()) + "˚C");
                humidAir.setText(String.valueOf(getter.getHumidAir()) + " %");
                humidSoil.setText(String.valueOf(getter.getHumidSoil()) + " %");
                ph.setText(String.valueOf(getter.getPh()));
                uv.setText(String.valueOf(getter.getUv()));
                lastUpdate.setHint("last updated : " + getter.getDate() + "  " + getter.getTime());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void addData() {
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                InformationGetter getter = dataSnapshot.getValue(InformationGetter.class);
                double temp = getter.getTempAir();
                double humAir = getter.getHumidAir();
                double humSoil = getter.getHumidSoil();
                double phs = getter.getPh();
                double uvs = getter.getUv();
                String date = getter.getDate();
                String time = getter.getTime();
                InformationGetter ig = new InformationGetter(temp, humAir, humSoil, phs, uvs, date, time, soilType);
                dbSave.child(date).child(time).setValue(ig);
                Toast.makeText(Information.this, "Added", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Information.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
