package it.inventory.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specification extends AppCompatActivity {

    FirebaseFirestore db;
    private static Activity activity;

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

        TextView computerNameSpec = findViewById(R.id.computerNameSpec);
        computerNameSpec.setText(String.valueOf(getIntent().getIntExtra("workstationNumber", 0000)));

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
                //loadSpecs();
                 instantSave();

            }
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_previous:

                        return true;
                    case R.id.navigation_scan:
                        Intent intent = new Intent(Specification.this, Scanner.class);
                        startActivityForResult(intent, 1);
                        return true;
                    case R.id.navigation_next:

                        return true;
                }
                return false;
            }
        });

    }


    public void instantSave() {
        View parentLayout = findViewById(android.R.id.content);

        Map<String, Object> data = new HashMap<>();
        data.put("computer_name", getIntent().getIntExtra("workstationNumber", 0000));
        data.put("last_updated", new Date());

        data.put("motherboard_barcode", moBarcode.getText().toString());
        data.put("motherboard_specs", moSpecs.getText().toString());

        data.put("processor_barcode", ppBarcode.getText().toString());
        data.put("processor_specs", ppSpecs.getText().toString());

        data.put("memory_barcode", mmBarcode1.getText().toString());
        data.put("memory_specs", mmSpecs1.getText().toString());
        data.put("memory_barcode", mmBarcode2.getText().toString());
        data.put("memory_specs", mmSpecs2.getText().toString());

        data.put("adapter_barcode", lpBarcode.getText().toString());
        data.put("adapter_specs", lpSpecs.getText().toString());

        data.put("videocard_barcode", dmBarcode.getText().toString());
        data.put("videocard_specs", dmSpecs.getText().toString());

        data.put("harddisk_barcode", hdBarcode1.getText().toString());
        data.put("harddisk_specs", hdSpecs1.getText().toString());
        data.put("harddisk_barcode", hdBarcode2.getText().toString());
        data.put("harddisk_specs", hdSpecs2.getText().toString());

        data.put("powersupply_barcode", pwBarcode.getText().toString());
        data.put("powersupply_specs", pwSpecs.getText().toString());

        data.put("casing_barcode", ccBarcode.getText().toString());
        data.put("casing_specs", ccSpecs.getText().toString());

        data.put("monitor_barcode", omBarcode1.getText().toString());
        data.put("monitor_specs", omSpecs1.getText().toString());
        data.put("monitor_barcode", omBarcode2.getText().toString());
        data.put("monitor_specs", omSpecs2.getText().toString());

        data.put("keyboard_barcode", kbBarcode.getText().toString());
        data.put("keyboard_specs", kbSpecs.getText().toString());

        data.put("mouse_barcode", msBarcode.getText().toString());
        data.put("mouse_specs", msSpecs.getText().toString());

        data.put("webcam_barcode", wcBarcode.getText().toString());
        data.put("webcam_specs", wcSpecs.getText().toString());

        data.put("headset_barcode", hsBarcode.getText().toString());
        data.put("headset_specs", hsBarcode.getText().toString());


        db.collection("Files").document(Workstation.fileId).collection("list").document(getIntent().getStringExtra("id"))
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        hideKeyboard();
                        Snackbar.make(parentLayout, "Updated", Snackbar.LENGTH_SHORT)
                                .show();
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

    }

    public void loadSpecs() {


        db.collection("Specs")
                .document("Motherboard").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        List<String> motherboardList = (List<String>) document.get("specs");
                        Spinner s = (Spinner) findViewById(R.id.moSpinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Specification.this,
                                android.R.layout.simple_spinner_item, motherboardList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        s.setAdapter(adapter);

                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selectedItem = parent.getItemAtPosition(position).toString();

                                moSpecs.setText(selectedItem);

                            }

                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }
                });

        moSpecs.setText("");
        ppSpecs.setText("");
        mmSpecs1.setText("");
        mmSpecs2.setText("");
        lpSpecs.setText("");
        dmSpecs.setText("");
        hdSpecs1.setText("");
        hdSpecs2.setText("");
        pwSpecs.setText("");
        ccSpecs.setText("");
        omSpecs1.setText("");
        omSpecs2.setText("");
        kbSpecs.setText("");
        msSpecs.setText("");
        wcSpecs.setText("");
        hsSpecs.setText("");

    }

    public static void hideKeyboard() {
        activity.getCurrentFocus();
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
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