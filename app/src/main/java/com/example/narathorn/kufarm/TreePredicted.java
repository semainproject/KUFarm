package com.example.narathorn.kufarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TreePredicted extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView listView;
    String soil;
    DatabaseReference db, dbData;
    List<TreeGetter> list;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_predicted);

        listView = (ListView) findViewById(R.id.predListView);
        spinner = (Spinner) findViewById(R.id.spinner);
        list = new ArrayList<>();
        dbData = FirebaseDatabase.getInstance().getReference("Information");
        db = FirebaseDatabase.getInstance().getReference("Tree");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.soilTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                soil = text;
                querys();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void querys() {
        dbData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                InformationGetter informationGetter = dataSnapshot.getValue(InformationGetter.class);
                //String soil = informationGetter.getSoil();
                double temp = informationGetter.getTempAir();
                double ph = informationGetter.getPh();
                if(soil.equals("ดินเหนียว")) {
                    clayCheck(temp, ph);
                } else if(soil.equals("ดินร่วน")) {
                    moldCheck(temp, ph);
                } else if(soil.equals("ดินทราย")) {
                    sandyCheck(temp, ph);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void clayCheck(double temp, double ph) {
        if(temp >= 20 && temp < 25) {  //tempLow
            if(ph >= 5 && ph <= 7) {   //phNormal
                db.child("clay").child("tempLow").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else if(temp >= 25 && temp < 30) {  //tempNormal
            if(ph >= 4 && ph < 5) {          //phLow
                db.child("clay").child("tempNormal").child("phLow").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 5 && ph < 7) {  //phNormal
                db.child("clay").child("tempNormal").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 7 && ph <= 8.5) {  //phHigh
                db.child("clay").child("tempNormal").child("phHigh").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else if(temp >= 30 && temp <= 35) {  //tempHigh
            if(ph >= 5 && ph <= 7) {           //phNormal
                db.child("clay").child("tempHigh").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void moldCheck(double temp, double ph) {
        if(temp >= 10 && temp < 25) {  //tempLow
            if(ph >= 4 && ph < 5.5) {   //phLow
                db.child("mold").child("tempLow").child("phLow").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 5.5 && ph < 6.5) { //phNormal
                db.child("mold").child("tempLow").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 6.5 && ph <= 7.5) { //phHigh
                db.child("mold").child("tempLow").child("phHigh").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else if(temp >= 25 && temp < 30) {  //tempNormal
            if(ph >= 4 && ph < 5.5) {          //phLow
                db.child("mold").child("tempNormal").child("phLow").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 5.5 && ph < 6.5) {  //phNormal
                db.child("mold").child("tempNormal").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 6.5 && ph <= 7.5) {  //phHigh
                db.child("mold").child("tempNormal").child("phHigh").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else if(temp >= 30 && temp <= 45) {  //tempHigh
            if(ph >= 5.5 && ph < 6.5) {        //phNormal
                db.child("mold").child("tempHigh").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 6.5 && ph <= 7.5) { //phHigh
                db.child("mold").child("tempHigh").child("phHigh").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void sandyCheck(double temp, double ph) {
        if(temp >= 25 && temp < 30) {  //tempNormal
            if(ph >= 4.5 && ph < 5.5) {    //phLow
                db.child("sandy").child("tempNormal").child("phLow").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 5.5 && ph < 7.5) {  //phNormal
                db.child("sandy").child("tempNormal").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else if(ph >= 7.5 && ph <= 8.5) {  //phHigh
                db.child("sandy").child("tempNormal").child("phHigh").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else if(temp >= 30 && temp <= 40) {  //tempHigh
            if(ph >= 5.5 && ph <= 7.5) {           //phNormal
                db.child("sandy").child("tempHigh").child("phNormal").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot predSnapshot) {
                        list.clear();
                        for(DataSnapshot treeSnapshot : predSnapshot.getChildren()) {
                            TreeGetter treeGetter = treeSnapshot.getValue(TreeGetter.class);
                            list.add(treeGetter);
                        }
                        PredictList adapter = new PredictList(TreePredicted.this, list);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
