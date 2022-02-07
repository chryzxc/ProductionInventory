package it.inventory.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Specification extends AppCompatActivity {
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
         moBarcode = findViewById(R.id.moBarcode);
         moSpecs= findViewById(R.id.moSpecs);

         ppBarcode= findViewById(R.id.ppBarcode);
         ppSpecs= findViewById(R.id.ppSpecs);

         mmBarcode1= findViewById(R.id.mmBarcode1);
         mmSpecs1= findViewById(R.id.mmSpecs1);
         mmBarcode2= findViewById(R.id.mmBarcode2);
         mmSpecs2= findViewById(R.id.mmSpecs2);

         lpBarcode= findViewById(R.id.lpBarcode);
         lpSpecs= findViewById(R.id.lpSpecs);

         dmBarcode= findViewById(R.id.dmBarcode);
         dmSpecs= findViewById(R.id.dmSpecs);

         hdBarcode1= findViewById(R.id.hdBarcode1);
         hdSpecs1= findViewById(R.id.hdSpecs1);
         hdBarcode2= findViewById(R.id.hdBarcode2);
         hdSpecs2= findViewById(R.id.hdSpecs2);

         pwBarcode= findViewById(R.id.pwBarcode);
         pwSpecs= findViewById(R.id.pwSpecs);


         ccBarcode= findViewById(R.id.ccBarcode);
         ccSpecs= findViewById(R.id.ccSpecs);

         omBarcode1= findViewById(R.id.omBarcode1);
         omSpecs1= findViewById(R.id.omSpecs1);
         omBarcode2= findViewById(R.id.omBarcode2);
         omSpecs2= findViewById(R.id.omSpecs2);

         kbBarcode= findViewById(R.id.kbBarcode);
         kbSpecs= findViewById(R.id.kbSpecs);

         msBarcode= findViewById(R.id.msBarcode);
         msSpecs= findViewById(R.id.msSpecs);

         wcBarcode= findViewById(R.id.wcBarcode);
         wcSpecs= findViewById(R.id.wcSpecs);

         hsBarcode= findViewById(R.id.hsBarcode);
         hsSpecs= findViewById(R.id.hsSpecs);



        ImageView scanButton = (ImageView) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String barcode = data.getStringExtra("barcodeString");

                if (barcode.contains("MO")){
                    moBarcode.setText(barcode);
                    moBarcode.requestFocus();
                }
                if (barcode.contains("PP")){
                    ppBarcode.setText(barcode);
                    ppBarcode.requestFocus();

                }
                if (barcode.contains("MM")){

                    if (mmBarcode1.getText().toString().isEmpty()){
                        mmBarcode1.setText(barcode);
                        mmBarcode1.requestFocus();
                    }else{
                        if (mmBarcode2.getText().toString().isEmpty()){
                            mmBarcode2.setText(barcode);
                            mmBarcode2.requestFocus();
                        }
                    }


                }
                if (barcode.contains("LP")){
                    lpBarcode.setText(barcode);
                    lpBarcode.requestFocus();
                }
                if (barcode.contains("DM")){
                    dmBarcode.setText(barcode);
                    dmBarcode.requestFocus();
                }
                if (barcode.contains("HD")){

                    if (hdBarcode1.getText().toString().isEmpty()){
                        hdBarcode1.setText(barcode);
                        hdBarcode1.requestFocus();
                    }else{
                        if (hdBarcode2.getText().toString().isEmpty()){
                            hdBarcode2.setText(barcode);
                            hdBarcode2.requestFocus();
                        }
                    }

                }
                if (barcode.contains("PW")){
                    pwBarcode.setText(barcode);
                    pwBarcode.requestFocus();
                }
                if (barcode.contains("CC")){
                    ccBarcode.setText(barcode);
                    ccBarcode.requestFocus();
                }

                if (barcode.contains("OM")){
                    if (omBarcode1.getText().toString().isEmpty()){
                        omBarcode1.setText(barcode);
                        omBarcode1.requestFocus();
                    }else{
                        if (omBarcode2.getText().toString().isEmpty()){
                            omBarcode2.setText(barcode);
                            omBarcode2.requestFocus();
                        }
                    }
                }
                if (barcode.contains("KB")){
                    kbBarcode.setText(barcode);
                    kbBarcode.requestFocus();
                }
                if (barcode.contains("MS")){
                    msBarcode.setText(barcode);
                    msBarcode.requestFocus();
                }
                if (barcode.contains("WC")){
                    wcBarcode.setText(barcode);
                    wcBarcode.requestFocus();
                }
                if (barcode.contains("HS")){
                    hsBarcode.setText(barcode);
                    hsBarcode.requestFocus();
                }



            }
        }
    }
}