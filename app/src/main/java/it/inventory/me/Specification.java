package it.inventory.me;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specification extends AppCompatActivity {

    FirebaseFirestore db;
    private static Activity activity;
    View parentLayout;

    EditText moBarcode;
    EditText moSpecs;

    EditText ppBarcode;
    EditText ppSpecs;

    EditText mmBarcode1;
    EditText mmSpecs1;
    EditText mmBarcode2;
    EditText mmSpecs2;

    EditText lpBarcode;
    EditText lpSpecs;

    EditText dmBarcode;
    EditText dmSpecs;

    EditText hdBarcode1;
    EditText hdSpecs1;
    EditText hdBarcode2;
    EditText hdSpecs2;

    EditText pwBarcode;
    EditText pwSpecs;


    EditText ccBarcode;
    EditText ccSpecs;

    EditText omBarcode1;
    EditText omSpecs1;
    EditText omBarcode2;
    EditText omSpecs2;

    EditText kbBarcode;
    EditText kbSpecs;

    EditText msBarcode;
    EditText msSpecs;

    EditText wcBarcode;
    EditText wcSpecs;

    EditText hsBarcode;
    EditText hsSpecs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specification);
        db = FirebaseFirestore.getInstance();
        activity = Specification.this;
        parentLayout = findViewById(android.R.id.content);


        TextView computerNameSpec = findViewById(R.id.computerNameSpec);
        computerNameSpec.setText(String.valueOf(getIntent().getIntExtra("workstationNumber", 0000)));

        LinearLayout backLayout = findViewById(R.id.backLayout);
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Specification.this, "gpg", Toast.LENGTH_SHORT).show();

            }
        });

        moBarcode = findViewById(R.id.moBarcode);
        moSpecs = findViewById(R.id.moSpecs);

        ppBarcode = findViewById(R.id.ppBarcode);
        ppSpecs = findViewById(R.id.ppSpecs);

        mmBarcode1 = findViewById(R.id.mmBarcode1);
        mmSpecs1 = findViewById(R.id.mmSpecs1);
        mmBarcode2 = findViewById(R.id.mmBarcode2);
        mmSpecs2 = findViewById(R.id.mmSpecs2);

        lpBarcode = findViewById(R.id.lpBarcode);
        lpSpecs = findViewById(R.id.lpSpecs);

        dmBarcode = findViewById(R.id.dmBarcode);
        dmSpecs = findViewById(R.id.dmSpecs);

        hdBarcode1 = findViewById(R.id.hdBarcode1);
        hdSpecs1 = findViewById(R.id.hdSpecs1);
        hdBarcode2 = findViewById(R.id.hdBarcode2);
        hdSpecs2 = findViewById(R.id.hdSpecs2);

        pwBarcode = findViewById(R.id.pwBarcode);
        pwSpecs = findViewById(R.id.pwSpecs);


        ccBarcode = findViewById(R.id.ccBarcode);
        ccSpecs = findViewById(R.id.ccSpecs);

        omBarcode1 = findViewById(R.id.omBarcode1);
        omSpecs1 = findViewById(R.id.omSpecs1);
        omBarcode2 = findViewById(R.id.omBarcode2);
        omSpecs2 = findViewById(R.id.omSpecs2);

        kbBarcode = findViewById(R.id.kbBarcode);
        kbSpecs = findViewById(R.id.kbSpecs);

        msBarcode = findViewById(R.id.msBarcode);
        msSpecs = findViewById(R.id.msSpecs);

        wcBarcode = findViewById(R.id.wcBarcode);
        wcSpecs = findViewById(R.id.wcSpecs);

        hsBarcode = findViewById(R.id.hsBarcode);
        hsSpecs = findViewById(R.id.hsSpecs);


        ImageView saveButton = (ImageView) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // loadSpecs();
                instantSave();

            }
        });

        loadData();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_delete:

                        return true;
                    case R.id.navigation_scan:
                        Intent intent = new Intent(Specification.this, Scanner.class);
                        startActivityForResult(intent, 1);
                        return true;
                    case R.id.navigation_save:

                        return true;
                }
                return false;
            }
        });

    }


    public void instantSave() {


        Map<String, Object> data = new HashMap<>();
        data.put("computer_name", getIntent().getIntExtra("workstationNumber", 0000));
        data.put("last_updated", new Date());

        data.put("motherboard_barcode", moBarcode.getText().toString());
        data.put("motherboard_specs", moSpecs.getText().toString());
        if (!moSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Motherboard").update("specs", FieldValue.arrayUnion(moSpecs.getText().toString()));
        }

        data.put("processor_barcode", ppBarcode.getText().toString());
        data.put("processor_specs", ppSpecs.getText().toString());
        if (!ppSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Processor").update("specs", FieldValue.arrayUnion(ppSpecs.getText().toString()));
        }

        data.put("memory_barcode1", mmBarcode1.getText().toString());
        data.put("memory_specs1", mmSpecs1.getText().toString());
        data.put("memory_barcode2", mmBarcode2.getText().toString());
        data.put("memory_specs2", mmSpecs2.getText().toString());
        if (!mmSpecs1.getText().toString().isEmpty()) {
            db.collection("Specs").document("Memory").update("specs", FieldValue.arrayUnion(mmSpecs1.getText().toString()));
        }
        if (!mmSpecs2.getText().toString().isEmpty()) {
            db.collection("Specs").document("Memory").update("specs", FieldValue.arrayUnion(mmSpecs2.getText().toString()));
        }

        data.put("adapter_barcode", lpBarcode.getText().toString());
        data.put("adapter_specs", lpSpecs.getText().toString());
        if (!lpSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Adapter").update("specs", FieldValue.arrayUnion(lpSpecs.getText().toString()));
        }

        data.put("videocard_barcode", dmBarcode.getText().toString());
        data.put("videocard_specs", dmSpecs.getText().toString());
        if (!dmSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("VideoCard").update("specs", FieldValue.arrayUnion(dmSpecs.getText().toString()));
        }

        data.put("harddisk_barcode1", hdBarcode1.getText().toString());
        data.put("harddisk_specs1", hdSpecs1.getText().toString());
        data.put("harddisk_barcode2", hdBarcode2.getText().toString());
        data.put("harddisk_specs2", hdSpecs2.getText().toString());
        if (!hdSpecs1.getText().toString().isEmpty()) {
            db.collection("Specs").document("HardDisk").update("specs", FieldValue.arrayUnion(hdSpecs1.getText().toString()));
        }
        if (!hdSpecs2.getText().toString().isEmpty()) {
            db.collection("Specs").document("HardDisk").update("specs", FieldValue.arrayUnion(hdSpecs2.getText().toString()));
        }

        data.put("powersupply_barcode", pwBarcode.getText().toString());
        data.put("powersupply_specs", pwSpecs.getText().toString());
        if (!pwSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("PowerSupply").update("specs", FieldValue.arrayUnion(pwSpecs.getText().toString()));
        }

        data.put("casing_barcode", ccBarcode.getText().toString());
        data.put("casing_specs", ccSpecs.getText().toString());
        if (!ccSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Casing").update("specs", FieldValue.arrayUnion(ccSpecs.getText().toString()));
        }

        data.put("monitor_barcode1", omBarcode1.getText().toString());
        data.put("monitor_specs1", omSpecs1.getText().toString());
        data.put("monitor_barcode2", omBarcode2.getText().toString());
        data.put("monitor_specs2", omSpecs2.getText().toString());
        if (!omSpecs1.getText().toString().isEmpty()) {
            db.collection("Specs").document("Monitor").update("specs", FieldValue.arrayUnion(omSpecs1.getText().toString()));
        }
        if (!omSpecs2.getText().toString().isEmpty()) {
            db.collection("Specs").document("Casing").update("specs", FieldValue.arrayUnion(omSpecs2.getText().toString()));
        }

        data.put("keyboard_barcode", kbBarcode.getText().toString());
        data.put("keyboard_specs", kbSpecs.getText().toString());
        if (!kbSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Keyboard").update("specs", FieldValue.arrayUnion(kbSpecs.getText().toString()));
        }

        data.put("mouse_barcode", msBarcode.getText().toString());
        data.put("mouse_specs", msSpecs.getText().toString());
        if (!msSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Mouse").update("specs", FieldValue.arrayUnion(msSpecs.getText().toString()));
        }


        data.put("webcam_barcode", wcBarcode.getText().toString());
        data.put("webcam_specs", wcSpecs.getText().toString());
        if (!wcSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Webcam").update("specs", FieldValue.arrayUnion(wcSpecs.getText().toString()));
        }

        data.put("headset_barcode", hsBarcode.getText().toString());
        data.put("headset_specs", hsSpecs.getText().toString());
        if (!hsSpecs.getText().toString().isEmpty()) {
            db.collection("Specs").document("Headset").update("specs", FieldValue.arrayUnion(hsSpecs.getText().toString()));
        }


        db.collection("Files").document(Workstation.fileId).collection("list").document(getIntent().getStringExtra("id"))
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        hideKeyboard();

                        db.collection("Files").document(Workstation.fileId).update("last_updated", new Date())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(parentLayout, "Updated", Snackbar.LENGTH_SHORT)
                                                .show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(parentLayout, e.getMessage(), Snackbar.LENGTH_LONG)
                                                .show();
                                    }
                                });


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        hideKeyboard();
                        Snackbar.make(parentLayout, e.getMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                });

    }

    public void loadData() {


        db.collection("Files").document(Workstation.fileId).collection("list").document(getIntent().getStringExtra("id")).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Snackbar.make(parentLayout, e.getMessage(), Snackbar.LENGTH_LONG)
                            .show();
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    moBarcode.setText(snapshot.getString("motherboard_barcode"));
                    moSpecs.setText(snapshot.getString("motherboard_specs"));

                    ppBarcode.setText(snapshot.getString("processor_barcode"));
                    ppSpecs.setText(snapshot.getString("processor_specs"));


                    mmBarcode1.setText(snapshot.getString("memory_barcode1"));
                    mmSpecs1.setText(snapshot.getString("memory_specs1"));
                    mmBarcode2.setText(snapshot.getString("memory_barcode2"));
                    mmSpecs2.setText(snapshot.getString("memory_specs2"));


                    lpBarcode.setText(snapshot.getString("adapter_barcode"));
                    lpSpecs.setText(snapshot.getString("adapter_specs"));


                    dmBarcode.setText(snapshot.getString("videocard_barcode"));
                    dmSpecs.setText(snapshot.getString("videocard_specs"));


                    hdBarcode1.setText(snapshot.getString("harddisk_barcode1"));
                    hdSpecs1.setText(snapshot.getString("harddisk_specs1"));
                    hdBarcode2.setText(snapshot.getString("harddisk_barcode2"));
                    hdSpecs2.setText(snapshot.getString("harddisk_specs2"));

                    pwBarcode.setText(snapshot.getString("powersupply_barcode"));
                    pwSpecs.setText(snapshot.getString("powersupply_specs"));


                    ccBarcode.setText(snapshot.getString("casing_barcode"));
                    ccSpecs.setText(snapshot.getString("casing_specs"));


                    omBarcode1.setText(snapshot.getString("monitor_barcode1"));
                    omSpecs1.setText(snapshot.getString("monitor_specs1"));
                    omBarcode2.setText(snapshot.getString("monitor_barcode2"));
                    omSpecs2.setText(snapshot.getString("monitor_specs2"));


                    moBarcode.setText(snapshot.getString("motherboard_barcode"));
                    moSpecs.setText(snapshot.getString("motherboard_specs"));


                    kbBarcode.setText(snapshot.getString("keyboard_barcode"));
                    kbSpecs.setText(snapshot.getString("keyboard_specs"));


                    msBarcode.setText(snapshot.getString("mouse_barcode"));
                    msSpecs.setText(snapshot.getString("mouse_specs"));

                    wcBarcode.setText(snapshot.getString("webcam_barcode"));
                    wcSpecs.setText(snapshot.getString("webcam_specs"));

                    hsBarcode.setText(snapshot.getString("headset_barcode"));
                    hsSpecs.setText(snapshot.getString("headset_specs"));

                    loadSpecs();


                } else {
                    Snackbar.make(parentLayout, "Data is null", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

    public void loadSpecs() {

        //MOTHERBOARD
        db.collection("Specs")
                .document("Motherboard").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {

                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.moSpinner);


                            final ArrayAdapter<String>[] moadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            moadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(moadapter[0]);
                            s.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                                @Override
                                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                                    Toast.makeText(Specification.this, "adsqweqwe", Toast.LENGTH_SHORT).show();
                                }
                            });

                            s.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    Toast.makeText(Specification.this, event.toString(), Toast.LENGTH_SHORT).show();
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] moisClicked = {true};



                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (moisClicked[0]) {
                                                    moSpecs.setText(selectedItem);
                                                    moisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                moisClicked[0] = false;

                                                Toast.makeText(Specification.this, "Cnacled", Toast.LENGTH_SHORT).show();
                                            }

                                        });

                                    }
                                    return false;
                                }

                            });

                        }


                    }
                });

        //PROCESSOR
        db.collection("Specs")
                .document("Processor").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {


                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.ppSpinner);


                            final ArrayAdapter<String>[] ppadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            ppadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(ppadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] ppisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (ppisClicked[0]) {
                                                    ppSpecs.setText(selectedItem);
                                                    ppisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                ppisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });

        //MEMORY1
        db.collection("Specs")
                .document("Memory").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.mmSpinner1);


                            final ArrayAdapter<String>[] mmadapter1 = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            mmadapter1[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(mmadapter1[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] mmisClicked1 = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (mmisClicked1[0]) {
                                                    mmSpecs1.setText(selectedItem);
                                                    mmisClicked1[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                mmisClicked1[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });

        //MEMORY2
        db.collection("Specs")
                .document("Memory").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {


                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.mmSpinner2);


                            final ArrayAdapter<String>[] mmadapter2 = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            mmadapter2[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(mmadapter2[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] mmisClicked2 = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (mmisClicked2[0]) {
                                                    mmSpecs2.setText(selectedItem);
                                                    mmisClicked2[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                mmisClicked2[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });
                        }

                    }
                });

        //ADAPTER
        db.collection("Specs")
                .document("Adapter").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.lpSpinner);


                            final ArrayAdapter<String>[] lpadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            lpadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(lpadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] lpisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (lpisClicked[0]) {
                                                    lpSpecs.setText(selectedItem);
                                                    lpisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                lpisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });

        //VIDEO CARD
        db.collection("Specs")
                .document("VideoCard").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.dmSpinner);


                            final ArrayAdapter<String>[] dmadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            dmadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(dmadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] dmisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (dmisClicked[0]) {
                                                    dmSpecs.setText(selectedItem);
                                                    dmisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                dmisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });
                        }

                    }
                });

        //HARDDISK1
        db.collection("Specs")
                .document("HardDisk").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {


                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.hdSpinner1);


                            final ArrayAdapter<String>[] hdadapter1 = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            hdadapter1[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(hdadapter1[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] hdisClicked1 = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (hdisClicked1[0]) {
                                                    hdSpecs1.setText(selectedItem);
                                                    hdisClicked1[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                hdisClicked1[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });
                        }

                    }
                });

        //HARDDISK2
        db.collection("Specs")
                .document("HardDisk").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {


                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.hdSpinner2);


                            final ArrayAdapter<String>[] hdadapter2 = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            hdadapter2[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(hdadapter2[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] hdisClicked2 = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (hdisClicked2[0]) {
                                                    hdSpecs2.setText(selectedItem);
                                                    hdisClicked2[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                hdisClicked2[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });


        //POWERSUPPLY
        db.collection("Specs")
                .document("PowerSupply").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.pwSpinner);


                            final ArrayAdapter<String>[] pwadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            pwadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(pwadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] pwisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (pwisClicked[0]) {
                                                    pwSpecs.setText(selectedItem);
                                                    pwisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                pwisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });

        //CASING
        db.collection("Specs")
                .document("Casing").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.ccSpinner);


                            final ArrayAdapter<String>[] ccadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            ccadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(ccadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] ccisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (ccisClicked[0]) {
                                                    ccSpecs.setText(selectedItem);
                                                    ccisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                ccisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });
                        }

                    }
                });

        //MONITOR1
        db.collection("Specs")
                .document("Monitor").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()){


                        List<String> list = (List<String>) document.get("specs");
                        Spinner s = (Spinner) findViewById(R.id.omSpinner1);


                        final ArrayAdapter<String>[] omadapter1 = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                android.R.layout.simple_spinner_item, list)};
                        omadapter1[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        s.setAdapter(omadapter1[0]);

                        s.setOnTouchListener(new View.OnTouchListener() {

                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    final Boolean[] omisClicked1 = {true};


                                    s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            String selectedItem = parent.getItemAtPosition(position).toString();

                                            if (omisClicked1[0]) {
                                                omSpecs1.setText(selectedItem);
                                                omisClicked1[0] = false;
                                            }


                                        }

                                        public void onNothingSelected(AdapterView<?> parent) {
                                            omisClicked1[0] = false;
                                        }
                                    });


                                }
                                return false;
                            }

                        });

                        }
                    }
                });

        //MONITOR2
        db.collection("Specs")
                .document("Monitor").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.omSpinner2);


                            final ArrayAdapter<String>[] omadapter2 = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            omadapter2[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(omadapter2[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] omisClicked2 = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (omisClicked2[0]) {
                                                    omSpecs2.setText(selectedItem);
                                                    omisClicked2[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                omisClicked2[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });


        //KEYBOARD
        db.collection("Specs")
                .document("Keyboard").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.kbSpinner);


                            final ArrayAdapter<String>[] kbadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            kbadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(kbadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] kbisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (kbisClicked[0]) {
                                                    kbSpecs.setText(selectedItem);
                                                    kbisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                kbisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });


        //MOUSE
        db.collection("Specs")
                .document("Mouse").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.msSpinner);


                            final ArrayAdapter<String>[] msadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            msadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(msadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] msisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (msisClicked[0]) {
                                                    msSpecs.setText(selectedItem);
                                                    msisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                msisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });
                        }

                    }
                });

