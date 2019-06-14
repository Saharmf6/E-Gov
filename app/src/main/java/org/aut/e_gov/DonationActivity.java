package org.aut.e_gov;

import org.aut.e_gov.R;
import org.aut.e_gov.models.DatabaseAccess;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class DonationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText et1,edtPass2,edtCvv2, edtYear, edtMonth, edtAmount;
    Button btnDone;
    String choosed = "", cardNumber;
    String[] orgNames={"محک","کمک به سیل زدگان","حلال احمر","جمعیت امام علی","کمک به زلزله زدگان"};

    public void findViews(){
        et1 = findViewById(R.id.editTextDonation);
        edtCvv2 = findViewById(R.id.edtCvv2Donation);
        edtYear = findViewById(R.id.edtYearDonation);
        edtMonth = findViewById(R.id.edtMonthDonation);
        edtPass2 = findViewById(R.id.edtPass2Donation);
        edtAmount = findViewById(R.id.edtAmountDonation);
        btnDone = findViewById(R.id.btnDoneDonation);
    }

    public void implementListeners(){
        final DatabaseAccess dbAccess = new DatabaseAccess(this);

        et1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et1.getText().toString().length()== 16)     //size as per your requirement
                {
                    edtCvv2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        edtYear.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edtYear.getText().toString().length()== 2)     //size as per your requirement
                {
                    edtMonth.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        edtMonth.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edtMonth.getText().toString().length()== 2)     //size as per your requirement
                {
                    edtPass2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        edtPass2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edtPass2.getText().toString().length()== 4)     //size as per your requirement
                {
                    edtAmount.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean userFound = false;
                cardNumber = et1.getText().toString();
                Cursor bankCursor = dbAccess.getDb().rawQuery("SELECT * FROM Bank", null);
                bankCursor.moveToFirst();
                while (!bankCursor.isAfterLast()){
                    String thisCardNumber = bankCursor.getString(0);
                    if(thisCardNumber.equals(cardNumber)) {
                        userFound = true;
                        break;
                    }
                    else
                        bankCursor.moveToNext();
                }
                if (userFound) {
                    ContentValues bankcv = new ContentValues();
                    bankcv.put("card_number", cardNumber);
                    bankcv.put("pass2", bankCursor.getInt(1));
                    bankcv.put("pass2", "1234");
                    bankcv.put("cvv2", bankCursor.getInt(2));
                    bankcv.put("expire_year", bankCursor.getInt(3));
                    bankcv.put("expire_month", bankCursor.getInt(4));
                    Double amount = Double.parseDouble(edtAmount.getText().toString());
                    bankcv.put("balance", bankCursor.getDouble(5) - amount);
                    dbAccess.getDb().update("Bank", bankcv, "card_number = '" + cardNumber + "'", null);
                    Toast.makeText(getApplicationContext(), "شما به خیریه " + choosed + " مبلغ " + amount + " تومان کمک کردید.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DonationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (userFound == false){
                    Toast.makeText(getApplicationContext(), "این کارت در سامانه ثبت نشده است",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,orgNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        findViews();
        implementListeners();
    }


    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), orgNames[position], Toast.LENGTH_LONG).show();
        choosed = orgNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    // TODO Auto-generated method stub

    }
}