//WEBCAM
        db.collection("Specs")
                .document("Webcam").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.wcSpinner);


                            final ArrayAdapter<String>[] wcadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            wcadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(wcadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] wcisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (wcisClicked[0]) {
                                                    wcSpecs.setText(selectedItem);
                                                    wcisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                wcisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });
                        }

                    }
                });

        //HEADSET
        db.collection("Specs")
                .document("Headset").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<String> list = (List<String>) document.get("specs");
                            Spinner s = (Spinner) findViewById(R.id.hsSpinner);


                            final ArrayAdapter<String>[] hsadapter = new ArrayAdapter[]{new ArrayAdapter<String>(Specification.this,
                                    android.R.layout.simple_spinner_item, list)};
                            hsadapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(hsadapter[0]);

                            s.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        final Boolean[] hsisClicked = {true};


                                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String selectedItem = parent.getItemAtPosition(position).toString();

                                                if (hsisClicked[0]) {
                                                    hsSpecs.setText(selectedItem);
                                                    hsisClicked[0] = false;
                                                }


                                            }

                                            public void onNothingSelected(AdapterView<?> parent) {
                                                hsisClicked[0] = false;
                                            }
                                        });


                                    }
                                    return false;
                                }

                            });

                        }
                    }
                });


    }

    public static void hideKeyboard() {
        activity.getCurrentFocus();
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String barcode = data.getStringExtra("barcodeString");

                if (barcode.contains("MO")) {
                    moBarcode.setText(barcode);
                    moBarcode.requestFocus();
                }
                if (barcode.contains("PP")) {
                    ppBarcode.setText(barcode);
                    ppBarcode.requestFocus();

                }
                if (barcode.contains("MM")) {

                    if (mmBarcode1.getText().toString().isEmpty()) {
                        mmBarcode1.setText(barcode);
                        mmBarcode1.requestFocus();
                    } else {
                        if (mmBarcode2.getText().toString().isEmpty()) {
                            mmBarcode2.setText(barcode);
                            mmBarcode2.requestFocus();
                        }
                    }


                }
                if (barcode.contains("LP")) {
                    lpBarcode.setText(barcode);
                    lpBarcode.requestFocus();
                }
                if (barcode.contains("DM")) {
                    dmBarcode.setText(barcode);
                    dmBarcode.requestFocus();
                }
                if (barcode.contains("HD")) {

                    if (hdBarcode1.getText().toString().isEmpty()) {
                        hdBarcode1.setText(barcode);
                        hdBarcode1.requestFocus();
                    } else {
                        if (hdBarcode2.getText().toString().isEmpty()) {
                            hdBarcode2.setText(barcode);
                            hdBarcode2.requestFocus();
                        }
                    }

                }
                if (barcode.contains("PW")) {
                    pwBarcode.setText(barcode);
                    pwBarcode.requestFocus();
                }
                if (barcode.contains("CC")) {
                    ccBarcode.setText(barcode);
                    ccBarcode.requestFocus();
                }

                if (barcode.contains("OM")) {
                    if (omBarcode1.getText().toString().isEmpty()) {
                        omBarcode1.setText(barcode);
                        omBarcode1.requestFocus();
                    } else {
                        if (omBarcode2.getText().toString().isEmpty()) {
                            omBarcode2.setText(barcode);
                            omBarcode2.requestFocus();
                        }
                    }
                }
                if (barcode.contains("KB")) {
                    kbBarcode.setText(barcode);
                    kbBarcode.requestFocus();
                }
                if (barcode.contains("MS")) {
                    msBarcode.setText(barcode);
                    msBarcode.requestFocus();
                }
                if (barcode.contains("WC")) {
                    wcBarcode.setText(barcode);
                    wcBarcode.requestFocus();
                }
                if (barcode.contains("HS")) {
                    hsBarcode.setText(barcode);
                    hsBarcode.requestFocus();
                }


            }
        }
    }
}